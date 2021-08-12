package br.com.keyworks.view.backing;

import static br.com.keyworks.constants.Constants.IMG_CHECK_FALSE;
import static br.com.keyworks.constants.Constants.IMG_CHECK_TRUE;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.services.MensalidadeService;
import br.com.keyworks.services.UsuarioService;

@Named("mensalidadeBack")
@ViewScoped
public class MensalidadeBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private IdentidadeSessao sessao;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private MensalidadeService mensalidadeService;

	private String nome;

	private UploadedFile comprovante;

	private StreamedContent comprovanteDownload;

	private List<Mensalidade> listaMensalidades;

	private int indexSelecionado;

	@PostConstruct
	public void Init() {
		this.nome = sessao.getNome();
		this.listaMensalidades = mensalidadeService.getDadosExistentes(nome);
	}

	public String nomeParcial() {
		return usuarioService.gerenciarNomeParaView("parcial", nome);
	}

	public String nomeCompleto() {
		return usuarioService.gerenciarNomeParaView("completo", nome);
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

	public void listenerObservacaoEdit(CellEditEvent event) {
		Mensalidade mensalidade = listaMensalidades.get(event.getRowIndex());
		mensalidade.setObservacao((String) event.getNewValue());
		mensalidadeService.salvarObservacao(mensalidade);

	}

	public void salvarComprovante() {
		mensalidadeService.salvarComprovante(listaMensalidades.get(indexSelecionado), comprovante);
	}

	@SuppressWarnings("deprecation")
	public void abrirModal(int index) {
		comprovante = null;
		indexSelecionado = index;
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('uploadDialog').show()");
	}

	public String mostrarNomeComprovante(Mensalidade mensalidade) {
		return mensalidadeService.getNomeComprovante(mensalidade.getId());
	}

	public void downloadComprovante(Mensalidade mensalidade) {
		InputStream stream = new ByteArrayInputStream(mensalidadeService.getComprovante(mensalidade.getId()));
		this.comprovanteDownload = new DefaultStreamedContent(stream, "application/pdf", mensalidade.getNomeComprovante());
	}

	public String mostrarImagemPagamento(String op) {
		if (op.equals("Pago")) {
			return IMG_CHECK_TRUE;
		} else {
			return IMG_CHECK_FALSE;
		}
	}

	public List<Mensalidade> getListaMensalidades() {
		return listaMensalidades;
	}

	public void setListaMensalidades(List<Mensalidade> listaMensalidades) {
		this.listaMensalidades = listaMensalidades;
	}

	public UploadedFile getComprovante() {
		return comprovante;
	}

	public void setComprovante(UploadedFile comprovante) {
		this.comprovante = comprovante;
	}

	public StreamedContent getComprovanteDownload() {
		return comprovanteDownload;
	}

	public void setComprovanteDownload(StreamedContent comprovanteDownload) {
		this.comprovanteDownload = comprovanteDownload;
	}

}
