package br.com.keyworks.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.com.keyworks.model.entities.administracao.Conta;

@Stateless
public class ContaBean {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Conta> buscaTodasContas() {

		Query query = em.createNamedQuery(Conta.BUSCA_TODAS_CONTAS);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Conta> buscaContasPorTipo(String tipo) {

		Query query = em.createNamedQuery(Conta.BUSCA_CONTAS_POR_TIPO);
		query.setParameter("tipo", tipo);
		return query.getResultList();

	}

}
