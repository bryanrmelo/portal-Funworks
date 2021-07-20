package br.com.keyworks.filter;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import br.com.keyworks.dto.FilterContasPagarDTO;

@SessionScoped
public class FilterContasPagar implements Serializable {

	private static final long serialVersionUID = 1L;

	private FilterContasPagarDTO filter;

	public FilterContasPagar() {
		filter = new FilterContasPagarDTO();
	}

	public void limpaFiltro() {
		filter = new FilterContasPagarDTO();
	}

	public FilterContasPagarDTO getFilter() {
		return filter;
	}

	public void setFilter(FilterContasPagarDTO filter) {
		this.filter = filter;
	}

}
