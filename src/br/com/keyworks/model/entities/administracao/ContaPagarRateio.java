package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "conta_pagar_rateio")
@SequenceGenerator(name = "ContaPagarRateioSeq", sequenceName = "CONTA_PAGAR_RATEIO_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = ContaPagarRateio.BUSCA_TODOS_RATEIOS_POR_CONTA_A_PAGAR, query = "SELECT e FROM ContaPagarRateio e WHERE e.contaPagar.id = :contapagar") })
public class ContaPagarRateio implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String BUSCA_TODOS_RATEIOS_POR_CONTA_A_PAGAR = "todos.rateios.por.conta.a.pagar";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContaPagarRateioSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@Column(name = "tipo")
	private Integer TipoRateio;

	@Column(name = "rateio_pre_configurado")
	private boolean rateioPreSelecionado;

	@ManyToOne
	@JoinColumn(name = "rateio_pre_configurado_id")
	private TipoRateio rateioPreSelecionadoId;
	//
	// @OneToMany(mappedBy = "contaPagarRateio", cascade = { CascadeType.ALL }, orphanRemoval = true)
	// private List<RateioEmpresa> rateioEmpresa;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "conta_pagar_rateio_empresa", joinColumns = { @JoinColumn(name = "conta_pagar_rateio_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "rateio_empresa_id", referencedColumnName = "id") })
	private List<RateioEmpresa> rateioEmpresa;

	@OneToOne(mappedBy = "contaPagarRateio")
	private ContaPagar contaPagar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipoRateio() {
		return TipoRateio;
	}

	public void setTipoRateio(Integer tipoRateio) {
		TipoRateio = tipoRateio;
	}

	public boolean isRateioPreSelecionado() {
		return rateioPreSelecionado;
	}

	public void setRateioPreSelecionado(boolean rateioPreSelecionado) {
		this.rateioPreSelecionado = rateioPreSelecionado;
	}

	public TipoRateio getRateioPreSelecionadoId() {
		return rateioPreSelecionadoId;
	}

	public void setRateioPreSelecionadoId(TipoRateio rateioPreSelecionadoId) {
		this.rateioPreSelecionadoId = rateioPreSelecionadoId;
	}

	public List<RateioEmpresa> getRateioEmpresa() {
		return rateioEmpresa;
	}

	public void setRateioEmpresa(List<RateioEmpresa> rateioEmpresa) {
		this.rateioEmpresa = rateioEmpresa;
	}

}
