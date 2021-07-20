package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trocarsenha")
// @SequenceGenerator(name = "TrocarSenhaSeq", sequenceName = "TROCARSENHA_SEQ", allocationSize = 1)
public class RecuperacaoSenha implements Serializable {

	private static final long serialVersionUID = 1L;

	public RecuperacaoSenha() {
	}

	public RecuperacaoSenha(String login, String hash) {
		this.login = login;
		// this.hash = StringToMD5Converter.convertStringToMd5(RandomStringGenerator.getAlphaNumericString(5));
		// this.hash = StringToMD5Converter.convertStringToMd5(login);
		this.hash = hash;
		this.ativo = true;
		this.dataCriacao = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "login", nullable = false)
	private String login;

	@NotNull
	@Column(name = "hash", nullable = false)
	private String hash;

	@NotNull
	@Column(name = "ativo", nullable = false)
	private boolean ativo;

	@Column(name = "dataCriacao")
	private Date dataCriacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "RecuperacaoSenha [login=" + login + ", hash=" + hash + ", ativo=" + ativo + ", dataCriacao=" + dataCriacao + "]";
	}

}
