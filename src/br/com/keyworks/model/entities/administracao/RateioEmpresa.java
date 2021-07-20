package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rateio_empresa")
@SequenceGenerator(name = "rateioEmpresaSeq", sequenceName = "RATEIO_EMPRESA_SEQ", allocationSize = 1)
// @NamedQueries({ @NamedQuery(name = RateioEmpresa.BUSCA_TODOS_RATEIOS_EMPRESA_POR_CONTA_A_PAGAR_RATEIO, query =
// "SELECT e FROM RateioEmpresa e WHERE e.contaPagarRateio= :contaPagarRateio") })
public class RateioEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String BUSCA_TODOS_RATEIOS_EMPRESA_POR_CONTA_A_PAGAR_RATEIO = "todos.rateios_empresa.por.conta.a.pagar_rateio";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rateioEmpresaSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	// @ManyToOne
	// @JoinColumn(name = "conta_pagar_rateio_id")
	// private ContaPagarRateio contaPagarRateio;

	@JoinColumn(name = "valor")
	private BigDecimal valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		RateioEmpresa other = (RateioEmpresa) obj;
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
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else
			if (!valor.equals(other.valor))
				return false;
		return true;
	}

	@Override
	public String toString() {
		return "RateioEmpresa [id=" + id + ", empresa=" + empresa + ", valor=" + valor + "]";
	}

}
