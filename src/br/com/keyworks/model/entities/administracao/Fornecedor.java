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
@Table(name = "fornecedor")
@SequenceGenerator(name = "FornecedorSeq", sequenceName = "FORNECEDOR_SEQ", allocationSize = 1)
@NamedQueries({
				@NamedQuery(name = Fornecedor.BUSCA_COM_DESCRICAO_CONTENDO, query = "select e from Fornecedor e where UPPER(e.descricao) like :descricao and e.ativa = :ativa"),
				@NamedQuery(name = Fornecedor.BUSCA_TODOS_FORNECEDORES, query = "select e from Fornecedor e order by e.descricao"),
				@NamedQuery(name = Fornecedor.BUSCA_TODOS_FORNECEDORES_ATIVOS, query = "select e from Fornecedor e WHERE e.ativa=true order by e.descricao"),
				@NamedQuery(name = Fornecedor.COUNT_COM_DESCRICAO_CONTENDO, query = "select count(e) from Fornecedor e where UPPER(e.descricao) like :descricao and e.ativa = :ativa") })
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_COM_DESCRICAO_CONTENDO = "fornecedor.todos.com.descricao.contendo";
	public static final String COUNT_COM_DESCRICAO_CONTENDO = "fornecedor.count.com.descricao.contendo";
	public static final String BUSCA_TODOS_FORNECEDORES = "todos.fornecedor";
	public static final String BUSCA_TODOS_FORNECEDORES_ATIVOS = "todas.fornecedor.ativo";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FornecedorSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;

	@Column(name = "ativa", nullable = false)
	private boolean ativa;

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

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativa ? 1231 : 1237);
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
		Fornecedor other = (Fornecedor) obj;
		if (ativa != other.ativa)
			return false;
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
		return "Fornecedor [id=" + id + ", descricao=" + descricao + ", ativa=" + ativa + "]";
	}

}
