package br.com.keyworks.repository;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.PagedResult;

@Stateless
public class MensalidadeRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	public Mensalidade getMensalidadeById(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE id = :id";
		return em.createQuery(jpql, Mensalidade.class).setParameter("id", id).getSingleResult();
	}

	public List<Mensalidade> getMensalidadesByIdUsuario(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE idUsuario = :idUsuario ORDER BY id ASC";
		return em.createQuery(jpql, Mensalidade.class).setParameter("idUsuario", id).getResultList();
	}

	public List<Mensalidade> getMensalidades() {
		String jpql = "SELECT m FROM Mensalidade m";
		return em.createQuery(jpql, Mensalidade.class).getResultList();
	}

	public PagedResult<Mensalidade> getMensalidadesLazy(GridLazyLoaderDTO gridLazyLoaderDTO, String query) {
		return em.findPageWithQuery(query.toString(), gridLazyLoaderDTO.getFilters(), Mensalidade.class, gridLazyLoaderDTO.getFirst(),
						gridLazyLoaderDTO.getPageSize());
	}

	@SuppressWarnings("unchecked")
	public List<String> getAnos() {
		String jpql = "SELECT DISTINCT YEAR(m.dataVencimento) FROM Mensalidade m ORDER BY YEAR(m.dataVencimento) ASC";
		return em.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> getMeses() {
		String jpql = "SELECT DISTINCT MONTH(m.dataVencimento) FROM Mensalidade m ORDER BY MONTH(m.dataVencimento) ASC";
		return em.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Date> getDatas() {
		String jpql = "SELECT DISTINCT m.dataVencimento FROM Mensalidade m ORDER BY m.dataVencimento ASC";
		return em.createQuery(jpql).getResultList();
	}

	@Transactional
	public void persist(Mensalidade mensalidade) {
		em.persist(mensalidade);
	}

	@Transactional
	public Mensalidade merge(Mensalidade mensalidade) {
		return em.merge(mensalidade);
	}

	@Transactional
	public void persistList(List<Mensalidade> mensalidades) {
		for (Mensalidade m : mensalidades) {
			em.persist(m);
		}

	}

}
