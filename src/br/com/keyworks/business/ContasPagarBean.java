package br.com.keyworks.business;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.ContaPagar;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.PagedResult;

@Stateless
public class ContasPagarBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	public PagedResult<ContaPagar> buscaTodosPaginados(GridLazyLoaderDTO gridLazyLoaderDTO) {

		Map<String, Object> parameters = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder queryString = new StringBuilder("SELECT e FROM ContaPagar e  ");
		queryString.append(" WHERE 1 = 1");

		if (parameters.containsKey("dataPrevistaInicio")) {
			queryString.append(" AND e.dataPrevista >= :dataPrevistaInicio ");
		}
		if (parameters.containsKey("dataPrevistaFinal")) {
			queryString.append(" AND e.dataPrevista <= :dataPrevistaFinal ");
		}
		if (parameters.containsKey("dataVencimentoInicio")) {
			queryString.append(" AND e.dataVencimento >= :dataVencimentoInicio ");
		}
		if (parameters.containsKey("dataVencimentoFinal")) {
			queryString.append(" AND e.dataVencimento <= :dataVencimentoFinal ");
		}
		if (parameters.containsKey("fornecedor")) {
			queryString.append(" AND e.fornecedor = :fornecedor ");
		}
		if (parameters.containsKey("empresa")) {
			queryString.append(" AND e.empresa = :empresa ");
		}
		if (parameters.containsKey("descricao")) {
			queryString.append(" AND UPPER(e.descricao) like UPPER(:descricao)");
			gridLazyLoaderDTO.getFilters().put("descricao", "%" + parameters.get("descricao") + "%");
		}
		if (parameters.containsKey("conta")) {
			queryString.append(" AND e.conta = :conta ");
		}
		if (parameters.containsKey("valorInicial") && !parameters.containsKey("valorFinal")) {
			queryString.append(" AND e.valor = :valorInicial ");
		} else {
			if (parameters.containsKey("valorInicial") && parameters.containsKey("valorFinal")) {
				queryString.append(" AND e.valor >= :valorInicial AND e.valor <= :valorFinal");
			} else {
				if (!parameters.containsKey("valorInicial") && parameters.containsKey("valorFinal")) {
					queryString.append(" AND e.valor = :valorFinal ");
				}
			}
		}

		queryString.append(" ORDER BY e.dataPrevista,e.dataVencimento");

		PagedResult<ContaPagar> pa = entityManager.findPageWithQuery(queryString.toString(), gridLazyLoaderDTO.getFilters(), ContaPagar.class,
						gridLazyLoaderDTO.getFirst(), gridLazyLoaderDTO.getPageSize());

		return pa;

	}

	public ContaPagar buscaPorId(Integer id) {

		ContaPagar contaPagar = entityManager.find(ContaPagar.class, id);
		contaPagar.getContaPagarRateio().getRateioEmpresa().size();

		return contaPagar;

	}

	public void remove(ContaPagar contaPagar) {

		ContaPagar contaPagarResult = entityManager.find(ContaPagar.class, contaPagar.getId());
		if (contaPagarResult != null) {
			contaPagarResult.getContaPagarRateio().getRateioEmpresa().size();
			entityManager.remove(contaPagarResult);
		}
	}

	public void salva(ContaPagar contaPagar) {

		if (contaPagar != null) {
			if (contaPagar.getId() != null) {
				entityManager.merge(contaPagar);
			} else {
				entityManager.persist(contaPagar);
			}
		}
	}

	public void merge(ContaPagar contaPagar) {
		entityManager.merge(contaPagar);
	}

	public List<ContaPagar> buscaTodosSemPaginacao(GridLazyLoaderDTO gridLazyLoaderDTO) {

		Map<String, Object> parameters = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder queryString = new StringBuilder("SELECT e FROM ContaPagar e  ");
		queryString.append(" WHERE 1 = 1");

		if (parameters.containsKey("dataPrevistaInicio")) {
			queryString.append(" AND e.dataPrevista >= :dataPrevistaInicio ");
		}
		if (parameters.containsKey("dataPrevistaFinal")) {
			queryString.append(" AND e.dataPrevista <= :dataPrevistaFinal ");
		}
		if (parameters.containsKey("dataVencimentoInicio")) {
			queryString.append(" AND e.dataVencimento >= :dataVencimentoInicio ");
		}
		if (parameters.containsKey("dataVencimentoFinal")) {
			queryString.append(" AND e.dataVencimento <= :dataVencimentoFinal ");
		}
		if (parameters.containsKey("fornecedor")) {
			queryString.append(" AND e.fornecedor = :fornecedor ");
		}
		if (parameters.containsKey("empresa")) {
			queryString.append(" AND e.empresa = :empresa ");
		}
		if (parameters.containsKey("descricao")) {
			queryString.append(" AND UPPER(e.descricao) like UPPER(:descricao)");
			parameters.put("descricao", "%" + parameters.get("descricao") + "%");
		}
		if (parameters.containsKey("conta")) {
			queryString.append(" AND e.conta = :conta ");
		}
		if (parameters.containsKey("valorInicial") && !parameters.containsKey("valorFinal")) {
			queryString.append(" AND e.valor = :valorInicial ");
		} else {
			if (parameters.containsKey("valorInicial") && parameters.containsKey("valorFinal")) {
				queryString.append(" AND e.valor >= :valorInicial AND e.valor <= :valorFinal");
			} else {
				if (!parameters.containsKey("valorInicial") && parameters.containsKey("valorFinal")) {
					queryString.append(" AND e.valor = :valorFinal ");
				}
			}
		}

		queryString.append(" ORDER BY e.dataVencimento asc");
		List<ContaPagar> listaAux = entityManager.findWithQuery(queryString.toString(), parameters, ContaPagar.class);
		return listaAux;
	}
}
