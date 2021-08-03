package br.com.keyworks.view.backing;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.services.UsuarioService;
import br.com.keyworks.util.ExpiracaoUtil;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.util.LogoutUtil;

@Named("home")
@ViewScoped
public class HomeBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;

	@Inject
	private IdentidadeSessao sessao;

	@Inject
	private UsuarioService usuarioService;

	private String nome;

	@PostConstruct
	public void ini() {
		this.nome = sessao.getNome();
		if (!ExpiracaoUtil.ValidaExpiracao(sessao.getDataCriacao())) {
			LogoutUtil.logout();
		}
		FacesMessageUtils.addInfoMessage("Seja bem vindo(a) " + nomeParcial()
						+ ", para navegar pelo portal utilize o menu à esquerda, se houverem dúvidas entre em contato com a Funworks de forma presencial ou através do e-mail funworks@keyworks.com.br. Obrigado!");

	}

	public String nomeParcial() {
		return usuarioService.gerenciarNomeParaView("parcial", nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}