package br.com.keyworks.view.backing;

import java.nio.ByteBuffer;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.UploadedFile;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.services.UsuarioService;

@Named("editar")
@ViewScoped
public class EditarPerfilBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	private String login;

	private Usuario usuario;

	private UploadedFile imagem;

	@Inject
	private IdentidadeSessao sessao;

	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	public void ini() {
		this.login = sessao.getNome();
		usuario = usuarioService.getDadosExistentes(login);
	}

	public void editarPerfil() {
		// Em ordem
		// Testa se a imagem presente no banco é maior que 0
		// ou se a imagem no banco é diferente da imagem nova
		// e se a imagem nova possui algum conteúdo
		if (usuario.getImage().length == 0 || usuario.getImage() != imagem.getContents() && imagem.getContents().length > 0) {
			usuario.setImage(imagem.getContents());
		}
		usuarioService.editar(usuario);

	}

	public String getImagemAtual() {
		if (usuario.getImage() != null) {
			return new String(Base64.encodeBase64(usuario.getImage()));
		} else {
			byte[] bytes = ByteBuffer.allocate(4).putInt(463792186).array();
			return new String(Base64.encodeBase64(bytes));
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UploadedFile getImagem() {
		return imagem;
	}

	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}

}
