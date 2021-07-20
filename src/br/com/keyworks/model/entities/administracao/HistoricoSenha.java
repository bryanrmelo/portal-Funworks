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
@Table(name = "logtrocarsenhas")
// @SequenceGenerator(name = "TrocarSenhaSeq", sequenceName = "TROCARSENHA_SEQ", allocationSize = 1)
public class HistoricoSenha implements Serializable {

	private static final long serialVersionUID = 1L;

	public HistoricoSenha() {
	}

	public HistoricoSenha(String login) {
		this.login = login;
		this.dataAlteracao = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "login", nullable = false)
	private String login;

	@NotNull
	@Column(name = "dataAlteracao", nullable = false)
	private Date dataAlteracao;

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

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public String toString() {
		return "HistoricoSenha [id=" + id + ", login=" + login + ", dataAlteracao=" + dataAlteracao + "]";
	}

}