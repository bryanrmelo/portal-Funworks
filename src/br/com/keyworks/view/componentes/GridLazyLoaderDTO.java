package br.com.keyworks.view.componentes;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.primefaces.model.SortOrder;

public class GridLazyLoaderDTO {

	private Integer first;
	private Integer pageSize;
	private String sortField;
	private SortOrder sortOrder;
	private Map<String, Object> filters;
	private String rawSortClause;

	public GridLazyLoaderDTO() {
		super();
	}

	public GridLazyLoaderDTO(Integer first, Integer pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		super();
		this.first = first;
		this.pageSize = pageSize;
		this.sortField = sortField;
		this.sortOrder = sortOrder;
		this.filters = filters;
	}

	public GridLazyLoaderDTO(Integer first, Integer pageSize, Map<String, Object> filters) {
		super();
		this.first = first;
		this.pageSize = pageSize;
		this.filters = filters;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	public String getRawSortClause() {
		return rawSortClause;
	}

	public void setRawSortClause(String rawSortClause) {
		this.rawSortClause = rawSortClause;
	}

	public Map<String, Object> getFilteredFilters() {

		Iterator<Entry<String, Object>> iterator = filters.entrySet().iterator();

		Entry<String, Object> actual = null;

		while (iterator.hasNext()) {

			actual = iterator.next();

			if (null == actual.getValue()) {
				iterator.remove();
			} else {
				if (actual.getValue().getClass().isAssignableFrom(String.class) && actual.getValue().toString().isEmpty()) {
					iterator.remove();
				}
			}

		}

		return filters;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filters == null) ? 0 : filters.hashCode());
		result = prime * result + first;
		result = prime * result + pageSize;
		result = prime * result + ((sortField == null) ? 0 : sortField.hashCode());
		result = prime * result + ((sortOrder == null) ? 0 : sortOrder.hashCode());
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
		GridLazyLoaderDTO other = (GridLazyLoaderDTO) obj;
		if (filters == null) {
			if (other.filters != null)
				return false;
		} else
			if (!filters.equals(other.filters))
				return false;
		if (first != other.first)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (sortField == null) {
			if (other.sortField != null)
				return false;
		} else
			if (!sortField.equals(other.sortField))
				return false;
		if (sortOrder != other.sortOrder)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GridLazyLoaderDTO [first=" + first + ", pageSize=" + pageSize + ", sortField=" + sortField + ", sortOrder=" + sortOrder
						+ ", filters=" + filters + "]";
	}


}
