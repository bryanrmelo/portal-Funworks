package br.com.keyworks.dto;

import java.io.Serializable;
import java.util.List;
import br.com.keyworks.model.entities.administracao.ContaBancaria;
import br.com.keyworks.model.entities.administracao.Empresa;

public class FilterContaBancariaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private Empresa empresa;
	private List<ContaBancaria> listaFilter;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<ContaBancaria> getListaFilter() {
		return listaFilter;
	}

	public void setListaFilter(List<ContaBancaria> listaFilter) {
		this.listaFilter = listaFilter;
	}

}
