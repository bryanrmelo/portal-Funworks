package br.com.keyworks.business;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.ContaReceber;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;

@Stateless
public class ContasReceberBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	public void remove(ContaReceber contaReceber) {

		ContaReceber contaReceberResult = entityManager.find(ContaReceber.class, contaReceber.getId());
		if (contaReceberResult != null) {
			contaReceberResult.getConta();
			entityManager.remove(contaReceberResult);
		}
	}

	public void salvar(ContaReceber contaReceber) {
		if (contaReceber.getId() == null) {
			entityManager.persist(contaReceber);
		} else {
			entityManager.merge(contaReceber);
		}

	}

	public List<ContaReceber> buscaTodosSemPaginacao(GridLazyLoaderDTO gridLazyLoaderDTO) {

		Map<String, Object> parameters = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder queryString = new StringBuilder("SELECT e FROM ContaReceber e  ");
		queryString.append(" WHERE 1 = 1");

		if (parameters.containsKey("dataPrevistaInicio")) {
			queryString.append(" AND e.dataRecebimento >= :dataPrevistaInicio ");
		}
		if (parameters.containsKey("dataPrevistaFinal")) {
			queryString.append(" AND e.dataRecebimento <= :dataPrevistaFinal ");
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

		queryString.append(" ORDER BY e.dataRecebimento asc");
		List<ContaReceber> listaAux = entityManager.findWithQuery(queryString.toString(), parameters, ContaReceber.class);
		return listaAux;
	}

	public ContaReceber buscaPorId(Integer id) {

		ContaReceber contaReceber = entityManager.find(ContaReceber.class, id);
		contaReceber.getConta().getDescricao();
		return contaReceber;

	}

}
