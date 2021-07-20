package br.com.keyworks.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.com.keyworks.model.entities.administracao.Fornecedor;

@Stateless
public class FornecedorBean {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Fornecedor> buscaTododFornecedoresAtivos() {

		Query q = em.createNamedQuery(Fornecedor.BUSCA_TODOS_FORNECEDORES_ATIVOS);

		return q.getResultList();

	}

}
