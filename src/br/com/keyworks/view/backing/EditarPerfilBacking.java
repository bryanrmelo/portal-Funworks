package br.com.keyworks.view.backing;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.UploadedFile;
import br.com.keyworks.exceptions.ObservacaoInvalidaException;
import br.com.keyworks.exceptions.QuantidadeInvalidaException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.services.UsuarioService;
import br.com.keyworks.util.FacesMessageUtils;

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
		try {
			usuario.setImage(imagem.getContents());
			usuarioService.editar(usuario);
		} catch (QuantidadeInvalidaException e) {
			FacesMessageUtils.addErrorMessage("Quantidade precisa ser preenchida!");
		} catch (ObservacaoInvalidaException e) {
			FacesMessageUtils.addErrorMessage("Observação precisa ser preenchida!");
		} catch (UsuarioNaoEncontradoException e) {
			System.out.println("Usuario não encontrado");
		}

	}

	public String getImagemAtual(Usuario usuario) {
		byte[] imagem = usuario.getImage();
		System.out.println(imagem);
		if (imagem != null) {
			String imageString = new String(Base64.encodeBase64(imagem));
			// String imageString = new String(Base64.getEncoder().encodeToString(imagem));
			return imageString;
		} else {
			return null;
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
