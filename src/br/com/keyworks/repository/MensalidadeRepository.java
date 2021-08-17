package br.com.keyworks.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.enumeracoes.SimNaoEnum;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.util.ConverterNomeUtil;

@Stateless
public class MensalidadeRepository {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	@Inject
	private UsuarioRepository usuarioRepo;

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

	@SuppressWarnings("unchecked")
	public List<Mensalidade> buscarMensalidadesComFiltro(Map<String, Object> parametros) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT m FROM Mensalidade m");

		if (parametros.size() > 0) {
			query.append(" WHERE 1 = 1");

			if (parametros.get("pagamento") != null) {
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
						if (usuarioRepo.buscarUsuario(ConverterNomeUtil.converterParaLogin((String) parametros.get("associado"))).getNome() != null) {
							query.append(" AND UPPER(m.usuario.nome) = UPPER(:associado)");
						} else {
							query.append(" AND UPPER(m.usuario.login) = UPPER(:associado)");
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (UsuarioNaoEncontradoException e) {
						e.printStackTrace();
					}
					// parametros.put("associado", ConverterNomeUtil.converterParaLogin((String)
					// parametros.get("associado")));
				}
			}
			if (parametros.get("meses") != null) {
				query.append(" AND 1 = 1");
				ArrayList<String> meses = (ArrayList<String>) parametros.get("meses");
				for (String mes : meses) {
					if (mes.equals("Janeiro")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-01-%'");
					}
					if (mes.equals("Fevereiro")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-02-%'");
					}
					if (mes.equals("Março")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-03-%'");
					}
					if (mes.equals("Abril")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-04-%'");
					}
					if (mes.equals("Maio")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-05-%'");
					}
					if (mes.equals("Junho")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-06-%'");
					}
					if (mes.equals("Julho")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-07-%'");
					}
					if (mes.equals("Agosto")) {
						query.append(" OR to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-08-%'");
					}
				}
				// SELECT * FROM mensalidade WHERE to_char(datavencimento, 'YYYY-MM-DD') LIKE '%-07-%';
				parametros.remove("meses");
			}
		}
		List<Mensalidade> lista = em.findWithQuery(query.toString(), parametros, Mensalidade.class);
		System.out.println(lista);
		return lista;
	}

}
