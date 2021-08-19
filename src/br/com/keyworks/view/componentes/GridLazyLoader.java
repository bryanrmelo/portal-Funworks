package br.com.keyworks.view.componentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class GridLazyLoader<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	private final IGridLazyLoader<T> iGridLazyLoader;

	public GridLazyLoader(IGridLazyLoader<T> iGridLazyLoader) {
		super();
		this.iGridLazyLoader = iGridLazyLoader;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		if (null == filters) {
			filters = new HashMap<>();
		}
		PagedResult<T> pagedResult = iGridLazyLoader
						.load(new GridLazyLoaderDTO(new Integer(first), new Integer(pageSize), sortField, sortOrder, filters));

		this.setRowCount(pagedResult.getTotalSize());

		return pagedResult.getPage();
	}

}
