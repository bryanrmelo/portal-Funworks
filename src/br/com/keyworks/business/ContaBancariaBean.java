package br.com.keyworks.business;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.ContaBancaria;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.PagedResult;

@Stateless
public class ContaBancariaBean {

	@Inject
	@DataRepository
	private EntityManagerExtended em;

	public ContaBancaria buscaPorId(Long id) {

		ContaBancaria contaBancaria = em.find(ContaBancaria.class, id);

		return contaBancaria;
	}

	public void remove(ContaBancaria contaBancaria) {
		em.remove(contaBancaria.getId(), ContaBancaria.class);
	}

	public void salva(ContaBancaria contaBancaria) {

		if (contaBancaria != null) {
			if (contaBancaria.getId() != null) {
				em.merge(contaBancaria);
			} else {
				em.persist(contaBancaria);
			}
		}
	}

	public ContaBancaria buscarContaBancariaDescricaoAtiva(String descricao, boolean ativo) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("descricao", descricao);
		parametros.put("ativo", ativo);

		return em.findFirstWithNamedQuery(ContaBancaria.POR_DESCRICAO_ATIVA, parametros, ContaBancaria.class);
	}

	public PagedResult<ContaBancaria> buscarContasBancarias(GridLazyLoaderDTO gridLazyLoaderDTO) {

		Map<String, Object> parameters = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder queryString = new StringBuilder("SELECT e FROM ContaBancaria e  ");
		queryString.append(" WHERE 1 = 1");

		if (parameters.containsKey("empresa")) {
			queryString.append(" AND e.empresa = :empresa ");
		}
		if (parameters.containsKey("descricao")) {
			queryString.append(" AND UPPER(e.descricao) like UPPER(:descricao)");
			gridLazyLoaderDTO.getFilters().put("descricao", "%" + parameters.get("descricao") + "%");
		}

		if (parameters.containsKey("ativa")) {
			queryString.append(" AND e.ativa = :ativa");
			gridLazyLoaderDTO.getFilters().put("ativa", true);
		}

		queryString.append(" ORDER BY e.descricao");

		return em.findPageWithQuery(queryString.toString(), gridLazyLoaderDTO.getFilters(), ContaBancaria.class, gridLazyLoaderDTO.getFirst(),
						gridLazyLoaderDTO.getPageSize());

	}

}
