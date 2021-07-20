package br.com.keyworks.filter;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import br.com.keyworks.dto.FilterContaBancariaDTO;

@SessionScoped
public class FilterContaBancaria implements Serializable {

	private static final long serialVersionUID = 1L;

	private FilterContaBancariaDTO filter;

	public FilterContaBancaria() {
		filter = new FilterContaBancariaDTO();
	}

	public void limpaFiltro() {
		filter = new FilterContaBancariaDTO();
	}

	public FilterContaBancariaDTO getFilter() {
		return filter;
	}

	public void setFilter(FilterContaBancariaDTO filter) {
		this.filter = filter;
	}

}
