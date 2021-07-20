package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_rateio")
@SequenceGenerator(name = "TipoRateioSeq", sequenceName = "TIPO_RATEIO_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = TipoRateio.BUSCA_TODOS_TIPO_RATEIOS, query = "select e from TipoRateio e ") })
public class TipoRateio implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_TODOS_TIPO_RATEIOS = "todos.tipoRateio";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TipoRateioSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;

	@OneToMany(mappedBy = "tipoRateio", fetch = FetchType.EAGER)
	private List<Rateio> rateio;

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

	public List<Rateio> getRateio() {
		return rateio;
	}

	public void setRateio(List<Rateio> rateio) {
		this.rateio = rateio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		TipoRateio other = (TipoRateio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoRateio [id=" + id + ", descricao=" + descricao + ", rateio=" + rateio + "]";
	}

}
