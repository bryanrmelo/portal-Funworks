package br.com.keyworks.view.componentes;

public interface IGridLazyLoader<T> {

	PagedResult<T> load(GridLazyLoaderDTO dto);

}