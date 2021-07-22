package br.com.keyworks.view.backing;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

	private String nome;

	private String email;

	private String celular;

	private String whatsapp;

	private String endereco;

	private String nascimento;

	private String estadoCivil;

	private String admissao;

	private String genero;

	private String dependentes;

	private Integer qtdDependentes;

	private String animais;

	private Integer qtdAnimais;

	private String obsAnimais;

	private String orientacaoAlimentar;

	private String obsOrientacaoAlimentar;

	private String alergias;

	private Integer obsAlergias;

	private String intolerancias;

	private Integer obsIntolerancias;

	private String preferencias;

	private String dicas;

	@Inject
	private IdentidadeSessao sessao;

	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	public void ini() {
		this.login = sessao.getNome();
		Usuario usuario = usuarioService.getDadosExistentes(login);
		preencher(usuario);
	}

	private void preencher(Usuario u) {
		this.nome = u.getNome();
		this.email = u.getEmail();
		this.celular = u.getCelular();
		this.whatsapp = u.getWhatsapp();
		this.endereco = u.getEndereco();
		this.nascimento = u.getNascimento();
		this.estadoCivil = u.getEstadoCivil();
		this.admissao = u.getAdmissao();
		this.genero = u.getGenero();
		this.dependentes = u.getDependentes();
		this.qtdDependentes = u.getQtdDependentes();
		this.animais = u.getAnimais();
		this.qtdAnimais = u.getQtdAnimais();
		this.obsAnimais = u.getObsAnimais();
		this.orientacaoAlimentar = u.getOrientacaoAlimentar();
		this.obsOrientacaoAlimentar = u.getObsOrientacaoAlimentar();
		this.alergias = u.getAlergias();
		this.obsAlergias = u.getObsAlergias();
		this.intolerancias = u.getIntolerancias();
		this.obsIntolerancias = u.getObsIntolerancias();
		this.preferencias = u.getPreferencias();
		this.dicas = u.getDicas();

	}

	public void editarPerfil() {
		try {
			usuarioService.editar(login, nome, email, celular, whatsapp, nascimento, estadoCivil, admissao, genero, dependentes, qtdDependentes,
							animais, qtdAnimais, obsAnimais, orientacaoAlimentar, obsOrientacaoAlimentar, alergias, obsAlergias, intolerancias,
							obsIntolerancias, preferencias, dicas);
		} catch (QuantidadeInvalidaException e) {
			FacesMessageUtils.addErrorMessage("Quantidade precisa ser preenchida!");
		} catch (ObservacaoInvalidaException e) {
			FacesMessageUtils.addErrorMessage("Observação precisa ser preenchida!");
		} catch (UsuarioNaoEncontradoException e) {
			System.out.println("Usuario não encontrado");
		}

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getAdmissao() {
		return admissao;
	}

	public void setAdmissao(String admissao) {
		this.admissao = admissao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDependentes() {
		return dependentes;
	}

	public void setDependentes(String dependentes) {
		this.dependentes = dependentes;
	}

	public Integer getQtdDependentes() {
		return qtdDependentes;
	}

	public void setQtdDependentes(Integer qtdDependentes) {
		this.qtdDependentes = qtdDependentes;
	}

	public String getAnimais() {
		return animais;
	}

	public void setAnimais(String animais) {
		this.animais = animais;
	}

	public Integer getQtdAnimais() {
		return qtdAnimais;
	}

	public void setQtdAnimais(Integer qtdAnimais) {
		this.qtdAnimais = qtdAnimais;
	}

	public String getObsAnimais() {
		return obsAnimais;
	}

	public void setObsAnimais(String obsAnimais) {
		this.obsAnimais = obsAnimais;
	}

	public String getOrientacaoAlimentar() {
		return orientacaoAlimentar;
	}

	public void setOrientacaoAlimentar(String orientacaoAlimentar) {
		this.orientacaoAlimentar = orientacaoAlimentar;
	}

	public String getObsOrientacaoAlimentar() {
		return obsOrientacaoAlimentar;
	}

	public void setObsOrientacaoAlimentar(String obsOrientacaoAlimentar) {
		this.obsOrientacaoAlimentar = obsOrientacaoAlimentar;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public Integer getObsAlergias() {
		return obsAlergias;
	}

	public void setObsAlergias(Integer obsAlergias) {
		this.obsAlergias = obsAlergias;
	}

	public String getIntolerancias() {
		return intolerancias;
	}

	public void setIntolerancias(String intolerancias) {
		this.intolerancias = intolerancias;
	}

	public Integer getObsIntolerancias() {
		return obsIntolerancias;
	}

	public void setObsIntolerancias(Integer obsIntolerancias) {
		this.obsIntolerancias = obsIntolerancias;
	}

	public String getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}

	public String getDicas() {
		return dicas;
	}

	public void setDicas(String dicas) {
		this.dicas = dicas;
	}

}
