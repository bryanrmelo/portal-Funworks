package br.com.keyworks.view.backing;

import static br.com.keyworks.constants.Constants.IMG_CHECK_FALSE;
import static br.com.keyworks.constants.Constants.IMG_CHECK_TRUE;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

	private UploadedFile imagem;

	private List<Mensalidade> listaMensalidades;

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

	public String mostrarImagemPagamento(String op) {
		if (op.equals("Pago")) {
			return IMG_CHECK_TRUE;
		} else {
			return IMG_CHECK_FALSE;
		}
	}

	public void salvarObservacao(Integer id, String observacao) {
		mensalidadeService.salvarObservacao(id, observacao);
	}

	public List<Mensalidade> getListaMensalidades() {
		return listaMensalidades;
	}

	public void setListaMensalidades(List<Mensalidade> listaMensalidades) {
		this.listaMensalidades = listaMensalidades;
	}

	public UploadedFile getImagem() {
		return imagem;
	}

	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}

}
