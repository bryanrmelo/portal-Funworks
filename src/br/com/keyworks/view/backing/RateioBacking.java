package br.com.keyworks.view.backing;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.keyworks.business.RateioBean;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Rateio;
import br.com.keyworks.view.componentes.GridLazyLoader;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.IGridLazyLoader;
import br.com.keyworks.view.componentes.PagedResult;

@Named("listaRateioBack")
@ViewScoped
public class RateioBacking extends AbstractBacking {

	@Inject
	private RateioBean iRateio;

	private GridLazyLoader<Rateio> gridLazyLoader;

	private static final long serialVersionUID = 1L;

	@PostConstruct
	private void init() {

		gridLazyLoader = new GridLazyLoader<Rateio>(new IGridLazyLoader<Rateio>() {

			@Override
			public PagedResult<Rateio> load(GridLazyLoaderDTO gridLazyLoaderDTO) {
				return carregaRateios(gridLazyLoaderDTO);
			}
		});
	}

	private PagedResult<Rateio> carregaRateios(GridLazyLoaderDTO gridLazyLoaderDTO) {
		PagedResult<Rateio> pagedResult = iRateio.buscaTodosRateiosPaginados(gridLazyLoaderDTO);
		return pagedResult;
	}

	public GridLazyLoader<Rateio> rateios() {
		return gridLazyLoader;
	}

	public String novo() {
		return null;
	}

	public String listagem() {
		return null;
	}

	public void exclui(ActionEvent event) {

	}

}
