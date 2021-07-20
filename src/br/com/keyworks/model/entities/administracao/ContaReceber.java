package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "conta_receber")
@SequenceGenerator(name = "ContaReceberSeq", sequenceName = "CONTA_RECEBER_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = ContaReceber.BUSCA_TODAS_CONTAS_A_RECEBER, query = "SELECT e FROM ContaReceber e ") })
public class ContaReceber implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_TODAS_CONTAS_A_RECEBER = "todos.conta_receber";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContaReceberSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;

	@Column(name = "data_recebimento", nullable = false)
	private Date dataRecebimento;

	@NotNull
	@Column(name = "descricao", nullable = false)
	private String descricao;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "conta_id", nullable = false)
	private Conta conta;

	@NotNull
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@Transient
	private String mesAnoDataRecebimento;

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

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getMesAnoDataRecebimento() {
		return mesAnoDataRecebimento;
	}

	public void setMesAnoDataRecebimento(String mesAnoDataRecebimento) {
		this.mesAnoDataRecebimento = mesAnoDataRecebimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataRecebimento == null) ? 0 : dataRecebimento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		ContaReceber other = (ContaReceber) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else
			if (!conta.equals(other.conta))
				return false;
		if (dataRecebimento == null) {
			if (other.dataRecebimento != null)
				return false;
		} else
			if (!dataRecebimento.equals(other.dataRecebimento))
				return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else
			if (!descricao.equals(other.descricao))
				return false;
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
		return "ContaReceber [id=" + id + ", empresa=" + empresa + ", dataRecebimento=" + dataRecebimento + ", descricao=" + descricao + ", conta="
						+ conta + ", valor=" + valor + "]";
	}

}
