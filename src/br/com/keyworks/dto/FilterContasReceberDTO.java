package br.com.keyworks.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaReceber;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.view.componentes.GridLazyLoader;

public class FilterContasReceberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataRecebimentoInicio;
	private Date dataRecebimentoFinal;
	private Empresa empresa;
	private Conta conta;
	private String descricao;
	private GridLazyLoader<ContaReceber> gridLazyLoader;
	private List<ContaReceberVencimentoDTO> listaFilter;

	public Date getDataRecebimentoInicio() {
		return dataRecebimentoInicio;
	}

	public void setDataRecebimentoInicio(Date dataRecebimentoInicio) {
		this.dataRecebimentoInicio = dataRecebimentoInicio;
	}

	public Date getDataRecebimentoFinal() {
		return dataRecebimentoFinal;
	}

	public void setDataRecebimentoFinal(Date dataRecebimentoFinal) {
		this.dataRecebimentoFinal = dataRecebimentoFinal;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GridLazyLoader<ContaReceber> getGridLazyLoader() {
		return gridLazyLoader;
	}

	public void setGridLazyLoader(GridLazyLoader<ContaReceber> gridLazyLoader) {
		this.gridLazyLoader = gridLazyLoader;
	}

	public List<ContaReceberVencimentoDTO> getListaFilter() {
		return listaFilter;
	}

	public void setListaFilter(List<ContaReceberVencimentoDTO> listaFilter) {
		this.listaFilter = listaFilter;
	}

}
