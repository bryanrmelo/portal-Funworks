package br.com.keyworks.view.backing;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.keyworks.enumeracoes.ContaEnum;
import br.com.keyworks.exceptions.SenhaInvalidaException;
import br.com.keyworks.exceptions.SenhaTamanhoInvalidoException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.services.UsuarioService;
import br.com.keyworks.util.FacesMessageUtils;

@Named("perfilAdmBack")
@ViewScoped
public class AdministracaoPerfilBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private IdentidadeSessao sessao;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuario = new Usuario();

	private String nome;

	@SuppressWarnings("unused")
	private List<ContaEnum> tipos;

	@PostConstruct
	public void init() {
		this.setNome(sessao.getNome());
		// if (ExpiracaoUtil.ValidaExpiracao(sessao.getDataCriacao())) {
		// LogoutUtil.logout();
		// }
	}

	public void registrar() {
		try {
			usuarioService.registrar(usuario);
		} catch (SenhaInvalidaException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		} catch (SenhaTamanhoInvalidoException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		} catch (UsuarioNaoEncontradoException e) {
			FacesMessageUtils.addErrorMessage(e.getMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ContaEnum> getTipos() {
		return Arrays.asList(ContaEnum.values());
	}

	public void setTipos(List<ContaEnum> tipos) {
		this.tipos = tipos;
	}
}
