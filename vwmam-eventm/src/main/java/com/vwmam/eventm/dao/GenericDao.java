/**
 * Copyright: Volkswagen Group China – Mobility Asia
 */
package com.vwmam.eventm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作接口，使用泛型方法。
 */
public interface GenericDao {

	/**
	 * 保存一个实体
	 */
	public <T> void save(T entity) throws Exception;

	/**
	 * 保存对象返回保存的id
	 */
	public <T> Integer saveObject(T entity) throws Exception;

	/**
	 * 更新一个实体
	 */
	public <T> void update(T entity) throws Exception;

	/**
	 * 获取一个实体，根据主键
	 */
	public <T> T getById(Serializable id, Class<T> clazz) throws Exception;

	/**
	 * 删除对象，根据主键
	 */
	public <T> void delete(Serializable id, Class<T> clazz) throws Exception;

	/**
	 * 获取单一对象，根据属性，支持多属性（符合条件的单条数据）
	 */
	public <T> T findOne(Class<T> clazz, Object... params) throws Exception;

	/**
	 * 获取单一对象，根据属性，支持多属性（符合条件的单条数据） 带排序
	 */
	public <T> T findOneWithSort(Class<T> clazz, String paramStr, String descStr, Object... params) throws Exception;

	/**
	 * 获取单一对象，根据hql（单个对象） <br>
	 * 支持hql中以？绑定的参数 <br>
	 */
	public <T> T findOneByHql(String hql, Object... params) throws Exception;

	/**
	 * 获取单一对象，根据hql（单个对象） <br>
	 * 支持hql中以？绑定的参数 <br>
	 */
	public <T> T findOneBySql(String sql, Object... params) throws Exception;

	/**
	 * 获取所有数据，根据属性，支持多属性（符合条件的所有数据） <br>
	 */
	public <T> List<T> findAll(Class<T> clazz, Object... params) throws Exception;

	/**
	 * 获取所有数据，根据属性，支持多属性（符合条件的所有数据） 带排序 <br>
	 */
	public <T> List<T> findAllWithSort(Class<T> clazz, String paramStr, String descStr, Object... params)
			throws Exception;

	/**
	 * 根据hql查询数据（单表）（符合条件的所有数据） <br>
	 * hql中如果使用?绑定参数，则使用该方法 <br>
	 */
	public <T> List<T> findAllByHqlSingleTable(String hql, Object... params) throws Exception;

	/**
	 * 根据hql查询数据（符合条件的所有数据） <br>
	 * hql中如果使用?绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findAllByHql(String hql, Object... params) throws Exception;

	/**
	 * 根据hql查询数据（符合条件的所有数据） <br>
	 * hql中如果使用命名绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findAllByHql(String hql, Map<String, Object> params) throws Exception;

	/**
	 * 根据sql查询数据（符合条件的所有数据） <br>
	 * sql中如果使用?绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findAllBySql(String sql, Object... params) throws Exception;

	/**
	 * 根据sql查询数据（符合条件的所有数据） <br>
	 * sql中如果使用命名绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findAllBySql(String sql, Map<String, Object> params) throws Exception;

	/**
	 * 分页获取数据，根据属性，支持多属性（符合条件的单页数据） <br>
	 * 默认按主键降序 <br>
	 */
	public <T> List<T> findPage(Class<T> clazz, int page, int pageSize, Object... params) throws Exception;

	/**
	 * 根据hql查询数据（单表）（符合条件的单页数据） <br>
	 * hql中如果使用?绑定参数，则使用该方法 <br>
	 */
	public <T> List<T> findPageByHqlSingleTable(String hql, int page, int pageSize, Object... params) throws Exception;

	/**
	 * 根据hql查询数据（符合条件的单页数据） <br>
	 * hql中如果使用?绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findPageByHql(String hql, int page, int pageSize, Object... params)
			throws Exception;

	/**
	 * 根据hql查询数据（符合条件的单页数据） <br>
	 * hql中如果使用命名绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findPageByHql(String hql, int page, int pageSize, Map<String, Object> params)
			throws Exception;

	/**
	 * 根据sql查询数据（符合条件的单页数据） <br>
	 * sql中如果使用?绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findPageBySql(String sql, int page, int pageSize, Object... params)
			throws Exception;

	/**
	 * 根据sql查询数据（符合条件的单页数据） <br>
	 * sql中如果使用命名绑定参数，则使用该方法 <br>
	 */
	public List<Map<String, Object>> findPageBySql(String sql, int page, int pageSize, Map<String, Object> params)
			throws Exception;

	/**
	 * 查询总数（符合条件的数据总条数） <br>
	 * 根据属性查询，支持多属性 <br>
	 */
	public <T> int findTotalCounts(Class<T> clazz, Object... params) throws Exception;

	/**
	 * 查询总数（符合条件的数据总条数） <br>
	 * 根据hql，sql中如果使用？绑定参数，则使用该方法 <br>
	 */
	public int findTotalCountsByHql(String hql, Object... params) throws Exception;

	/**
	 * 查询总数（符合条件的数据总条数） <br>
	 * 根据hql，hql中如果使用命名绑定参数，则使用该方法 <br>
	 */
	public int findTotalCountsByHql(String hql, Map<String, Object> params) throws Exception;

	/**
	 * 查询总数（符合条件的数据总条数） <br>
	 * 根据sql，sql中如果使用？绑定参数，则使用该方法 <br>
	 */
	public int findTotalCountsBySql(String sql, Object... params) throws Exception;

	/**
	 * 查询总数（符合条件的数据总条数） <br>
	 * 根据sql，sql中如果使用命名绑定参数，则使用该方法 <br>
	 */
	public int findTotalCountsBySql(String sql, Map<String, Object> params) throws Exception;

	/**
	 * hql in(A1,A2,A3....)查询语句
	 */
	public List findByHQL(String hql, Object arg[]);

	/**
	 * pageModel 分页（Object 参数）
	 */
	public PageModel queryPageModelSQL(String sql, PageModel pageinfo, Object... params) throws Exception;

	/**
	 * pageModel 分页（Map 参数）
	 */
	public PageModel findPageModelSQL(String sql, PageModel pageModel, Map<String, Object> params) throws Exception;
}