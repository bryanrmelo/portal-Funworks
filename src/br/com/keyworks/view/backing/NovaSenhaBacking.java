package br.com.keyworks.view.backing;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import br.com.keyworks.exceptions.AlteracaoConcluidaException;
import br.com.keyworks.exceptions.AlteracaoFalhouException;
import br.com.keyworks.exceptions.NaoValidoException;
import br.com.keyworks.exceptions.SenhaInvalidaException;
import br.com.keyworks.exceptions.SenhaTamanhoInvalidoException;
import br.com.keyworks.exceptions.SenhasNaoCoincidemException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.services.SenhaService;
import br.com.keyworks.util.FacesMessageUtils;

@Named("novaSenha")
@ViewScoped
public class NovaSenhaBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private SenhaService senhaService;

	private String hash;

	private String senha;

	private String confirmarSenha;

	public void alterar() {
		try {
			senhaService.alterar(hash, senha, confirmarSenha);
		} catch (UsuarioNaoEncontradoException | PersistenceException | NullPointerException | NaoValidoException e) {
			FacesMessageUtils.addErrorMessage("Erro ao buscar usuário!");
		} catch (AlteracaoFalhouException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		} catch (AlteracaoConcluidaException e) {
			FacesMessageUtils.addInfoMessage(e.getMessage());
		} catch (SenhaInvalidaException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		} catch (SenhasNaoCoincidemException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		} catch (SenhaTamanhoInvalidoException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		}
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
