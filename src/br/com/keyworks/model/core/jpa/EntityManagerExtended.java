package br.com.keyworks.model.core.jpa;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import br.com.keyworks.view.componentes.PagedResult;

public interface EntityManagerExtended extends EntityManager {

	/*
	 * ========================================================================
	 * Named Queries
	 * ========================================================================
	 */

	<T> List<T> findWithNamedQuery(String namedQueryName, Class<T> type);

	<T> List<T> findWithNamedQuery(String namedQueryName, Map<?, ?> parameters, Class<T> type);

	<T> T findFirstWithNamedQuery(String namedQueryName, Map<?, ?> parameters, Class<T> type);

	<T> T findFirstWithNamedQuery(String namedQueryName, Class<T> type);

	<T> PagedResult<T> findPageWithNamedQuery(String namedQueryName, Class<T> type, Map<?, ?> parameters, Integer start, Integer limit);

	/*
	 * ========================================================================
	 * Native Queries
	 * ========================================================================
	 */

	<T> List<T> findWithNamedNativeQuery(String namedNativeQueryName, Map<?, ?> parameters, Class<T> type);

	<T> PagedResult<T> findPageWithNativeQuery(String sql, Class<T> type, Integer start, Integer limit);

	<T> PagedResult<T> findPageWithNativeQuery(String sql, String resultSetMapping, Map<?, ?> parameters, Integer start, Integer limit);

	<T> List<T> findWithNativeQuery(String sql, Class<T> type);

	<T> List<T> findNativeQueryList(String sql, String resultSetMapping, Map<?, ?> parameters);

	/*
	 * ========================================================================
	 * Customized Queries
	 * ========================================================================
	 */

	<T> List<T> findWithQuery(String sql, Class<T> type);

	<T> T findFirstWithQuery(String sql, Map<?, ?> parameters, Class<T> type);

	<T> List<T> findWithQuery(String sql, Map<?, ?> parameters, Class<T> type);

	<T> PagedResult<T> findPageWithQuery(String sql, Map<?, ?> parameters, Class<T> type, Integer start, Integer limit);

	void remove(Long id, Class<?> clazz);

}
