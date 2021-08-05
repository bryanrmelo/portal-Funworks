package br.com.keyworks.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Mensalidade;

@Stateless
public class MensalidadeRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	public List<Mensalidade> buscarMensalidadesPorId(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE idUsuario = :idUsuario ORDER BY id ASC";
		return em.createQuery(jpql, Mensalidade.class).setParameter("idUsuario", id).getResultList();
	}

	public Mensalidade buscarMensalidade(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE id = :id";
		return em.createQuery(jpql, Mensalidade.class).setParameter("id", id).getSingleResult();
	}

	@Transactional
	public void salvar(Mensalidade mensalidade) {
		em.persist(mensalidade);
	}

	@Transactional
	public void atualizar(Mensalidade mensalidade) {
		em.merge(mensalidade);
	}

}
