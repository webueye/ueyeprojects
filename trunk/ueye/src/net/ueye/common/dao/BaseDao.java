package net.ueye.common.dao;

import java.io.Serializable;
import java.util.List;

import net.ueye.common.common.Page;

/**
 * @author rubys@vip.qq.com
 * Aug 28, 2009
 */
public interface BaseDao<T> {

	/**
	 * ###################      获取             ######################
	 */
	
	T get(Class<?> clazz,Serializable id);
	
	Object getObject(Class<?> clazz,Serializable id);
	
	
	
	/**
	 * ###################      添加/更新             ######################
	 */
	
	void insert(Object entity);
	
	void save(Object entity);
	
	void saveOrUpdate(Object entity);

	void update(Object entity);
	
	
	
	/**
	 * ###################      删除           ###################
	 */
	
	void delete(Object entity);
	
	void delete(Class<?> clazz,Serializable id);
	
	void delete(final String className, final String[] params, final Object[] values);
	
	void delete(final String className, final String[] params, final Object[] values, final String andOrOr);
	
	void deleteByHQL(final String hql,Object ... params);
	
	
	
	/**
	 * #################      统计          ###################
	 */
	
	int countByHql(String hql,Object ...params);
	
	int count(final String className);
	
	int count(final String className,final String[] propName,final Object ...params);
	
	int count(final String className,final String[] propName,final String equOrNo,final String andOror,final Object[] params);
	
	
	
	/**
	 * #################      查询          ###################
	 */

	List<?> findDataList(String queryString,Object...params);
	
	List<?> findDataListByClassName(final String className, final String[] propName, final Object[] params);
	
	List<T> findEntityListByHql(String hql,Object ...params);
	
	List<T> findEntityListByHql(final Page page,final String hql,final String[] params,final Object ... values);
		
	List<T> findEntityListByEntityName(final String className);
	
	List<T> findEntityListByEntityName(final Page page,final String className);
	
	List<T> findEntityListByEntityName(final String className,final String[] propName,final Object[] params);
	
	List<T> findEntityListByEntityName(final Page page,final String className,final String[] propName,final Object[] params);
	
	List<T> findEntityListByEntityName(final Page page,final String className,final String[] propName,final String equOrNo,final String andOror,final Object[] params);
	
	
	
	//void execute(String hql);
	
}
