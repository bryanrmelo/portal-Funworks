package br.com.keyworks.business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.TipoRateio;

@Stateless
public class TipoRateioBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	@SuppressWarnings("unchecked")
	public List<TipoRateio> buscaTodosTiposRateios() {

		Query query = entityManager.createNamedQuery(TipoRateio.BUSCA_TODOS_TIPO_RATEIOS);
		return new ArrayList<TipoRateio>(query.getResultList());

	}

	public TipoRateio buscaPorId(Integer id) {
		TipoRateio tipoRateio = entityManager.find(TipoRateio.class, id);
		return tipoRateio;

	}

}
