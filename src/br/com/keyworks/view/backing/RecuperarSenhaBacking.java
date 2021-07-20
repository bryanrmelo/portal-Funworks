package br.com.keyworks.view.backing;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import org.apache.commons.mail.EmailException;
import br.com.keyworks.exceptions.NaoValidoException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.services.SenhaService;
import br.com.keyworks.util.FacesMessageUtils;

@Named("recuperarSenha")
@ViewScoped
public class RecuperarSenhaBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private SenhaService senhaService;

	private String login;

	private String senha;

	public void recuperar() {
		FacesMessageUtils.addInfoMessage(
						"Estamos validando suas informações. Em poucos instantes você receberá uma mensagem no e-mail cadastrado com as instruções de recuperação de senha ");
		try {
			senhaService.recuperar(login);
		} catch (EmailException e) {
			FacesMessageUtils.addErrorMessage("Não foi possível enviar o e-mail!");
		} catch (UsuarioNaoEncontradoException | NullPointerException | PersistenceException | NaoValidoException e) {
			FacesMessageUtils.addErrorMessage("Não foi possível encontrar o usuário!");
		}

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
