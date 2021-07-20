package br.com.keyworks.producer;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.jpa.EntityManagerFacade;
import br.com.keyworks.model.core.qualifier.DataRepository;

@ApplicationScoped
public class DataRepositoryProducer {

	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	@DataRepository
	public EntityManagerExtended getEntityManager() {
		return new EntityManagerFacade(entityManager);
	}

}
