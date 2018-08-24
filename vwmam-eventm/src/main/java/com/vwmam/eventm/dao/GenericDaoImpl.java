/**
 * Copyright: Volkswagen Group China – Mobility Asia
 */
package com.vwmam.eventm.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据库操作实现，使用泛型方法。
 */
@SuppressWarnings("unchecked")
@Repository(value = "genericDao")
@Transactional
public class GenericDaoImpl implements GenericDao {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 保存一个实体
	 */
	public <T> void save(T entity) throws Exception {
		sessionFactory.getCurrentSession().save(entity);
	}

	/**
	 * 保存一个实体
	 */
	public <T> Integer saveObject(T entity) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Serializable a = session.save(entity);
		return Integer.parseInt(String.valueOf(a));
	}

	/**
	 * 更新一个实体
	 */
	public <T> void update(T entity) throws Exception {
		sessionFactory.getCurrentSession().merge(entity);
	}

	/**
	 * 获取一个实体，根据主键
	 */
	public <T> T getById(Serializable id, Class<T> clazz) throws Exception {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	/**
	 * 删除对象，根据主键
	 */
	public <T> void delete(Serializable id, Class<T> clazz) throws Exception {
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(clazz, id));
	}

	/**
	 * 获取单一对象，根据属性，支持多属性（符合条件的单条数据）
	 */
	public <T> T findOne(Class<T> clazz, Object... params) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		Object[] paramArr = params;
		for (int i = 0; i < paramArr.length;) {
			criteria.add(Restrictions.eq(String.valueOf(paramArr[i]), paramArr[i + 1]));
			i = i + 2;
		}
		List<T> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	/**
	 * 获取单一对象，根据属性，支持多属性（符合条件的单条数据） 带排序 
	 */
	public <T> T findOneWithSort(Class<T> clazz, String paramStr, String descStr, Object... params) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		if ("desc".equals(descStr)) {
			criteria.addOrder(Order.desc(paramStr));
		} else {
			criteria.addOrder(Order.asc(paramStr));
		}
		Object[] paramArr = params;
		for (int i = 0; i < paramArr.length;) {
			criteria.add(Restrictions.eq(String.valueOf(paramArr[i]), paramArr[i + 1]));
			i = i + 2;
		}
		List<T> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 获取单一对象，根据hql（单个对象）
	 */
	public <T> T findOneByHql(String hql, Object... params) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		List<T> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	/**
	 * 获取单一对象，根据hql（单个对象）
	 */
	public <T> T findOneBySql(String sql, Object... params) throws Exception {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		List<T> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	/**
	 * 获取所有数据，根据属性，支持多属性（符合条件的所有数据）
	 */
	public <T> List<T> findAll(Class<T> clazz, Object... params) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		criteria.addOrder(Order.desc("id"));
		Object[] paramArr = params;
		for (int i = 0; i < paramArr.length;) {
			criteria.add(Restrictions.eq(String.valueOf(paramArr[i]), paramArr[i + 1]));
			i = i + 2;
		}
		return criteria.list();
	}

	/**
	 * 获取所有数据，根据属性，支持多属性（符合条件的所有数据）带排序
	 */
	public <T> List<T> findAllWithSort(Class<T> clazz, String paramStr, String descStr, Object... params)
			throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);

		if ("desc".equals(descStr)) {
			criteria.addOrder(Order.desc(paramStr));
		} else {
			criteria.addOrder(Order.asc(paramStr));
		}
		Object[] paramArr = params;
		for (int i = 0; i < paramArr.length;) {
			criteria.add(Restrictions.eq(String.valueOf(paramArr[i]), paramArr[i + 1]));
			i = i + 2;
		}
		return criteria.list();
	}

	/**
	 * 根据hql查询数据（单表）（符合条件的所有数据） <br>
	 * hql中如果使用?绑定参数，则使用该方法
	 */
	public <T> List<T> findAllByHqlSingleTable(String hql, Object... params) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		return q.list();
	}

	/**
	 * 根据hql查询数据（符合条件的所有数据） hql中如果使用?绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findAllByHql(String hql, Object... params) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		return q.list();
	}

	/**
	 * 根据hql查询数据（符合条件的所有数据） hql中如果使用命名绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findAllByHql(String hql, Map<String, Object> params) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (String param : params.keySet()) {
			q.setParameter(param, params.get(param));
		}
		return q.list();
	}

	/**
	 * 根据sql查询数据（符合条件的所有数据） sql中如果使用?绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findAllBySql(String sql, Object... params) throws Exception {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		return q.list();
	}

	/**
	 * 根据sql查询数据（符合条件的所有数据） sql中如果使用命名绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findAllBySql(String sql, Map<String, Object> params) throws Exception {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		int i = 0;
		for (String param : params.keySet()) {
			q.setParameter(i, params.get(param));
			i++;
		}
		return q.list();
	}

	/**
	 * 分页获取数据，根据属性，支持多属性（符合条件的单页数据）  默认按主键降序
	 */
	public <T> List<T> findPage(Class<T> clazz, int page, int pageSize, Object... params) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		criteria.addOrder(Order.desc("id"));
		Object[] paramArr = params;
		for (int i = 0; i < paramArr.length;) {
			criteria.add(Restrictions.eq(String.valueOf(paramArr[i]), paramArr[i + 1]));
			i = i + 2;
		}
		criteria.setFirstResult((page - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	/**
	 * 根据hql查询数据（单表）（符合条件的单页数据） hql中如果使用?绑定参数，则使用该方法
	 */
	public <T> List<T> findPageByHqlSingleTable(String hql, int page, int pageSize, Object... params) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		return q.list();
	}

	/**
	 * 根据hql查询数据（符合条件的单页数据） <br>
	 * hql中如果使用?绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findPageByHql(String hql, int page, int pageSize, Object... params)
			throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		return q.list();
	}

	/**
	 * 根据hql查询数据（符合条件的单页数据） hql中如果使用命名绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findPageByHql(String hql, int page, int pageSize, Map<String, Object> params)
			throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (String param : params.keySet()) {
			q.setParameter(param, params.get(param));
		}

		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		return q.list();
	}

	/**
	 * 根据sql查询数据（符合条件的单页数据） sql中如果使用?绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findPageBySql(String sql, int page, int pageSize, Object... params)
			throws Exception {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		return q.list();
	}

	/**
	 * 根据sql查询数据（符合条件的单页数据） sql中如果使用命名绑定参数，则使用该方法
	 */
	public List<Map<String, Object>> findPageBySql(String sql, int page, int pageSize, Map<String, Object> params)
			throws Exception {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		int i = 0;
		for (String param : params.keySet()) {
			String value = params.get(param) == null ? "" : params.get(param).toString();
			q.setParameter(i, value);
			i++;
		}
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		return q.list();
	}

	/**
	 * 查询总数（符合条件的数据总条数）根据属性查询，支持多属性
	 */
	public <T> int findTotalCounts(Class<T> clazz, Object... params) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		Object[] paramArr = params;
		for (int i = 0; i < paramArr.length;) {
			criteria.add(Restrictions.eq(String.valueOf(paramArr[i]), paramArr[i + 1]));
			i = i + 2;
		}
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	/**
	 * 查询总数（符合条件的数据总条数） 根据hql，sql中如果使用？绑定参数，则使用该方法
	 */
	public int findTotalCountsByHql(String hql, Object... params) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		return ((Number) q.uniqueResult()).intValue();
	}

	/**
	 * 查询总数（符合条件的数据总条数） 根据hql，sql中如果使用命名绑定参数，则使用该方法
	 */
	public int findTotalCountsByHql(String hql, Map<String, Object> params) throws Exception {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (String param : params.keySet()) {
			q.setParameter(param, params.get(param));
		}
		return ((Number) q.uniqueResult()).intValue();
	}

	/**
	 * 查询总数（符合条件的数据总条数） 根据sql，sql中如果使用？绑定参数，则使用该方法
	 */
	public int findTotalCountsBySql(String sql, Object... params) throws Exception {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < params.length;) {
			q.setParameter(i, params[i]);
			i = i + 1;
		}
		return ((Number) q.uniqueResult()).intValue();
	}

	/**
	 * 查询总数（符合条件的数据总条数） <br>根据sql，sql中如果使用命名绑定参数，则使用该方法
	 */
	public int findTotalCountsBySql(String sql, Map<String, Object> params) throws Exception {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (String param : params.keySet()) {
			q.setParameter(param, params.get(param));
		}
		return ((Number) q.uniqueResult()).intValue();
	}
	
	/**
	 * hql in(A1,A2,A3....)查询语句
	 */
	public List findByHQL(String hql, Object arg[]) {
		Query query = getSession().createQuery(hql);
		try {
			if (arg != null && arg.length > 0) {
				for (int i = 0; i < arg.length; i++)
					query.setParameter(i, arg[i]);
			}
			return query.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * pageModel 分页（Object 参数）
	 */
	public PageModel queryPageModelSQL(String sql, PageModel pageinfo, Object... params) throws Exception {
		long stime = System.currentTimeMillis();
		pageinfo = pageinfo == null ? new PageModel() : pageinfo;

		int orderpoint = sql.toLowerCase().indexOf("order by");
		String tmp;
		if (orderpoint > 0) {
			tmp = sql.substring(0, orderpoint - 1);
		}else {
			tmp = sql;
		}
		String totalSQL = "select count(*) as cnt from (" + tmp + " ) t ";
		List list = findAllBySql(totalSQL, params);
		int rowCount = 0;
		if ((list != null) && (list.size() > 0)) {
			Map rowData = (Map) list.get(0);
			Object obj = rowData.get("cnt");
			rowCount = Integer.valueOf(String.valueOf(obj));
		}
		pageinfo.setRowCount(rowCount);//总条数

		String dataSql = "select tempa.* from (" + sql + ") tempa limit " + (pageinfo.getStart() - 1) + "," + pageinfo.getLimit();
		list = findAllBySql(dataSql, params);
		double usedtime = (System.currentTimeMillis() - stime) / 1000.0D;
		if (usedtime > 3000.0D) {
			System.out.println("querySQL() used " + usedtime + "s[sql:" + sql + "]");
		}
		pageinfo.setObjectList(list);//满足条数的数据

		int hasNext = 0;
		if (rowCount > pageinfo.getPageNo() * pageinfo.getLimit()) {
			hasNext = 1;
		}
		pageinfo.setHasNext(hasNext);
		int totalPage = rowCount / pageinfo.getLimit();
		if (rowCount % pageinfo.getLimit() != 0) {
			totalPage = totalPage + 1;
		}
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}

	/**
	 * pageModel分页（Map 参数）
	 */
	@SuppressWarnings("rawtypes")
	public PageModel findPageModelSQL(String sql, PageModel pageModel, Map<String, Object> params) throws Exception {
		long stime = System.currentTimeMillis();
		pageModel = pageModel == null ? new PageModel() : pageModel;
		int orderpoint = sql.toLowerCase().indexOf("order by");
		String tmp;
		if (orderpoint > 0) {
			tmp = sql.substring(0, orderpoint - 1);
		} else {
			tmp = sql;
		}
		String totalSQL = "select count(*) as cnt from (" + tmp + " ) t ";
		List list = findAllBySql(totalSQL, params);
		int rowCount = 0;
		if ((list != null) && (list.size() > 0)) {
			Map rowData = (Map) list.get(0);
			BigDecimal o = (BigDecimal) rowData.values().toArray()[0];
			rowCount = o.intValue();
		}
		pageModel.setRowCount(rowCount);//总条数
		
		String sqlQ = "select tempa.* from (" + sql + ") tempa limit " + (pageModel.getStart() - 1) + "," + pageModel.getLimit();
		list = findAllBySql(sqlQ, params);
		double usedtime = (System.currentTimeMillis() - stime) / 1000.0D;
		if (usedtime > 3000.0D) {
			System.out.println("querySQL() used " + usedtime + "s[sql:" + sql + "]");
		}
		pageModel.setObjectList(list);//满足条数的数据

		int hasNext = 0;
		if (rowCount > pageModel.getPageNo() * pageModel.getLimit()) {
			hasNext = 1;
		}
		pageModel.setHasNext(hasNext);
		int totalPage = rowCount / pageModel.getLimit();
		if (rowCount % pageModel.getLimit() != 0) {
			totalPage = totalPage + 1;
		}
		pageModel.setTotalPage(totalPage);
		return pageModel;
	}
	
	/**
	 * @return Session
	 */
	public Session getSession() {
		// 需要开启事物，才能得到CurrentSession
		return sessionFactory.getCurrentSession();
	}
	
}