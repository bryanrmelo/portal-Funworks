package br.com.keyworks.view.backing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import br.com.keyworks.enumeracoes.SimNaoEnum;
import br.com.keyworks.filter.MensalidadeFilter;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.services.MensalidadeService;
import br.com.keyworks.services.UsuarioService;

@Named("mensalidadeAdmBack")
@ViewScoped
public class MensalidadeAdministracaoBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private MensalidadeService mensalidadeService;

	@Inject
	private UsuarioService usuarioService;

	private StreamedContent comprovanteDownload;

	private List<String> meses;

	private List<String> mesesSelecionados;

	private List<Mensalidade> listaMensalidades;

	private MensalidadeFilter filtro = new MensalidadeFilter();

	private Map<String, Object> filtrosSelecionados = new HashMap<String, Object>();

	@PostConstruct
	public void init() {
		listaMensalidades = mensalidadeService.getAllDadosExistentes();
		criarListaMeses();

	}

	private void criarListaMeses() {
		meses = new ArrayList<String>();
		meses.add("Janeiro");
		meses.add("Fevereiro");
		meses.add("Março");
		meses.add("Abril");
		meses.add("Maio");
		meses.add("Junho");
		meses.add("Julho");
		meses.add("Agosto");
		meses.add("Setembro");
		meses.add("Outubro");
		meses.add("Novembro");
		meses.add("Dezembro");
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

	public void downloadComprovante(Mensalidade mensalidade) {
		InputStream stream = new ByteArrayInputStream(mensalidadeService.getComprovante(mensalidade.getId()));
		this.setComprovanteDownload(new DefaultStreamedContent(stream, "application/pdf", mensalidade.getNomeComprovante()));
	}

	public String mostrarNomeComprovante(Mensalidade mensalidade) {
		return mensalidadeService.getNomeComprovante(mensalidade.getId());
	}

	public void salvarFiltros() {

		Optional.ofNullable(filtro.getPagamento()).ifPresent(filtro -> filtrosSelecionados.put("pagamento", this.filtro.getPagamento()));

		Optional.ofNullable(filtro.getComprovante()).ifPresent(filtro -> filtrosSelecionados.put("comprovante", this.filtro.getComprovante()));

		Optional.ofNullable(filtro.getAssociado()).ifPresent(filtro -> filtrosSelecionados.put("associado", this.filtro.getAssociado()));

		Optional.ofNullable(filtro.getMeses()).ifPresent(filtro -> filtrosSelecionados.put("meses", this.filtro.getMeses()));

		listaMensalidades = mensalidadeService.getAllDadosExistentesComFiltros(filtrosSelecionados);
	}

	public void limparFiltros() {
		this.filtrosSelecionados.clear();
		listaMensalidades = mensalidadeService.getAllDadosExistentesComFiltros(filtrosSelecionados);
	}

	public List<SimNaoEnum> getSimNaoValues() {
		return Arrays.asList(SimNaoEnum.values());
	}

	public List<Mensalidade> getListaMensalidades() {
		return listaMensalidades;
	}

	public void setListaMensalidades(List<Mensalidade> listaMensalidades) {
		this.listaMensalidades = listaMensalidades;
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

	public List<String> getMeses() {
		return meses;
	}

	public void setMeses(List<String> meses) {
		this.meses = meses;
	}

	public List<String> getMesesSelecionados() {
		return mesesSelecionados;
	}

	public void setMesesSelecionados(List<String> mesesSelecionados) {
		this.mesesSelecionados = mesesSelecionados;
	}
}
