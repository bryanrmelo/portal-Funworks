package br.com.keyworks.view.backing;

import java.io.IOException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import br.com.keyworks.business.LoginBean;
import br.com.keyworks.exceptions.LoginFalhouException;
import br.com.keyworks.exceptions.SenhaErradaException;
import br.com.keyworks.exceptions.SenhaInvalidaException;
import br.com.keyworks.exceptions.SenhaTamanhoInvalidoException;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.services.SenhaService;
import br.com.keyworks.util.FacesMessageUtils;

@Named("login")
@ViewScoped
public class LoginBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoginBean loginBean;

	@Inject
	private SenhaService senhaService;

	@Inject
	private IdentidadeSessao idSessao;

	private String nome;

	private String senha;

	// private StringToMD5Converter converterMD5;

	@PostConstruct
	private void init() {
	}

	// public String login() {
	//
	// try {
	// request.logout();
	// request.login(nome, "autenticado");
	// return "/content/secure/home.xhtml";
	// } catch (ServletException ex) {
	// ex.printStackTrace();
	// return null;
	// }
	// }

	public String login() {
		try {

			if (senhaService.validar(senha)) {

				if (checarSenha(nome, senha)) {
					FacesContext context = FacesContext.getCurrentInstance();
					HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
					try {
						request.logout();
					} catch (ServletException e1) {
						e1.printStackTrace();
					}
					HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					try {
						if (!autenticacao()) {
							request.logout();
							session.invalidate();
							return "/public/login.xhtml?faces-redirect=true";
						}
					} catch (ServletException e) {
						e.printStackTrace();
					}
					idSessao.setNome(nome);
					idSessao.setDataCriacao(new Date());
					return "/secure/home.xhtml?faces-redirect=true";
				} else {
					System.out.println("Login falhou!!");
					return "/public/login.xhtml?faces-redirect=true";
				}
			} else {
				throw new LoginFalhouException();
			}

		} catch (SenhaInvalidaException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
			return null;
		} catch (SenhaErradaException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
			return null;
		} catch (SenhaTamanhoInvalidoException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
			return null;
		} catch (LoginFalhouException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
			return null;
		}

	}

	private boolean checarSenha(String nome, String senha) throws SenhaErradaException {
		return senhaService.checarSenha(nome, senha);
	}

	private boolean autenticacao() throws ServletException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

		if (session.getAttribute("login") == null) {

			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

			request.login(nome, "autenticado");
			session.setAttribute("login", nome);

			return true;
		} else {

			return true;
		}
	}

	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("login", null);
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		try {
			session.invalidate();
			request.logout();
			FacesContext.getCurrentInstance().getExternalContext().redirect("../public/login");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public String trocarSenha() {
		return "/public/recuperarSenha.xhtml?faces-redirect=true";
	}

	public String getUserLogado() {
		return loginBean.getPrincipal().getName();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
