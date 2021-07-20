package br.com.keyworks.business;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.com.keyworks.dto.RelatorioDTO;
import br.com.keyworks.model.core.jpa.EntityManagerExtended;
import br.com.keyworks.model.core.qualifier.DataRepository;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaReceber;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;

@Stateless
public class RelatoriosBean {

	@Inject
	@DataRepository
	private EntityManagerExtended entityManager;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

	public List<RelatorioDTO> buscaDespesasSemPaginacao(GridLazyLoaderDTO gridLazyLoaderDTO, String resultSetMapping) {

		Map<String, Object> parameters = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder queryString = new StringBuilder();
		queryString.append(" SELECT ");
		queryString.append(" to_char(data_prevista, 'MM/YYYY') as data, ");
		queryString.append(" empresa_id as empresaId, ");
		queryString.append(" (SELECT id from conta where id= pai_id) as idPai, ");
		queryString.append(" (SELECT descricao from conta where id= pai_id) as descricaoPai, ");
		queryString.append(" data_prevista as dataPrevista, ");
		queryString.append(" conta_id as contaId, ");
		queryString.append(" conta_desc as contaDescricao, ");
		queryString.append(" sum(valor)  as valor ");
		queryString.append(" FROM( ");
		queryString.append(" SELECT ");
		queryString.append(
						" rt.empresa_id as empresa_id, co.pai as pai_id, c.data_prevista as data_prevista, c.conta_id as conta_id, co.descricao as conta_desc, ");
		queryString.append(" case when cr.tipo=1 then ROUND((c.valor*rt.valor)/100,2) else rt.valor end ");
		queryString.append(" FROM ");
		queryString.append(" conta co, ");
		queryString.append(" conta_pagar c, ");
		queryString.append(" conta_pagar_rateio cr, ");
		queryString.append(" conta_pagar_rateio_empresa ce, ");
		queryString.append(" rateio_empresa rt ");
		queryString.append(" WHERE ");
		queryString.append(" c.conta_id = co.id and ");
		queryString.append(" c.contapagarrateio_id = cr.id and ");
		queryString.append(" ce.conta_pagar_rateio_id = cr.id and ");
		queryString.append(" ce.rateio_empresa_id = rt.id ");

		if (parameters.containsKey("empresa")) {
			Empresa emp = new Empresa();
			emp = (Empresa) parameters.get("empresa");
			queryString.append(" and rt.empresa_id = ");
			queryString.append(emp.getId());
		}

		if (parameters.containsKey("empresasSelecionadas")) {
			List<Empresa> empresas = (List<Empresa>) parameters.get("empresasSelecionadas");
			if (empresas.size() > 0) {
				int i = 0;
				String emp = "";
				for (Empresa empr : empresas) {
					emp += empr.getId();
					i++;
					if (i < empresas.size()) {
						emp += ",";
					}
				}
				queryString.append(" and rt.empresa_id in(");
				queryString.append(emp);
				queryString.append(") ");
			}
			parameters.remove("empresasSelecionadas");
		}

		if (parameters.containsKey("conta")) {
			Conta cta = new Conta();
			cta = (Conta) parameters.get("conta");
			queryString.append(" and c.conta_id = ");
			queryString.append(cta.getId());
		}

		if (parameters.containsKey("dataInicial")) {
			String dt = format.format(parameters.get("dataInicial"));
			queryString.append(" AND c.data_prevista >= ' ");
			queryString.append(dt);
			queryString.append(" ' ");
		}

		if (parameters.containsKey("dataFinal")) {
			String dt = format.format(parameters.get("dataFinal"));
			queryString.append(" AND c.data_prevista <= ' ");
			queryString.append(dt);
			queryString.append(" ' ");
		}

		queryString.append(" ) tabela ");
		queryString.append(" GROUP BY ");
		queryString.append(" data, empresaId, dataPrevista, contaId, contaDescricao, idPai, pai_id");
		queryString.append(" ORDER BY ");
		queryString.append(" dataPrevista, idpai, empresaId");

		List<RelatorioDTO> listaContaPagar = entityManager.findNativeQueryList(queryString.toString(), resultSetMapping, null);

		return listaContaPagar;
	}

	public List<ContaReceber> buscaReceitasSemPaginacao(GridLazyLoaderDTO gridLazyLoaderDTO) {

		Map<String, Object> parameters = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder queryString = new StringBuilder("SELECT e FROM ContaReceber e  ");
		StringBuilder queryWhere = new StringBuilder(" WHERE 1 = 1");

		if (parameters.containsKey("empresa")) {
			queryWhere.append(" AND e.empresa = :empresa ");
		}

		if (parameters.containsKey("empresasSelecionadas")) {
			List<Empresa> empresas = (List<Empresa>) parameters.get("empresasSelecionadas");
			if (empresas.size() > 0) {
				int i = 0;
				String emp = "";
				for (Empresa empr : empresas) {
					emp += empr.getId();
					i++;
					if (i < empresas.size()) {
						emp += ",";
					}
				}
				queryWhere.append(" AND e.empresa in(");
				queryWhere.append(emp);
				queryWhere.append(") ");
			}
			parameters.remove("empresasSelecionadas");
		}

		if (parameters.containsKey("conta")) {
			queryWhere.append(" AND e.conta = :conta ");
		}

		if (parameters.containsKey("dataInicial")) {
			queryWhere.append(" AND e.dataRecebimento >= :dataInicial ");
		}

		if (parameters.containsKey("dataFinal")) {
			queryWhere.append(" AND e.dataRecebimento <= :dataFinal ");
		}

		queryString.append(queryWhere.toString());

		List<ContaReceber> listaReceitas = entityManager.findWithQuery(queryString.toString(), parameters, ContaReceber.class);

		return listaReceitas;

	}
}
