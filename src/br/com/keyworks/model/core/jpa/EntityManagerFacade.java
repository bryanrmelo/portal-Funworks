package br.com.keyworks.model.core.jpa;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import br.com.keyworks.model.core.jpa.QueryExecutor.QueryType;
import br.com.keyworks.view.componentes.PagedResult;

public class EntityManagerFacade implements EntityManagerExtended {

	private final EntityManager entityManager;

	public EntityManagerFacade(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Object entity) {

		try {
			entityManager.persist(entity);

		} catch (ConstraintViolationException e) {
			for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
				System.out.println("============================================");
				System.out.println("Root Bean: " + violation.getRootBean());
				System.out.println("Property Path: " + violation.getPropertyPath());
				System.out.println("Invalid Value: " + violation.getInvalidValue());
				System.out.println("Message: " + violation.getMessage());
			}
			throw e;
		}
	}

	@Override
	public <T> T merge(T entity) {

		// FIXME: It's a temporary solution to show errors on log files. Must be removed before going to production.
		try {

			// Keep only the line below
			return entityManager.merge(entity);

		} catch (ConstraintViolationException e) {
			for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
				System.out.println("============================================");
				System.out.println("Root Bean: " + violation.getRootBean());
				System.out.println("Property Path: " + violation.getPropertyPath());
				System.out.println("Invalid Value: " + violation.getInvalidValue());
				System.out.println("Message: " + violation.getMessage());
			}
			throw e;
		}
	}

	@Override
	public void remove(Object entity) {
		entityManager.remove(entity);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
		return entityManager.find(entityClass, primaryKey, properties);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
		return entityManager.find(entityClass, primaryKey, lockMode);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
		return entityManager.find(entityClass, primaryKey, lockMode, properties);
	}

	@Override
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		return entityManager.getReference(entityClass, primaryKey);
	}

	@Override
	public void flush() {
		entityManager.flush();
	}

	@Override
	public void setFlushMode(FlushModeType flushMode) {
		entityManager.setFlushMode(flushMode);
	}

	@Override
	public FlushModeType getFlushMode() {
		return entityManager.getFlushMode();
	}

	@Override
	public void lock(Object entity, LockModeType lockMode) {
		entityManager.lock(entity, lockMode);
	}

	@Override
	public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		entityManager.lock(entity, lockMode, properties);
	}

	@Override
	public void refresh(Object entity) {
		entityManager.refresh(entity);
	}

	@Override
	public void refresh(Object entity, Map<String, Object> properties) {
		entityManager.refresh(entity, properties);
	}

	@Override
	public void refresh(Object entity, LockModeType lockMode) {
		entityManager.refresh(entity, lockMode);
	}

	@Override
	public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		entityManager.refresh(entity, lockMode, properties);
	}

	@Override
	public void clear() {
		entityManager.clear();
	}

	@Override
	public void detach(Object entity) {
		entityManager.detach(entity);
	}

	@Override
	public boolean contains(Object entity) {
		return entityManager.contains(entity);
	}

	@Override
	public LockModeType getLockMode(Object entity) {
		return entityManager.getLockMode(entity);
	}

	@Override
	public void setProperty(String propertyName, Object value) {
		entityManager.setProperty(propertyName, value);
	}

	@Override
	public Map<String, Object> getProperties() {
		return entityManager.getProperties();
	}

	@Override
	public Query createQuery(String qlString) {
		return entityManager.createQuery(qlString);
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		return entityManager.createQuery(criteriaQuery);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		return entityManager.createQuery(qlString, resultClass);
	}

	@Override
	public Query createNamedQuery(String name) {
		return entityManager.createNamedQuery(name);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
		return entityManager.createNamedQuery(name, resultClass);
	}

	@Override
	public Query createNativeQuery(String sqlString) {
		return entityManager.createNativeQuery(sqlString);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Query createNativeQuery(String sqlString, Class resultClass) {
		return entityManager.createNativeQuery(sqlString, resultClass);
	}

	@Override
	public Query createNativeQuery(String sqlString, String resultSetMapping) {
		return entityManager.createNativeQuery(sqlString, resultSetMapping);
	}

	@Override
	public void joinTransaction() {
		entityManager.joinTransaction();
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		return entityManager.unwrap(cls);
	}

	@Override
	public Object getDelegate() {
		return entityManager.getDelegate();
	}

	@Override
	public void close() {
		entityManager.close();
	}

	@Override
	public boolean isOpen() {
		return entityManager.isOpen();
	}

	@Override
	public EntityTransaction getTransaction() {
		return entityManager.getTransaction();
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManager.getEntityManagerFactory();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	@Override
	public Metamodel getMetamodel() {
		return entityManager.getMetamodel();
	}

	/*
	 * ========================================================================
	 * Named Queries
	 * ========================================================================
	 */

	@Override
	public <T> List<T> findWithNamedQuery(String namedQueryName, Class<T> type) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Named, namedQueryName, type);
		return query.getList();
	}

	@Override
	public <T> List<T> findWithNamedQuery(String namedQueryName, Map<?, ?> parameters, Class<T> type) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Named, namedQueryName, type);
		query.setParameters(parameters);
		return query.getList();
	}

	@Override
	public <T> T findFirstWithNamedQuery(String namedQueryName, Map<?, ?> parameters, Class<T> type) {
		List<T> entities = findWithNamedQuery(namedQueryName, parameters, type);
		if (entities.isEmpty()) {
			return null;
		} else {
			return entities.get(0);
		}
	}

	@Override
	public <T> T findFirstWithNamedQuery(String namedQueryName, Class<T> type) {
		List<T> entities = findWithNamedQuery(namedQueryName, type);
		if (entities.isEmpty()) {
			return null;
		} else {
			return entities.get(0);
		}
	}

	@Override
	public <T> PagedResult<T> findPageWithNamedQuery(String namedQueryName, Class<T> type, Map<?, ?> parameters, Integer start, Integer limit) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Named, namedQueryName, type, start, limit, parameters);
		return query.getPagedResult();
	}

	/*
	 * ========================================================================
	 * Native Queries
	 * ========================================================================
	 */
	@Override
	public <T> List<T> findWithNamedNativeQuery(String namedNativeQueryName, Map<?, ?> parameters, Class<T> type) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Native, namedNativeQueryName, type);
		query.setParameters(parameters);
		return query.getList();
	}

	@Override
	public <T> PagedResult<T> findPageWithNativeQuery(String sql, Class<T> type, Integer start, Integer limit) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Native, sql, type, start, limit, null);
		return query.getPagedResult();
	}

	@Override
	public <T> PagedResult<T> findPageWithNativeQuery(String sql, String resultSetMapping, Map<?, ?> parameters, Integer start, Integer limit) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Native, sql, null, start, limit, parameters);
		query.setResultSetMapping(resultSetMapping);
		return query.getPagedResult();
	}

	@Override
	public <T> List<T> findNativeQueryList(String sql, String resultSetMapping, Map<?, ?> parameters) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Native, sql, null, null, null, parameters);
		query.setResultSetMapping(resultSetMapping);
		return query.getList();
	}

	@Override
	public <T> List<T> findWithNativeQuery(String sql, Class<T> type) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Native, sql, type, null, null, null);
		return query.getList();
	}

	/*
	 * ========================================================================
	 * Customized Queries
	 * ========================================================================
	 */

	@Override
	public <T> List<T> findWithQuery(String sql, Class<T> type) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Custom, sql, type);
		return query.getList();
	}

	@Override
	public <T> List<T> findWithQuery(String sql, Map<?, ?> parameters, Class<T> type) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Custom, sql, type);
		query.setParameters(parameters);
		return query.getList();
	}

	@Override
	public <T> T findFirstWithQuery(String sql, Map<?, ?> parameters, Class<T> type) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Custom, sql, type);
		query.setParameters(parameters);

		List<T> entities = query.getList();
		if (entities.isEmpty()) {
			return null;
		} else {
			return entities.get(0);
		}
	}

	@Override
	public <T> PagedResult<T> findPageWithQuery(String sql, Map<?, ?> parameters, Class<T> type, Integer start, Integer limit) {
		QueryExecutor<T> query = new QueryExecutor<T>(entityManager, QueryType.Custom, sql, type, start, limit, parameters);
		return query.getPagedResult();
	}

	@Override
	public void remove(Long id, Class<?> clazz) {
		entityManager.remove(entityManager.getReference(clazz, id));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Query createQuery(CriteriaUpdate updateQuery) {
		return entityManager.createQuery(updateQuery);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Query createQuery(CriteriaDelete deleteQuery) {
		return entityManager.createQuery(deleteQuery);
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
		return entityManager.createNamedStoredProcedureQuery(name);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
		return entityManager.createStoredProcedureQuery(procedureName);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
		return entityManager.createStoredProcedureQuery(procedureName, resultClasses);
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
		return entityManager.createStoredProcedureQuery(procedureName, resultSetMappings);
	}

	@Override
	public boolean isJoinedToTransaction() {
		return entityManager.isJoinedToTransaction();
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
		return entityManager.createEntityGraph(rootType);
	}

	@Override
	public EntityGraph<?> createEntityGraph(String graphName) {
		return entityManager.createEntityGraph(graphName);
	}

	@Override
	public EntityGraph<?> getEntityGraph(String graphName) {
		return entityManager.getEntityGraph(graphName);
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
		return entityManager.getEntityGraphs(entityClass);
	}

}
