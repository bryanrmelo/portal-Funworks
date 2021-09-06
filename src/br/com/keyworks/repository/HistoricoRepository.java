package br.com.keyworks.repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.HistoricoSenha;

@Stateless
public class HistoricoRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	@Transactional
	public void persist(HistoricoSenha hs) throws PersistenceException {
		em.persist(hs);
	}

}
