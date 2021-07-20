package br.com.keyworks.model.core.jpa;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import com.google.common.base.Strings;
import br.com.keyworks.view.componentes.PagedResult;

public class QueryExecutor<E> {

	/*
	 * ========================================================================
	 * Enumerações
	 * ========================================================================
	 */

	public enum QueryType {
		Named, Native, Custom
	}

	/*
	 * ========================================================================
	 * Atributos
	 * ========================================================================
	 */

	private EntityManager em;
	private QueryType queryType;
	private String queryName;
	private String sql;
	@SuppressWarnings("rawtypes")
	private Class entityClass;
	private Integer start;
	private Integer limit;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private String resultSetMapping;

	/*
	 * ========================================================================
	 * Construtores
	 * ========================================================================
	 */

	@SuppressWarnings("rawtypes")
	public QueryExecutor(EntityManager em, QueryType queryType, String queryNameOrSQL, Class entityClass, Integer start, Integer limit, Map parameters) {
		this(em, queryType, queryNameOrSQL, entityClass);
		this.start = start;
		this.limit = limit;
		this.parameters = parameters;
	}

	@SuppressWarnings("rawtypes")
	public QueryExecutor(EntityManager em, QueryType queryType, String queryNameOrSQL, Class entityClass) {
		super();
		this.em = em;
		this.queryType = queryType;
		this.entityClass = entityClass;
		if (queryType.equals(QueryType.Named)) {
			this.queryName = queryNameOrSQL;
		} else {
			this.sql = queryNameOrSQL;
		}
	}

	public QueryExecutor(EntityManager em, QueryType queryType, String queryNameOrSQL) {
		this(em, queryType, queryNameOrSQL, null);
	}

	/*
	 * ========================================================================
	 * Getters e Setters
	 * ========================================================================
	 */

	public String getQueryName() {
		return queryName;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@SuppressWarnings("rawtypes")
	public Map getParameters() {
		return parameters;
	}

	@SuppressWarnings("rawtypes")
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public String getResultSetMapping() {
		return resultSetMapping;
	}

	public void setResultSetMapping(String resultSetMapping) {
		this.resultSetMapping = resultSetMapping;
	}

	/*
	 * ========================================================================
	 * Métodos Públicos
	 * ========================================================================
	 */

	@SuppressWarnings({ "unchecked" })
	public List<E> getList() {

		// Cria a query
		Query query = null;
		switch (queryType) {

		case Native:

			if (Strings.isNullOrEmpty(resultSetMapping)) {
				query = em.createNativeQuery(sql, entityClass);
			} else {
				query = em.createNativeQuery(sql, resultSetMapping);
			}

			applyParametersNativeQuery(query);

			break;

		case Named:

			query = em.createNamedQuery(queryName);

			applyParameters(query);

			break;

		case Custom:

			query = em.createQuery(sql);

			applyParameters(query);

			break;

		}

		// Verifica parâmetros de paginação
		if (start != null) {
			query.setFirstResult(start);
		}
		if (limit != null) {
			query.setMaxResults(limit);
		}

		// Consulta
		List<E> entidades = query.getResultList();

		// Retorna
		return entidades;
	}

	public PagedResult<E> getPagedResult() {

		// Cria o resultado
		PagedResult<E> result = new PagedResult<E>();

		// Busca as entidades
		List<E> entidades = getList();

		// Define a página
		result.setPage(entidades);

		// Contagem de registros
		String sqlCount;
		Query query = null;
		switch (queryType) {

		case Native:

			String upperCase = sql.toUpperCase();

			if (upperCase.contains("UNION")) {
				StringBuilder queryCount = new StringBuilder();
				String[] querys = upperCase.split("UNION");

				queryCount.append(" SELECT SUM(RowNumber) FROM ( ");

				for (int i = 0; i < querys.length; i++) {
					String editQuery = querys[i];
					int indexOfFirstFrom = editQuery.indexOf("FROM");

					if (i != 0) {
						queryCount.append(" UNION ");
					}
					editQuery = "SELECT COUNT(*) RowNumber " + editQuery.substring(indexOfFirstFrom);

					queryCount.append(editQuery);
				}
				queryCount.append(" ) ");
				upperCase = queryCount.toString();
			} else {
				int indexOfFirstFrom = upperCase.indexOf("FROM");
				upperCase = "SELECT COUNT(*) " + upperCase.substring(indexOfFirstFrom);
			}

			query = em.createNativeQuery(upperCase);

			applyParametersNativeQuery(query);

			break;

		case Custom:

			String alias = getSelectAlias();
			if (sql.startsWith("SELECT DISTINCT")) {
				sqlCount = sql.replace(selectClauseToReplace().toUpperCase(), "SELECT COUNT(DISTINCT " + alias + ") ");
			} else {
				sqlCount = sql.replace(selectClauseToReplace(), "SELECT COUNT(" + alias + ") ");
			}
			if (sqlCount.contains("ORDER BY")) {
				// Com ORDER BY, entra na condição, senão tiver, não entra
				sqlCount = sqlCount.substring(0, sqlCount.indexOf("ORDER BY"));
			}
			query = em.createQuery(sqlCount);

			applyParameters(query);

			break;

		case Named:
			// busca a anotacao "NamedQuery" da classe referenciada e troca o "e" por "count(e)" para fazer o
			// count da query passada

			// armazena todas as anotacoes da classe referenciada
			Annotation[] annotations = entityClass.getAnnotations();

			// percorre todas as anotacoes que foram retornadas
			for (Annotation a : annotations) {

				// entra somente se a anotacao for NamedQuery
				if (a instanceof NamedQueries) {

					// armazena todas as NamedQueries
					NamedQuery[] namedQuery = ((NamedQueries) a).value();

					// percorre todas as NamedQueries
					for (NamedQuery named : namedQuery) {

						// altera a query de "SELECT e" por "SELECT COUNT(*)" da query passada para retornar o Count
						// da mesma query
						if (queryName.equals(named.name())) {
							sql = named.query();
							sqlCount = sql.replace("SELECT e", "SELECT COUNT(e)");
							query = em.createQuery(sqlCount);
							break;

						}
					}
				}
			}

			applyParameters(query);

			break;

		}

		// Executa
		try {

			Object countValue = query.getSingleResult();

			if (countValue instanceof Long) {
				result.setTotalSize(((Long) countValue).intValue());
			} else {
				if (countValue instanceof BigDecimal) {
					result.setTotalSize(((BigDecimal) countValue).intValue());
				}
			}

		} catch (NonUniqueResultException e) {
			result.setTotalSize(query.getResultList().size());
		} catch (NoResultException f) {
			result.setTotalSize(0);
		}

		// Retorna
		return result;

	}

	private String selectClauseToReplace() {

		String[] split = sql.split("FROM");

		return split[0];

	}

	private String getSelectAlias() {

		String[] split = sql.split("FROM");

		String selectClause = split[0];

		String alias = null;

		if (selectClause.startsWith("SELECT DISTINCT")) {
			alias = selectClause.replace("SELECT DISTINCT", "");
		} else {
			alias = selectClause.replace("SELECT", "");
		}

		if (alias.contains(".")) {
			return aliasFider(alias, "\\.");
		}

		if (alias.contains(",")) {
			return aliasFider(alias, ",");
		}

		return alias.trim();
	}

	private String aliasFider(String string, String splitChar) {

		String[] aliasArray = string.split(splitChar);

		return aliasArray[0].trim();

	}

	/*
	 * ========================================================================
	 * Métodos Privados
	 * ========================================================================
	 */

	/**
	 * Aplica os parâmetros da consulta.
	 * 
	 * @param query
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void applyParameters(Query query) {
		if ((parameters != null) && (parameters.entrySet().size() > 0)) {
			Set<Entry> rawParameters = parameters.entrySet();
			for (Entry entry : rawParameters) {
				query.setParameter((String) entry.getKey(), entry.getValue());
			}
		}
	}

	private void applyParametersNativeQuery(Query query) {
		if (parameters != null) {
			for (int i = 1; i <= parameters.size(); i++) {
				query.setParameter(i, parameters.get(String.valueOf(i)));
			}
		}
	}
}
