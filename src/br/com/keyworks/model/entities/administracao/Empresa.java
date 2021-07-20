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
import javax.persistence.Transient;

@Entity
@Table(name = "empresa")
@SequenceGenerator(name = "EmpresaSeq", sequenceName = "EMPRESA_SEQ", allocationSize = 1)
@NamedQueries({
				@NamedQuery(name = Empresa.BUSCA_COM_DESCRICAO_CONTENDO, query = "SELECT e FROM Empresa e WHERE UPPER(e.descricao) LIKE :descricao AND e.ativa = :ativa ORDER BY e.descricao"),
				@NamedQuery(name = Empresa.BUSCA_TODAS_EMPRESAS, query = "select e from Empresa e order by e.descricao "),
				@NamedQuery(name = Empresa.BUSCA_TODAS_EMPRESAS_ATIVA, query = "select e from Empresa e WHERE e.ativa=true order by e.descricao "),
				@NamedQuery(name = Empresa.COUNT_COM_DESCRICAO_CONTENDO, query = "select count(e) from Empresa e where UPPER(e.descricao) like :descricao and e.ativa = :ativa") })
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_COM_DESCRICAO_CONTENDO = "empresa.todos.com.descricao.contendo";
	public static final String COUNT_COM_DESCRICAO_CONTENDO = "empresa.count.com.descricao.contendo";
	public static final String BUSCA_TODAS_EMPRESAS = "todas.empresas";
	public static final String BUSCA_TODAS_EMPRESAS_ATIVA = "todas.empresas.ativas";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmpresaSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;

	@Column(name = "ativa", nullable = false)
	private boolean ativa;

	@Column(name = "sacado", nullable = false)
	private boolean sacado;

	@Transient
	boolean checkBoxRelValue;

	public boolean isCheckBoxRelValue() {
		return checkBoxRelValue;
	}

	public void setCheckBoxRelValue(boolean checkBoxRelValue) {
		this.checkBoxRelValue = checkBoxRelValue;
	}

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

	public boolean isSacado() {
		return sacado;
	}

	public void setSacado(boolean sacado) {
		this.sacado = sacado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativa ? 1231 : 1237);
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (sacado ? 1231 : 1237);
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
		Empresa other = (Empresa) obj;
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
		if (sacado != other.sacado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", descricao=" + descricao + ", ativa=" + ativa + ", sacado=" + sacado + "]";
	}

}
