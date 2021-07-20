package br.com.keyworks.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.com.keyworks.model.entities.administracao.Empresa;

@Stateless
public class EmpresaBean {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Empresa> buscaTodasEmpresasAtivas() {

		Query q = em.createNamedQuery(Empresa.BUSCA_TODAS_EMPRESAS_ATIVA);
		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Empresa> buscaComDescricaoContendo(String descricao, boolean ativa, Integer firstResult, Integer maxResults) {

		Query q = em.createNamedQuery(Empresa.BUSCA_COM_DESCRICAO_CONTENDO);

		if (firstResult != null && maxResults != null) {
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResults);
		}

		q.setParameter("descricao", "%" + descricao + "%");
		q.setParameter("ativa", ativa);

		return q.getResultList();

	}

	public List<Empresa> buscaComDescricaoContendo(String descricao, boolean ativa) {
		return this.buscaComDescricaoContendo(descricao, ativa, null, null);
	}

	public Long contaComDescricaoContendo(String descricao, boolean ativa, Integer firstResult, Integer maxResults) {

		Query q = em.createNamedQuery(Empresa.COUNT_COM_DESCRICAO_CONTENDO);

		if (firstResult != null && maxResults != null) {
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResults);
		}

		q.setParameter("descricao", "%" + descricao + "%");
		q.setParameter("ativa", ativa);

		return (long) q.getSingleResult();

	}

	public Long buscarTodasEmpresas(Integer firstResult, Integer maxResults) {
		Query q = em.createNamedQuery(Empresa.BUSCA_TODAS_EMPRESAS);
		return (long) q.getResultList().size();
	}

	public Long contaComDescricaoContendo(String descricao, boolean ativa) {
		return this.contaComDescricaoContendo(descricao, ativa, null, null);
	}

	public Long buscarTodasEmpresas() {
		return this.buscarTodasEmpresas(null, null);
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> buscaTodasEmpresas() {

		Query q = em.createNamedQuery(Empresa.BUSCA_TODAS_EMPRESAS);
		return q.getResultList();

	}

	public Empresa buscaPorId(Integer id) {
		return this.em.find(Empresa.class, id);
	}

}
