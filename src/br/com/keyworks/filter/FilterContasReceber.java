package br.com.keyworks.filter;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import br.com.keyworks.dto.FilterContasReceberDTO;

@SessionScoped
public class FilterContasReceber implements Serializable {

	private static final long serialVersionUID = 1L;

	private FilterContasReceberDTO filter;

	public FilterContasReceber() {
		filter = new FilterContasReceberDTO();
	}

	public void limpaFiltro() {
		filter = new FilterContasReceberDTO();
	}

	public FilterContasReceberDTO getFilter() {
		return filter;
	}

	public void setFilter(FilterContasReceberDTO filter) {
		this.filter = filter;
	}

}
