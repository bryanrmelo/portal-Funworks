package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import br.com.keyworks.dto.RelatorioDTO;

@Entity
@Table(name = "conta_pagar")
@SequenceGenerator(name = "ContaPagarSeq", sequenceName = "CONTA_PAGAR_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = ContaPagar.BUSCA_TODAS_CONTAS_A_PAGAR, query = "SELECT e FROM ContaPagar e ") })
@SqlResultSetMappings(value = { @SqlResultSetMapping(name = ContaPagar.RESULT_MAP_CUSTOMER_RELATORIO, classes = { @ConstructorResult(targetClass = RelatorioDTO.class, columns = {
				@ColumnResult(name = "data", type = String.class), @ColumnResult(name = "valor", type = BigDecimal.class),
				@ColumnResult(name = "contaId", type = Integer.class), @ColumnResult(name = "contaDescricao", type = String.class),
				@ColumnResult(name = "dataPrevista", type = Date.class), @ColumnResult(name = "idPai", type = Integer.class),
				@ColumnResult(name = "descricaoPai", type = String.class), @ColumnResult(name = "empresaId", type = Integer.class)}) }) })
public class ContaPagar implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String RESULT_MAP_CUSTOMER_RELATORIO = "contas.pagar.relatorio";

	public static final String BUSCA_TODAS_CONTAS_A_PAGAR = "todos.conta_pagar";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContaPagarSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private Fornecedor fornecedor;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;

	@Column(name = "data_prevista", nullable = false)
	private Date dataPrevista;

	@NotNull
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;

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

	@Column(name = "qtd_replica_meses")
	private Integer qtdReplicaMeses;

	@NotNull
	@Column(name = "replica_meses", nullable = false)
	private boolean replicaMeses;

	@Transient
	private String mesAnoDataVencimento;

	// @Column(name = "data_replica_periodo_inicial")
	// private Date dataReplicaPeriodoInicial;
	//
	// @Column(name = "data_replica_periodo_final")
	// private Date dataReplicaPeriodoFinal;

	@OneToOne(cascade = CascadeType.ALL)
	private ContaPagarRateio contaPagarRateio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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

	public boolean isReplicaMeses() {
		return replicaMeses;
	}

	public ContaPagarRateio getContaPagarRateio() {
		return contaPagarRateio;
	}

	public void setContaPagarRateio(ContaPagarRateio contaPagarRateio) {
		this.contaPagarRateio = contaPagarRateio;
	}

	public void setReplicaMeses(boolean replicaMeses) {
		this.replicaMeses = replicaMeses;
	}

	public Integer getQtdReplicaMeses() {
		return qtdReplicaMeses;
	}

	public void setQtdReplicaMeses(Integer qtdReplicaMeses) {
		this.qtdReplicaMeses = qtdReplicaMeses;
	}

	public String getMesAnoDataVencimento() {
		return mesAnoDataVencimento;
	}

	public void setMesAnoDataVencimento(String mesAnoDataVencimento) {
		this.mesAnoDataVencimento = mesAnoDataVencimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((contaPagarRateio == null) ? 0 : contaPagarRateio.hashCode());
		result = prime * result + ((dataPrevista == null) ? 0 : dataPrevista.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mesAnoDataVencimento == null) ? 0 : mesAnoDataVencimento.hashCode());
		result = prime * result + ((qtdReplicaMeses == null) ? 0 : qtdReplicaMeses.hashCode());
		result = prime * result + (replicaMeses ? 1231 : 1237);
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
		ContaPagar other = (ContaPagar) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else
			if (!conta.equals(other.conta))
				return false;
		if (contaPagarRateio == null) {
			if (other.contaPagarRateio != null)
				return false;
		} else
			if (!contaPagarRateio.equals(other.contaPagarRateio))
				return false;
		if (dataPrevista == null) {
			if (other.dataPrevista != null)
				return false;
		} else
			if (!dataPrevista.equals(other.dataPrevista))
				return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else
			if (!dataVencimento.equals(other.dataVencimento))
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
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else
			if (!fornecedor.equals(other.fornecedor))
				return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else
			if (!id.equals(other.id))
				return false;
		if (mesAnoDataVencimento == null) {
			if (other.mesAnoDataVencimento != null)
				return false;
		} else
			if (!mesAnoDataVencimento.equals(other.mesAnoDataVencimento))
				return false;
		if (qtdReplicaMeses == null) {
			if (other.qtdReplicaMeses != null)
				return false;
		} else
			if (!qtdReplicaMeses.equals(other.qtdReplicaMeses))
				return false;
		if (replicaMeses != other.replicaMeses)
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
		return "ContaPagar [id=" + id + ", fornecedor=" + fornecedor + ", empresa=" + empresa + ", dataPrevista=" + dataPrevista
						+ ", dataVencimento=" + dataVencimento + ", descricao=" + descricao + ", conta=" + conta + ", valor=" + valor
						+ ", qtdReplicaMeses=" + qtdReplicaMeses + ", replicaMeses=" + replicaMeses + ", mesAnoDataVencimento="
						+ mesAnoDataVencimento + ", contaPagarRateio=" + contaPagarRateio + "]";
	}

}
