package br.com.keyworks.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaPagar;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.model.entities.administracao.Fornecedor;
import br.com.keyworks.view.componentes.GridLazyLoader;

public class FilterContasPagarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataPrevistaInicio;
	private Date dataPrevistaFinal;
	private Date dataVencimentoInicio;
	private Date dataVencimentoFim;
	private Fornecedor fornecedor;
	private Empresa empresa;
	private Conta conta;
	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	private String descricao;
	private GridLazyLoader<ContaPagar> gridLazyLoader;
	private List<ContaPagarVencimentoDTO> listaFilter;

	public Date getDataPrevistaInicio() {
		return dataPrevistaInicio;
	}

	public void setDataPrevistaInicio(Date dataPrevistaInicio) {
		this.dataPrevistaInicio = dataPrevistaInicio;
	}

	public Date getDataPrevistaFinal() {
		return dataPrevistaFinal;
	}

	public void setDataPrevistaFinal(Date dataPrevistaFinal) {
		this.dataPrevistaFinal = dataPrevistaFinal;
	}

	public Date getDataVencimentoInicio() {
		return dataVencimentoInicio;
	}

	public void setDataVencimentoInicio(Date dataVencimentoInicio) {
		this.dataVencimentoInicio = dataVencimentoInicio;
	}

	public Date getDataVencimentoFim() {
		return dataVencimentoFim;
	}

	public void setDataVencimentoFim(Date dataVencimentoFim) {
		this.dataVencimentoFim = dataVencimentoFim;
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

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GridLazyLoader<ContaPagar> getGridLazyLoader() {
		return gridLazyLoader;
	}

	public void setGridLazyLoader(GridLazyLoader<ContaPagar> gridLazyLoader) {
		this.gridLazyLoader = gridLazyLoader;
	}

	public List<ContaPagarVencimentoDTO> getListaFilter() {
		return listaFilter;
	}

	public void setListaFilter(List<ContaPagarVencimentoDTO> listaFilter) {
		this.listaFilter = listaFilter;
	}

}
