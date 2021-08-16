package br.com.keyworks.repository;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.enumeracoes.SimNaoEnum;
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

	public List<Mensalidade> buscarMensalidades() {
		String jpql = "SELECT m FROM Mensalidade m";
		return em.createQuery(jpql, Mensalidade.class).getResultList();
	}

	public Mensalidade buscarMensalidade(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE id = :id";
		return em.createQuery(jpql, Mensalidade.class).setParameter("id", id).getSingleResult();
	}

	public void salvar(Mensalidade mensalidade) {
		em.persist(mensalidade);
	}

	public Mensalidade atualizar(Mensalidade mensalidade) {
		return em.merge(mensalidade);
	}

	public List<Mensalidade> buscarMensalidadesComFiltro(Map<String, Object> filtrosSelecionados) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT m FROM Mensalidade m INNER JOIN Usuario u ON m.idusuario = u.id");

		if (filtrosSelecionados.size() > 0) {
			query.append(" WHERE 1 = 1");

			if (filtrosSelecionados.get("pagamento") != null) {
				query.append(" AND pagamento = :pagamento");
			}

			if (filtrosSelecionados.get("comprovante") != null) {
				if (filtrosSelecionados.get("comprovante").equals(SimNaoEnum.SIM)) {
					query.append(" AND comprovante IS NOT NULL");
				} else {
					query.append(" AND comprovante IS NULL");
				}
				filtrosSelecionados.remove("comprovante");
			}
			if (filtrosSelecionados.get("associado") != null) {
				query.append(" AND u.nome = :associado");
			}
		}
		return em.findWithQuery(query.toString(), filtrosSelecionados, Mensalidade.class);
	}

}
