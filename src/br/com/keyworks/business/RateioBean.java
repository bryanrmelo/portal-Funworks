package br.com.keyworks.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Rateio;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.PagedResult;

@Stateless
public class RateioBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	public PagedResult<Rateio> buscaTodosRateiosPaginados(GridLazyLoaderDTO gridLazyLoaderDTO) {

		return entityManager.findPageWithNativeQuery(Rateio.BUSCA_TODOS_RATEIOS, Rateio.class, 1, 10);

	}

	@SuppressWarnings("unchecked")
	public List<Rateio> buscaTodosRateios() {

		Query query = entityManager.createNamedQuery(Rateio.BUSCA_TODOS_RATEIOS);
		return query.getResultList();

	}

	public Rateio buscaPorId(Integer id) {
		Rateio rateio = entityManager.find(Rateio.class, id);
		return rateio;

	}

}
