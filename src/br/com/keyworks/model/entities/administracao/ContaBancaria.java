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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "conta_bancaria")
@SequenceGenerator(name = "ContaBancariaSeq", sequenceName = "CONTA_BANCARIA_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = ContaBancaria.POR_DESCRICAO_ATIVA, query = "SELECT e FROM ContaBancaria e WHERE UPPER(e.descricao) = UPPER(:descricao) AND e.ativo = :ativo ") })
public class ContaBancaria implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String POR_DESCRICAO_ATIVA = "conta.pagar.descricao.ativa";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContaBancariaSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Long id;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;

	@NotNull
	@Size(max = 150)
	@Column(name = "descricao", nullable = false, length = 150)
	private String descricao;

	@NotNull
	@Size(max = 50)
	@Column(name = "banco", nullable = false, length = 50)
	private String banco;

	@NotNull
	@Size(max = 50)
	@Column(name = "agencia", nullable = false, length = 50)
	private String agencia;

	@NotNull
	@Size(max = 50)
	@Column(name = "numero_conta", nullable = false, length = 50)
	private String numeroConta;

	@NotNull
	@Column(name = "saldo_inicial", nullable = false)
	private BigDecimal saldoInicial;

	@NotNull
	@Column(name = "saldo_atual", nullable = false)
	private BigDecimal saldoAtual;

	@Column(name = "ativo")
	private boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroConta == null) ? 0 : numeroConta.hashCode());
		result = prime * result + ((saldoAtual == null) ? 0 : saldoAtual.hashCode());
		result = prime * result + ((saldoInicial == null) ? 0 : saldoInicial.hashCode());
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
		ContaBancaria other = (ContaBancaria) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else
			if (!agencia.equals(other.agencia))
				return false;
		if (ativo != other.ativo)
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else
			if (!banco.equals(other.banco))
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
		if (numeroConta == null) {
			if (other.numeroConta != null)
				return false;
		} else
			if (!numeroConta.equals(other.numeroConta))
				return false;
		if (saldoAtual == null) {
			if (other.saldoAtual != null)
				return false;
		} else
			if (!saldoAtual.equals(other.saldoAtual))
				return false;
		if (saldoInicial == null) {
			if (other.saldoInicial != null)
				return false;
		} else
			if (!saldoInicial.equals(other.saldoInicial))
				return false;
		return true;
	}

}
