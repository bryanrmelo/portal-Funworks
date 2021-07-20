package br.com.keyworks.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.Empresa;

public class FilterRelatorioResultadosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataInicial;
	private Date dataFinal;
	private Conta contaSelecionada;
	private Empresa empresaSelecionada;
	private boolean valoresConsolidados;

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	
	public boolean isValoresConsolidados() {
		return valoresConsolidados;
	}

	public void setValoresConsolidados(boolean valoresConsolidados) {
		this.valoresConsolidados = valoresConsolidados;
	}

}
