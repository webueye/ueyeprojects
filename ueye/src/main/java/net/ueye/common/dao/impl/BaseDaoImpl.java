package net.ueye.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import net.ueye.common.common.Page;
import net.ueye.common.constant.HQL;
import net.ueye.common.constant.Message;
import net.ueye.common.dao.BaseDao;

public abstract class BaseDaoImpl<T>  implements BaseDao<T> {
	
	
	/**
	 * ###################      获取             ######################
	 */	

	@SuppressWarnings("unchecked")
	public T get(Class clazz, Serializable id) {
		return (T)this.getHibernateTemplate().get(clazz, id);
	}

	public Object getObject(Class<?> clazz, Serializable id){
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	
	
	/**
	 * ###################      添加/更新             ######################
	 */

	public void insert(Object entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	public void save(Object entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	public void saveOrUpdate(Object entity){
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public void update(Object entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	
	
	
	/**
	 * ###################      删除           ###################
	 */
		
	public void delete(Object entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public void delete(Class<?> clazz, Serializable id) {
		if(get(clazz, id) != null)
			this.getHibernateTemplate().delete(get(clazz, id));
	}
	
	public void delete(final String className, final String[] params, final Object[] values){
		getHibernateTemplate().execute(new HibernateCallback(){			
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer("DELETE FROM "+className+" className ");
				Query query = createQuery(session, params, HQL.EQUAL, HQL.AND, values, sb);				
				return query.executeUpdate();
			}			
		});	
	}
	
	public void delete(final String className, final String[] params, final Object[] values, final String andOrOr){
		getHibernateTemplate().execute(new HibernateCallback(){			
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer("DELETE FROM "+className+" className ");
				Query query = createQuery(session, params, HQL.EQUAL, andOrOr, values, sb);				
				return query.executeUpdate();
			}			
		});	
	}
	
	public void deleteByHQL(final String hql, final Object ... param) {
		this.getHibernateTemplate().execute(new HibernateCallback(){			
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(param != null){
					for(int i = 0; i < param.length; i++){
						query.setParameter(i, param[i]);
					}
				}
				return query.executeUpdate();
			}			
		});	
	}
	
	
	
	/**
	 * #################      统计          ###################
	 */
	
	@SuppressWarnings("unchecked")
	public int countByHql(String hql, Object ...params){
		List<Long> list = getHibernateTemplate().find(hql, params);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public int count(final String className){
		return count(className, null, null, null, null);
	}
	
	public int count(final String className, final String[] propName, final Object ...params){
		return count(className, propName, HQL.EQUAL, HQL.AND, params);
	}

	public int count(final String className, final String[] propName, final String equOrNo, final String andOror, final Object[] params){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer("SELECT count(*) FROM "+className+" className ");
				Long count= (Long) createQuery(session, propName, equOrNo, andOror, params, sb).uniqueResult();
				return count.intValue();
			}			
		});
	}
	
	

	/**
	 * #################      查询          ###################
	 */
	
	public List<?> findDataList(String queryString, Object...params){
		return this.getHibernateTemplate().find(queryString,params);
	}
	
	public List<?> findDataListByClassName(final String className, final String[] propName, final Object[] params){
		return findEntityListByEntityName(className, propName, params);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findEntityListByHql(String hql, Object... params) {
		return this.getHibernateTemplate().find(hql, params);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findEntityListByHql(final Page page, final String hql, final String[] params, final Object ... values){
		return  (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(page.getBeginIndex()).setFetchSize(page.getPageSize()).setMaxResults(page.getPageSize());
				if(params != null && values != null){
					if(params.length != values.length){
						throw new RuntimeException(Message.PARAMS_LENGTH_NOT_MATCH);
					}
					for(int i = 0; i < params.length; i++)
						query.setParameter(params[i], values[i]);
				}
				return query.list();
			}		
		});
	}
		
	public List<T> findEntityListByEntityName(final String className){
		return findEntityListByEntityName(null, className, null, HQL.EQUAL, null, null);
	}
	
	public List<T> findEntityListByEntityName(final Page page, final String className){
		return findEntityListByEntityName(page, className, null, HQL.EQUAL, null, null);
	}
	
	public List<T> findEntityListByEntityName(final String className, final String[] propName, final Object[] params){
		return findEntityListByEntityName(null, className, propName, HQL.EQUAL, HQL.AND, params);
	}
	
	public List<T> findEntityListByEntityName(final Page page, final String className, final String[] propName, final Object[] params){
		return findEntityListByEntityName(page, className, null, HQL.EQUAL, HQL.AND, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findEntityListByEntityName(final Page page, final String className, final String[] propName, final String equOrNo, final String andOror,final Object[] params){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer("FROM "+className+" className ");				
				Query query = createQuery(session, propName, equOrNo, andOror, params, sb);
				if(page != null){
					page.setTotalCount(count(className, propName, equOrNo, andOror, params));
					query.setFirstResult(page.getBeginIndex()).setMaxResults(page.getPageSize());
				}
				return query.list();
			}
		});
	}
	
	private Query createQuery(Session session, String[] propName, String equOrNo, String andOror, Object[] params, StringBuffer sb){
		if(propName != null && params != null){
			if(propName.length != params.length)
				throw new RuntimeException(Message.PARAMS_LENGTH_NOT_MATCH);
			sb.append("WHERE ");
			for(int i = 0; i < propName.length; i++){
				sb.append("className.");
				sb.append(propName[i]);
				sb.append(equOrNo);
				sb.append("? ");
				if(i != propName.length-1)
					sb.append(andOror);
				sb.append(" ");
			}
			sb.append(" ORDER BY className.id DESC");
			Query query = session.createQuery(sb.toString());
			for(int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
			return query;			
		}
		else{
			sb.append(" ORDER BY className.id DESC");
			return session.createQuery(sb.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	public String getGenericClassName(){
		Type type = this.getClass().getGenericSuperclass();
		Type[] types = ((ParameterizedType)type).getActualTypeArguments();
		if(types != null && types.length > 0){
			return ((Class)types[0]).getSimpleName();
		}
		throw new RuntimeException("The BaseDaoImpl :"+ Message.GENERIC_CLASS_NOT_GET);
	}
	
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
