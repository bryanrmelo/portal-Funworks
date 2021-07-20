package br.com.keyworks.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.ContaPagarRateio;
import br.com.keyworks.model.entities.administracao.RateioEmpresa;

@Stateless
public class RateioEmpresaBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	@SuppressWarnings("unchecked")
	public List<RateioEmpresa> buscaTodosRateiosEmpresaPorContaPagaraRateio(ContaPagarRateio contaPagarRateio) {

		Query query = entityManager.createNamedQuery(RateioEmpresa.BUSCA_TODOS_RATEIOS_EMPRESA_POR_CONTA_A_PAGAR_RATEIO);
		query.setParameter("contaPagarRateio", contaPagarRateio);
		return query.getResultList();

	}
}
