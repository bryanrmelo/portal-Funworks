package br.com.keyworks.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Mensalidade;

@Stateless
public class MensalidadeRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	public List<Mensalidade> buscarMensalidade(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE idUsuario = :idUsuario";
		return em.createQuery(jpql, Mensalidade.class).setParameter("idUsuario", id).getResultList();
	}

}
