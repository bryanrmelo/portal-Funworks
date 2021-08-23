package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "UsuarioSeq", sequenceName = "USUARIO_SEQ", allocationSize = 1)

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "login", nullable = false)
	private String login;

	@NotNull
	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "tipo")
	private String tipo;

	@NotNull
	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "celular", nullable = false)
	private String celular;

	@NotNull
	@Column(name = "whatsapp", nullable = false)
	private String whatsapp;

	@Lob
	@Column(name = "imagem")
	private byte[] image;

	@NotNull
	@Column(name = "endereco", nullable = false)
	private String endereco;

	@NotNull
	@Column(name = "nascimento", nullable = false)
	private String nascimento;

	@Column(name = "estadoCivil")
	private String estadoCivil;

	@NotNull
	@Column(name = "admissao", nullable = false)
	private String admissao;

	@Column(name = "genero")
	private String genero;

	@Column(name = "dependentes")
	private String dependentes;

	@Column(name = "qtdDependentes")
	private Integer qtdDependentes;

	@Column(name = "animais")
	private String animais;

	@Column(name = "qtdAnimais")
	private Integer qtdAnimais;

	@Column(name = "obsAnimais")
	private String obsAnimais;

	@Column(name = "orientacaoAlimentar")
	private String orientacaoAlimentar;

	@Column(name = "obsOrientacaoAlimentar")
	private String obsOrientacaoAlimentar;

	@Column(name = "alergias")
	private String alergias;

	@Column(name = "obsAlergias")
	private String obsAlergias;

	@Column(name = "intolerancias")
	private String intolerancias;

	@Column(name = "obsIntolerancias")
	private String obsIntolerancias;

	@Column(name = "preferencias")
	private String preferencias;

	@Column(name = "dicas")
	private String dicas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public String getObsAlergias() {
		return obsAlergias;
	}

	public void setObsAlergias(String obsAlergias) {
		this.obsAlergias = obsAlergias;
	}

	public String getIntolerancias() {
		return intolerancias;
	}

	public void setIntolerancias(String intolerancias) {
		this.intolerancias = intolerancias;
	}

	public String getObsIntolerancias() {
		return obsIntolerancias;
	}

	public void setObsIntolerancias(String obsIntolerancias) {
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", nome=" + nome + ", email=" + email + ", celular=" + celular
						+ ", whatsapp=" + whatsapp + ", endereco=" + endereco + ", nascimento=" + nascimento + ", estadoCivil=" + estadoCivil
						+ ", admissao=" + admissao + ", genero=" + genero + ", dependentes=" + dependentes + ", qtdDependentes=" + qtdDependentes
						+ ", animais=" + animais + ", qtdAnimais=" + qtdAnimais + ", obsAnimais=" + obsAnimais + ", alergias=" + alergias
						+ ", obsAlergias=" + obsAlergias + ", intolerancias=" + intolerancias + ", obsIntolerancias=" + obsIntolerancias
						+ ", preferencias=" + preferencias + ", dicas=" + dicas + "]";
	}

}
