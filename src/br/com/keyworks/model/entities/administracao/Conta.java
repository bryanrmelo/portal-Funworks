package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "conta")
@SequenceGenerator(name = "ContaSeq", sequenceName = "CONTA_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = Conta.BUSCA_COM_DESCRICAO, query = "select e from Conta e where UPPER(e.descricao) like :descricao "),
				@NamedQuery(name = Conta.BUSCA_TODAS_CONTAS, query = "select e from Conta e "),
				@NamedQuery(name = Conta.COUNT_COM_DESCRICAO, query = "select count(e) from Conta e where UPPER(e.descricao) like :descricao"),
				@NamedQuery(name = Conta.BUSCA_CONTAS_POR_TIPO, query = "select e from Conta e where UPPER(e.tipo.descricao) like :tipo") })
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_COM_DESCRICAO = "conta.todos.com.descricao";
	public static final String COUNT_COM_DESCRICAO = "conta.count.com.descricao";
	public static final String BUSCA_TODAS_CONTAS = "todos.conta";
	public static final String BUSCA_CONTAS_POR_TIPO = "conta.por.tipo";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContaSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "tipo")
	private TipoConta tipo;

	@ManyToOne
	@JoinColumn(name = "pai")
	private Conta pai;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public Conta getPai() {
		return pai;
	}

	public void setPai(Conta pai) {
		this.pai = pai;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pai == null) ? 0 : pai.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else
			if (!descricao.equals(other.descricao))
				return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else
			if (!id.equals(other.id))
				return false;
		if (pai == null) {
			if (other.pai != null)
				return false;
		} else
			if (!pai.equals(other.pai))
				return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else
			if (!tipo.equals(other.tipo))
				return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + ", pai=" + pai + "]";
	}

}
