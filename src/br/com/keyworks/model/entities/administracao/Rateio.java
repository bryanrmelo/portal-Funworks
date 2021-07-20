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
@Table(name = "rateio")
@SequenceGenerator(name = "RateioSeq", sequenceName = "RATEIO_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = Rateio.BUSCA_TODOS_RATEIOS, query = "SELECT e FROM Rateio e ") })
public class Rateio implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_TODOS_RATEIOS = "todos.rateio";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RateioSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "tipo_rateio")
	private TipoRateio tipoRateio;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@Column(name = "percentual")
	private Double percentual;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoRateio getTipoRateio() {
		return tipoRateio;
	}

	public void setTipoRateio(TipoRateio tipoRateio) {
		this.tipoRateio = tipoRateio;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((percentual == null) ? 0 : percentual.hashCode());
		result = prime * result + ((tipoRateio == null) ? 0 : tipoRateio.hashCode());
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
		Rateio other = (Rateio) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else
			if (!empresa.equals(other.empresa))
				return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else
			if (!id.equals(other.id))
				return false;
		if (percentual == null) {
			if (other.percentual != null)
				return false;
		} else
			if (!percentual.equals(other.percentual))
				return false;
		if (tipoRateio == null) {
			if (other.tipoRateio != null)
				return false;
		} else
			if (!tipoRateio.equals(other.tipoRateio))
				return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rateio [id=" + id + ", tipoRateio=" + tipoRateio + ", empresa=" + empresa + ", percentual=" + percentual + "]";
	}

}
