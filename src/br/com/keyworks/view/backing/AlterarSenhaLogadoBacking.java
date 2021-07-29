package br.com.keyworks.view.backing;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import br.com.keyworks.exceptions.AlteracaoConcluidaException;
import br.com.keyworks.exceptions.SenhaErradaException;
import br.com.keyworks.exceptions.SenhaInvalidaException;
import br.com.keyworks.exceptions.SenhaTamanhoInvalidoException;
import br.com.keyworks.exceptions.SenhasNaoCoincidemException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.services.SenhaService;
import br.com.keyworks.services.UsuarioService;
import br.com.keyworks.util.ExpiracaoUtil;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.util.LogoutUtil;

@Named("alterarSenhaLogado")
@ViewScoped
public class AlterarSenhaLogadoBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private IdentidadeSessao sessao;

	@Inject
	private SenhaService senhaService;

	@Inject
	private UsuarioService usuarioService;

	private String nome;

	private String senhaAtual;

	private String novaSenha;

	private String confirmarSenha;

	@PostConstruct
	public void ini() {
		this.nome = sessao.getNome();
		if (!ExpiracaoUtil.ValidaExpiracao(sessao.getDataCriacao())) {
			FacesMessageUtils.addErrorMessage("Sessão expirada!");
			try {
				LogoutUtil.logout();
			} catch (Exception e) {

			}
		}
	}

	public String nomeParcial() {
		return usuarioService.gerenciarNomeParaView("parcial", nome);
	}

	public String nomeCompleto() {
		return usuarioService.gerenciarNomeParaView("completo", nome);
	}

	public void alterar() {
		try {
			senhaService.alterar(nome, senhaAtual, novaSenha, confirmarSenha);
		} catch (SenhaErradaException e) {
			FacesMessageUtils.addErrorMessage("Senha atual errada!");
		} catch (UsuarioNaoEncontradoException | PersistenceException e) {
			FacesMessageUtils.addErrorMessage("Erro ao procurar usuário!");
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
