package br.com.keyworks.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import br.com.keyworks.enumeracoes.AnoEnum;
import br.com.keyworks.enumeracoes.MesEnum;
import br.com.keyworks.enumeracoes.PagoPendenteEnum;
import br.com.keyworks.enumeracoes.SimNaoEnum;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.util.ConverterNomeUtil;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.PagedResult;

@Stateless
public class MensalidadeRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	@Inject
	private UsuarioRepository usuarioRepo;

	public List<Mensalidade> buscarMensalidadesPorIdUsuario(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE idUsuario = :idUsuario ORDER BY id ASC";
		return em.createQuery(jpql, Mensalidade.class).setParameter("idUsuario", id).getResultList();
	}

	public Mensalidade buscarMensalidadePorId(Integer id) {
		String jpql = "SELECT m FROM Mensalidade m WHERE id = :id";
		return em.createQuery(jpql, Mensalidade.class).setParameter("id", id).getSingleResult();
	}

	public List<Mensalidade> buscarMensalidades() {
		String jpql = "SELECT m FROM Mensalidade m";
		return em.createQuery(jpql, Mensalidade.class).getResultList();
	}

	@Transactional
	public void salvar(Mensalidade mensalidade) {
		em.persist(mensalidade);
	}

	@Transactional
	public Mensalidade atualizar(Mensalidade mensalidade) {
		return em.merge(mensalidade);
	}

	@SuppressWarnings("unchecked")
	public PagedResult<Mensalidade> buscarMensalidades(GridLazyLoaderDTO gridLazyLoaderDTO) {

		Map<String, Object> parametros = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder query = new StringBuilder("SELECT m FROM Mensalidade m");
		if (parametros.size() > 0) {
			query.append(" WHERE 1 = 1");

			if (parametros.get("pagamento") != null) {
				PagoPendenteEnum e = (PagoPendenteEnum) parametros.get("pagamento");
				parametros.remove("pagamento");
				parametros.put("pagamento", e.getId());
				query.append(" AND pagamento = :pagamento");
			}

			if (parametros.get("comprovante") != null) {
				if (parametros.get("comprovante").equals(SimNaoEnum.SIM)) {
					query.append(" AND comprovante IS NOT NULL");
				} else {
					query.append(" AND comprovante IS NULL");
				}
				parametros.remove("comprovante");
			}
			if (parametros.get("associado") != null) {
				// Caso o input text não seja preenchido o valor vem como "" e não como null, esse if previne que dê
				// erro nos parametros
				if (parametros.get("associado").equals("") || parametros.get("associado").equals("todos")) {
					parametros.remove("associado");
				} else {
					try {
						// Testa caso se o associado definiu um nome, caso não tenha definido será buscado pelo nome
						// de
						// usuario

						parametros.put("associado", usuarioRepo
										.buscarUsuario(ConverterNomeUtil.converterParaLogin((String) parametros.get("associado"))).getLogin());
						query.append(" AND UPPER(m.usuario.login) = UPPER(:associado)");

					} catch (NullPointerException | UsuarioNaoEncontradoException e) {
						query.append(" AND UPPER(m.usuario.nome) = UPPER(:associado)");
					} catch (ArrayIndexOutOfBoundsException e) {
						String valor = (String) parametros.get("associado");
						query.append(" AND UPPER(m.usuario.login) LIKE UPPER(:associado)");
						parametros.put("associado", "%" + valor + "%");

					}
				}
			}
			if (parametros.get("meses") != null) {

				ArrayList<MesEnum> meses = (ArrayList<MesEnum>) parametros.get("meses");

				if (meses.size() >= 1) {
					query.append(" AND  ( ");

					for (int i = 0; i < meses.size(); i++) {
						MesEnum mes = meses.get(i);

						if (i == 0) {
							query.append(" MONTH(m.dataVencimento) = " + mes.getId());
						} else {
							query.append(" OR MONTH(m.dataVencimento) = " + mes.getId());
						}
					}
					query.append(" ) ");

				}
				parametros.remove("meses");
			}

			if (parametros.get("anos") != null) {

				ArrayList<AnoEnum> anos = (ArrayList<AnoEnum>) parametros.get("anos");

				if (anos.size() >= 1) {

					query.append(" AND  ( ");
					for (int i = 0; i < anos.size(); i++) {
						AnoEnum ano = anos.get(i);

						if (i == 0) {
							query.append(" YEAR(m.dataVencimento) = " + ano.getId());
						} else {
							query.append(" OR YEAR(m.dataVencimento) = " + ano.getId());
						}
					}
					query.append(" ) ");
				}
				parametros.remove("anos");
			}

		}

		query.append(" ORDER BY m.usuario.login ASC, m.dataVencimento ASC");

		return em.findPageWithQuery(query.toString(), gridLazyLoaderDTO.getFilters(), Mensalidade.class, gridLazyLoaderDTO.getFirst(),
						gridLazyLoaderDTO.getPageSize());

	}
}
