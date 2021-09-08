package br.com.keyworks.view.backing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.mail.EmailException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import br.com.keyworks.enumeracoes.ContaEnum;
import br.com.keyworks.enumeracoes.MesEnum;
import br.com.keyworks.enumeracoes.PagoPendenteEnum;
import br.com.keyworks.enumeracoes.SimNaoEnum;
import br.com.keyworks.exceptions.MensalidadeExistenteException;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.model.entities.administracao.MensalidadeFilter;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.services.MensalidadeService;
import br.com.keyworks.services.UsuarioService;
import br.com.keyworks.util.EnviarEmailUtil;
import br.com.keyworks.util.ExpiracaoUtil;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.util.LogoutUtil;
import br.com.keyworks.view.componentes.GridLazyLoader;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.IGridLazyLoader;
import br.com.keyworks.view.componentes.PagedResult;

@Named("mensalidadeAdmBack")
@ViewScoped
public class MensalidadeAdministracaoBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private MensalidadeService mensalidadeService;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private IdentidadeSessao sessao;

	@SuppressWarnings("unused")
	private List<MesEnum> meses;

	private List<String> anos;

	@SuppressWarnings("unused")
	private List<PagoPendenteEnum> pagamento;

	private Map<String, Object> filtrosSelecionados = new HashMap<String, Object>();

	private List<Mensalidade> mensalidadesSelecionadas = new ArrayList<Mensalidade>();

	private StreamedContent comprovanteDownload;

	private GridLazyLoader<Mensalidade> gridLazyLoader;

	private String opcaoAtualizacaoSelecionada;

	private MensalidadeFilter filtro = new MensalidadeFilter();

	private List<Mensalidade> listaMensalidades = new ArrayList<Mensalidade>();

	private Date dataNovaMensalidade;

	private String opcao;

	@PostConstruct
	public void init() {
		pesquisar();
		if (!ExpiracaoUtil.ValidaExpiracao(sessao.getDataCriacao())) {
			LogoutUtil.logout();
		}
		this.anos = mensalidadeService.getAnos();
		Usuario usuario = usuarioService.getUsuario(sessao.getNome());
		if (usuario.getTipo().equals(ContaEnum.ADMINISTRADOR.getId())) {
			opcao = "0";
		} else {
			opcao = "1";
		}
	}

	public void pesquisar() {

		setGridLazyLoader(new GridLazyLoader<Mensalidade>(new IGridLazyLoader<Mensalidade>() {
			@Override
			public PagedResult<Mensalidade> load(GridLazyLoaderDTO gridLazyLoaderDTO) {

				gridLazyLoaderDTO.setFilters(setFiltros());

				return mensalidadeService.getMensalidades(gridLazyLoaderDTO);
			}
		}) {

			private static final long serialVersionUID = 1L;

			@Override
			public Mensalidade getRowData(String rowKey) {

				List<Mensalidade> listaMensalidades = (List<Mensalidade>) getWrappedData();

				for (Mensalidade mensalidade : listaMensalidades) {
					if (mensalidade.getId().equals(Integer.parseInt(rowKey))) {
						return mensalidade;
					}
				}
				return null;

			}

			@Override
			public Object getRowKey(Mensalidade mensalidade) {
				return mensalidade.getId();
			}
		});
	}

	public void salvarFiltros() {
		this.filtrosSelecionados = setFiltros();
	}

	public Map<String, Object> setFiltros() {

		Optional.ofNullable(filtro.getPagamento()).ifPresent(filtro -> this.filtrosSelecionados.put("pagamento", this.filtro.getPagamento()));

		Optional.ofNullable(filtro.getComprovante()).ifPresent(filtro -> this.filtrosSelecionados.put("comprovante", this.filtro.getComprovante()));

		Optional.ofNullable(filtro.getAssociado()).ifPresent(filtro -> this.filtrosSelecionados.put("associado", this.filtro.getAssociado()));

		Optional.ofNullable(filtro.getMeses()).ifPresent(filtro -> this.filtrosSelecionados.put("meses", this.filtro.getMeses()));

		Optional.ofNullable(filtro.getAnos()).ifPresent(filtro -> this.filtrosSelecionados.put("anos", this.filtro.getAnos()));

		return this.filtrosSelecionados;

	}

	public void limparFiltros() {
		this.filtrosSelecionados.clear();
		this.filtro = new MensalidadeFilter();
		pesquisar();
	}

	public void atualizarMensalidades() {
		for (Mensalidade mensalidade : mensalidadesSelecionadas) {
			if (opcaoAtualizacaoSelecionada.equals("pago")) {
				if (mensalidade.getComprovante() != null) {
					mensalidade.setPagamento("pa");
					mensalidadeService.updateMensalidade(mensalidade);
					enviarEmailConfirmacao(mensalidade.getUsuario().getLogin(), mensalidade.getDataVencimento());
					mostrarMensangemAtualizacaoPagoPendente(mensalidadesSelecionadas);
				} else {
					FacesMessageUtils.addErrorMessage("Mensalidade não possui comprovante.");
				}
			} else
				if (opcaoAtualizacaoSelecionada.equals("pendente")) {
					mensalidade.setPagamento("pe");
					mensalidadeService.updateMensalidade(mensalidade);
					mostrarMensangemAtualizacaoPagoPendente(mensalidadesSelecionadas);
				}
		}

		mensalidadesSelecionadas.clear();
	}

	private void enviarEmailConfirmacao(String login, Date data) {
		try {
			LocalDate mes = data.toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate();
			String mesExtenso = null;
			System.out.println(mes);
			for (MesEnum valor : MesEnum.values()) {
				if (valor.getId().equals(String.valueOf(mes.getMonthValue()))) {
					mesExtenso = valor.getDescricao();
				}
			}

			EnviarEmailUtil.enviar(login, mesExtenso);
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

	public String nomeParcial(String nome) {
		return usuarioService.gerenciarNomeParaView("parcial", nome);
	}

	public String nomeCompleto(String nome) {
		return usuarioService.gerenciarNomeParaView("completo", nome);
	}

	public String valorFormatado(Double valor) {
		return mensalidadeService.formatarValor(valor);
	}

	public String dataFormatada(Date data) {
		return mensalidadeService.formatarData(data);

	}

	public String mostrarNomePagamento(String pagamento) {
		if (pagamento.equals("pa")) {
			return "Pago";
		} else {
			return "Pendente";
		}
	}

	public String mostrarNomeComprovante(Mensalidade mensalidade) {
		return mensalidadeService.getNomeComprovante(mensalidade.getId());
	}

	private void mostrarMensangemAtualizacaoPagoPendente(List<Mensalidade> mensalidadesSelecionadas) {
		StringBuilder string = new StringBuilder();
		string.append("Mensalidades atualizadas: ");
		for (Mensalidade mensalidade : mensalidadesSelecionadas) {
			string.append(usuarioService.gerenciarNomeParaView("completo", mensalidade.getUsuario().getLogin()) + " com data: "
							+ dataFormatada(mensalidade.getDataVencimento()) + "	");
		}
		FacesMessageUtils.addInfoMessage(string.toString());
	}

	public void criarNovaMensalidade() {
		try {
			mensalidadeService.createMensalidade(dataNovaMensalidade);
		} catch (MensalidadeExistenteException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		}
	}

	public void downloadComprovante(Mensalidade mensalidade) {
		InputStream stream = new ByteArrayInputStream(mensalidadeService.getComprovante(mensalidade.getId()));
		this.setComprovanteDownload(new DefaultStreamedContent(stream, "application/pdf", mensalidade.getNomeComprovante()));
	}

	public void mudarPagina() {
		if (opcao.equals("1")) {
			opcao = "0";
		} else {
			opcao = "1";
		}
	}

	public Long getQuantidadeUsuarios() {
		return mensalidadeService.getQuantidadeUsuarios();
	}

	public List<SimNaoEnum> getSimNaoValues() {
		return Arrays.asList(SimNaoEnum.values());
	}

	public List<PagoPendenteEnum> getPagoPendenteValues() {
		return Arrays.asList(PagoPendenteEnum.values());
	}

	public StreamedContent getComprovanteDownload() {
		return comprovanteDownload;
	}

	public void setComprovanteDownload(StreamedContent comprovanteDownload) {
		this.comprovanteDownload = comprovanteDownload;
	}

	public MensalidadeFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(MensalidadeFilter filtro) {
		this.filtro = filtro;
	}

	public Map<String, Object> getFiltrosSelecionados() {
		return filtrosSelecionados;
	}

	public void setFiltrosSelecionados(Map<String, Object> filtrosSelecionados) {
		this.filtrosSelecionados = filtrosSelecionados;
	}

	public List<MesEnum> getMeses() {
		return Arrays.asList(MesEnum.values());
	}

	public void setMeses(List<MesEnum> meses) {
		this.meses = meses;
	}

	public List<String> getAnos() {
		return this.anos;
	}

	public void setAnos(List<String> anos) {
		this.anos = anos;
	}

	public List<Mensalidade> getMensalidadesSelecionadas() {
		return mensalidadesSelecionadas;
	}

	public void setMensalidadesSelecionadas(List<Mensalidade> mensalidadesSelecionadas) {
		this.mensalidadesSelecionadas = mensalidadesSelecionadas;
	}

	public String getOpcaoAtualizacaoSelecionada() {
		return opcaoAtualizacaoSelecionada;
	}

	public void setOpcaoAtualizacaoSelecionada(String opcaoAtualizacaoSelecionada) {
		this.opcaoAtualizacaoSelecionada = opcaoAtualizacaoSelecionada;
	}

	public List<Mensalidade> getListaMensalidades() {
		return listaMensalidades;
	}

	public void setListaMensalidades(List<Mensalidade> listaMensalidades) {
		this.listaMensalidades = listaMensalidades;
	}

	public GridLazyLoader<Mensalidade> getGridLazyLoader() {
		return gridLazyLoader;
	}

	public void setGridLazyLoader(GridLazyLoader<Mensalidade> gridLazyLoader) {
		this.gridLazyLoader = gridLazyLoader;
	}

	public Date getDataNovaMensalidade() {
		return dataNovaMensalidade;
	}

	public void setDataNovaMensalidade(Date dataNovaMensalidade) {
		this.dataNovaMensalidade = dataNovaMensalidade;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

}
