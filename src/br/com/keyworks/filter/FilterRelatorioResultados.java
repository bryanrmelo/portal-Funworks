package br.com.keyworks.filter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import br.com.keyworks.dto.FilterRelatorioResultadosDTO;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.view.backing.RelatorioResultadosBacking;

@SessionScoped
public class FilterRelatorioResultados implements Serializable {

	private static final long serialVersionUID = 1L;

	private FilterRelatorioResultadosDTO filter;

	public FilterRelatorioResultados() {
		filter = new FilterRelatorioResultadosDTO();
	}

	public void limpaFiltro() {
		filter = new FilterRelatorioResultadosDTO();
	}

	public FilterRelatorioResultadosDTO getFilter() {
		return filter;
	}

	public void setFilter(FilterRelatorioResultadosDTO filter) {
		this.filter = filter;
	}

}
