package br.com.keyworks.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.ContaPagar;
import br.com.keyworks.model.entities.administracao.ContaPagarRateio;
import br.com.keyworks.model.entities.administracao.RateioEmpresa;

@Stateless
public class ContaPagarRateioBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	@SuppressWarnings("unchecked")
	public List<RateioEmpresa> buscaTodosRateiosPorContaPagar(ContaPagar conta) {

		Query query = entityManager.createNamedQuery(ContaPagarRateio.BUSCA_TODOS_RATEIOS_POR_CONTA_A_PAGAR);
		query.setParameter("contapagar", conta.getId());
		return query.getResultList();

	}
}
