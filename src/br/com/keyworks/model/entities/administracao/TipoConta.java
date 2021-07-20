package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_conta")
@SequenceGenerator(name = "TipoContaSeq", sequenceName = "TIPO_CONTA_SEQ", allocationSize = 1)
@NamedQueries({
				@NamedQuery(name = TipoConta.BUSCA_COM_DESCRICAO, query = "select e from TipoConta e where UPPER(e.descricao) like :descricao "),
				@NamedQuery(name = TipoConta.BUSCA_TODOS_TIPO_CONTAS, query = "select e from TipoConta e "),
				@NamedQuery(name = TipoConta.COUNT_COM_DESCRICAO, query = "select count(e) from TipoConta e where UPPER(e.descricao) like :descricao") })
public class TipoConta implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_COM_DESCRICAO = "tipoConta.todos.com.descricao";
	public static final String COUNT_COM_DESCRICAO = "tipoConta.count.com.descricao";
	public static final String BUSCA_TODOS_TIPO_CONTAS = "todos.tipoConta";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TipoContaSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TipoConta other = (TipoConta) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "TipoConta [id=" + id + ", descricao=" + descricao + "]";
	}

}
