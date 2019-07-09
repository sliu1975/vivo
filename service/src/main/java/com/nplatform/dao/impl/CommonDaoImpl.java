package com.nplatform.dao.impl;

import com.mixsmart.exception.NullArgumentException;
import com.mixsmart.utils.StringUtils;
import com.nplatform.bean.BaseBeanImpl;
import com.nplatform.builder.SQLBuilder;
import com.nplatform.dao.ICommonDao;
import com.nplatform.exception.DaoException;
import com.nplatform.util.sqlmap.SQLVarParamFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.*;

public class CommonDaoImpl implements ICommonDao {
    
    protected Logger log = null;

	public CommonDaoImpl() {
        log = LoggerFactory.getLogger(getClass());
    }

	@Autowired
	private EntityManager entityManager;

    //@Autowired
	//protected SessionFactory sessionFactory;
	
	//protected Session getSession() {
	//	return sessionFactory.getCurrentSession();
	//}

	public Long exeCountSql(String sql, Map<String, Object> param) throws DaoException {
		long total = 0;
		if(StringUtils.isNotEmpty(sql)) {
			try {
				log.info("统计数据SQL["+sql+"]");
				Query query = (Query)getQuery(sql, param, true);
				//Object obj = query.uniqueResult();
				Object obj = query.getSingleResult();

				total = Long.parseLong(obj.toString());
			} catch (Exception e) {
				log.info("统计数据SQL["+sql+"]--[异常]--["+e.getMessage()+"]");
				e.printStackTrace();
				throw new DaoException(e.getLocalizedMessage(), e.getCause());
			}
		}
		return total;
	}

	public Long countSql(String sql, Map<String, Object> param) throws DaoException {
		long total = 0;
		if(StringUtils.isNotEmpty(sql)) {
			//sql = "select count(*) from ("+sql+") t";
			String countSql = SQLBuilder.countSQL(sql);
			if(StringUtils.isEmpty(countSql)) {
				throw new NullArgumentException("统计SQL语句为空");
			}
			total = exeCountSql(countSql, param);
		}
		return total;
	}

	public Integer executeSql(String sql) throws DaoException {
		int result = 0;
		if(StringUtils.isNotEmpty(sql)) {
			log.info("执行SQL["+sql+"]");
			try {
				result = getQuery(sql, null, true).executeUpdate();
			} catch (Exception e) {
				log.info("执行SQL["+sql+"]--[异常]--["+e.getMessage()+"]");
				e.printStackTrace();
				throw new DaoException(e.getLocalizedMessage(), e.getCause());
		    }
		}
		return result;
	}

	public Integer executeSql(String sql,Map<String, Object> param) throws DaoException {
		int result = 0;
		if(StringUtils.isNotEmpty(sql)) {
			try {
				log.info("执行SQL["+sql+"]");
			    result = getQuery(sql, param, true).executeUpdate(); 
			}catch (Exception e) {
				log.info("执行SQL["+sql+"]--[异常]--["+e.getMessage()+"]");
				e.printStackTrace();
				throw new DaoException(e.getLocalizedMessage(), e.getCause());
		    }
		}
		return result;
	}

	public Integer executeSql(String sql,List<Map<String, Object>> params) throws DaoException {
		int result = 0;
		if(StringUtils.isEmpty(sql)) {
			return result;
		}
		log.info("执行SQL["+sql+"]");
		try {
			if (null != params && params.size() > 0) {
				for(Map<String,Object> param : params) {
					result += getQuery(sql, param, true).executeUpdate();
				}
			}
		} catch (Exception e) {
			log.info("执行SQL["+sql+"]--[异常]--["+e.getMessage()+"]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
	    }
		return result;
	}

	public boolean executeSql(List<String> sqls, Map<String, Object> param) throws DaoException {
		boolean result = false;
		if(null != sqls && sqls.size()>0) {
			result = executeSql(sqls.toArray(new String[sqls.size()]),param);
		}
		return result;
	}


	public boolean executeSql(String[] sqls, Map<String, Object> param) throws DaoException {
		boolean result = false;
		if(null != sqls && sqls.length>0) {
			int res = 0;
			try {
			   for(String sql : sqls) {
				   if(StringUtils.isNotEmpty(sql)) {
					  res += getQuery(sql, param, true).executeUpdate();
				   }
			    }
			} catch (Exception e) {
				log.info("执行SQL-[异常]--["+e.getMessage()+"]");
				e.printStackTrace();
				throw new DaoException(e.getLocalizedMessage(), e.getCause());
			}
			result = res>0?true:false;
		}
		return result;
	}


	public boolean executeSql(List<String> sqls,List<Map<String, Object>> params) throws DaoException {
		boolean result = false;
		if(null != sqls && sqls.size()>0) {
			result = executeSql(sqls.toArray(new String[sqls.size()]),params);
		}
		return result;
	}


	public boolean executeSql(String[] sqls, List<Map<String, Object>> params) throws DaoException {
		boolean result = false;
		if(null != sqls && sqls.length>0) {
			int res = 0;
			int count = 0;
			try {
			    for(String sql : sqls) {
			    	if(StringUtils.isNotEmpty(sql)) {
			    		log.info("执行SQL["+sql+"]");
						if (null != params && params.size() > 0) {
							Map<String, Object> param = params.get(count);
							res += getQuery(sql, param, true).executeUpdate();
						}
					    count++;
			    	}
			   }
			} catch (Exception e) {
				log.info("执行SQL--[异常]--["+e.getMessage()+"]");
				e.printStackTrace();
				throw new DaoException(e.getLocalizedMessage(), e.getCause());
			}
			result = res>0?true:false;
		}
		return result;
	}


	

	/**
	 * 获取查询对象
	 * @param statement
	 * @param param
	 * @param isSql 标记statement参数内容是否为SQL语句
	 * <ul><li>true--表示statement参数内容为SQL语句</li>
	 *     <li>false--表示statement参数内容为HQL语句</li>
	 * </ul>
	 * @return Query
	 * @throws Exception
	 */
	protected Query getQuery(String statement, Map<String,Object> param, boolean isSql) throws Exception {
		Query query = null;
		if(StringUtils.isEmpty(statement)) {
			throw new NullArgumentException("statement 参数为空 ");
		}
		//处理SQL或HQL语句
		SQLVarParamFilter sqlVarFilter = new SQLVarParamFilter(statement, param);
		statement = sqlVarFilter.filter();
		param = sqlVarFilter.getParams();

		query =  entityManager.createNativeQuery(statement);
		//query = isSql?(getSession().createSQLQuery(statement)):(getSession().createQuery(statement));
		if(null != param) {
			//query.setProperties(param);

			for (Map.Entry<String, Object> entry : param.entrySet()) {
				//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				try {
					query.setParameter(entry.getKey(),entry.getValue());
				}
				catch(Exception e)
				{

				}
			}

		}

		return query;
	}

	protected Query getQuery(String statement, Map<String,Object> param, boolean isSql, Class<?> toBean) throws Exception {
		Query query = null;
		if(StringUtils.isEmpty(statement)) {
			throw new NullArgumentException("statement 参数为空 ");
		}
		//处理SQL或HQL语句
		SQLVarParamFilter sqlVarFilter = new SQLVarParamFilter(statement, param);
		statement = sqlVarFilter.filter();
		param = sqlVarFilter.getParams();

		query =  entityManager.createNativeQuery(statement,toBean);
		//query = isSql?(getSession().createSQLQuery(statement)):(getSession().createQuery(statement));
		if(null != param) {
			//query.setProperties(param);

			for (Map.Entry<String, Object> entry : param.entrySet()) {
				//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				try {
					query.setParameter(entry.getKey(),entry.getValue());
				}
				catch(Exception e)
				{

				}
			}

		}

		return query;
	}
	
	/**
	 * 获取查询对象
	 * @param statement
	 * @param isSql 标记statement参数内容是否为SQL语句
	 * <ul><li>true--表示statement参数内容为SQL语句</li>
	 *     <li>false--表示statement参数内容为HQL语句</li>
	 * </ul>
	 * @return Query
	 * @throws Exception
	 */
	protected Query getQuery(String statement, boolean isSql) throws Exception {
		Query query = null;
		if(StringUtils.isNotEmpty(statement)) {
			//query = isSql?(getSession().createSQLQuery(statement)):(getSession().createQuery(statement));
			query =  entityManager.createNativeQuery(statement);
		}
		return query;
	}
	

	public List<Object> queryObjSql(String sql) throws DaoException {
		return queryObjSql(sql, null);
	}

	public List<Object> queryObjSql(String sql,Map<String, Object> param) throws DaoException {
		return queryObjSql(sql, param, null, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> queryObjSql(String sql,Map<String, Object> param,Integer start, Integer rows) throws DaoException {
		List<Object> list = null;
		if(StringUtils.isEmpty(sql)) {
			log.error("SQL语句为空！");
			return null;
	    }
		try {
			//SQLQuery query = (SQLQuery)getQuery(sql, param, true);
			Query query = (Query)getQuery(sql, param, true);

			if(null != start && null != rows) {
				query.setFirstResult(start);
				query.setMaxResults(rows);
			}

			//list = query.list();
			list = query.getResultList();

			log.info("通过SQL查询数据[成功]");
		} catch (Exception e) {
			log.info("通过SQL查询数据[失败]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
		}
		return list;
	}

	public Long exeCountSql(String sql) throws DaoException {
		return exeCountSql(sql, null);
	}

	public Long countSql(String sql) throws DaoException {
		long total = 0;
		if(StringUtils.isNotEmpty(sql)) {
			String countSql = SQLBuilder.countSQL(sql);
			if(StringUtils.isEmpty(countSql)) {
				throw new NullArgumentException("统计SQL语句为空");
			}
			total = exeCountSql(countSql);
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> querySqlToBean(String sql, Class<?> toBean) throws DaoException {
		List<E> list = null;
		if(StringUtils.isEmpty(sql)) {
	    	return null;
	    }
		try {
			Query query = (Query)getQuery(sql, true);
			addScalars(query, toBean);

			//list = query.setResultTransformer(Transformers.aliasToBean(toBean)).list();

			log.info("通过SQL查询数据[成功]");
		} catch (Exception e) {
			log.info("通过SQL查询数据[失败]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> querySqlToBean(String sql, Map<String, Object> param, Class<?> toBean) throws DaoException {
		List<E> list = null;
		if(StringUtils.isEmpty(sql)) {
	    	return null;
	    }
		try {
			Query query = (Query)getQuery(sql, param, true, toBean);
			//addScalars(query, toBean);

			//list = query.setResultTransformer(Transformers.aliasToBean(toBean)).list();
			list = query.getResultList();

			log.info("通过SQL查询数据[成功]");
		} catch (Exception e) {
			log.info("通过SQL查询数据[失败]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
		}
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public <E> List<E> querySqlToBean(String sql, Map<String, Object> param, Class<?> toBean, Integer start, Integer rows) throws DaoException {
		List<E> list = null;
		if(StringUtils.isEmpty(sql)) {
	    	return null;
	    }
		try {
			Query query = (Query)getQuery(sql, param, true);
			if(null != start && null != rows) {
				query.setFirstResult(start);
				query.setMaxResults(rows);
			}
			addScalars(query, toBean);

			//list = query.setResultTransformer(Transformers.aliasToBean(toBean)).list();

			log.info("通过SQL查询数据[成功]");
		} catch (Exception e) {
			log.info("通过SQL查询数据[失败]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
		}
		return list;
	}
	
	/**
	 * SQL查询对象中添加toBeanClass属性
	 * @param sqlQuery SQLQuery对象
	 * @param toBeanClass 转换Bean
	 * @return 返回处理后的SQLQuery对象
	 */
	protected Query addScalars(Query sqlQuery, Class<?> toBeanClass){
		if(null == toBeanClass) {
			return sqlQuery;
		}
		List<Field> fields = new ArrayList<Field>();
		getAllFields(fields, toBeanClass);
		for(Field field : fields){
			if(!field.getName().equals("serialVersionUID")){
				/*
				if(field.getType() == Integer.class){
					sqlQuery.addScalar(field.getName(), StandardBasicTypes.INTEGER);
				} else if(field.getType() == Double.class){
					sqlQuery.addScalar(field.getName(), StandardBasicTypes.DOUBLE);
				} else if(field.getType() == Long.class){
					sqlQuery.addScalar(field.getName(), StandardBasicTypes.LONG);
				} else if(field.getType() == Float.class){
					sqlQuery.addScalar(field.getName(), StandardBasicTypes.FLOAT);
				} else if(field.getType() == Date.class){
					sqlQuery.addScalar(field.getName(), StandardBasicTypes.DATE);
				} else if(field.getType() == String.class){
					sqlQuery.addScalar(field.getName(), StandardBasicTypes.STRING);
				} else{
					sqlQuery.addScalar(field.getName());
				}
				*/
			}
		}
		return sqlQuery;
	}
	
	
	private void getAllFields(List<Field> fields,Class<?> toBeanClass) {
		if(null == fields || null == toBeanClass) {
			throw new NullArgumentException();
		}
		Field[] array = toBeanClass.getDeclaredFields();
		if(array.length > 0) {
			fields.addAll(Arrays.asList(array));
		}
		toBeanClass = toBeanClass.getSuperclass();
		if(null != toBeanClass && !(toBeanClass.isAssignableFrom(Object.class))) {
			getAllFields(fields, toBeanClass);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> querySqlToEntity(String sql, Class<? extends BaseBeanImpl> entity) throws DaoException {
		List<E> list = null;
		if(StringUtils.isEmpty(sql)) {
	    	return null;
	    }
		try {
			Query query = (Query)getQuery(sql, true);
			//query.addEntity(entity);
			list = query.getResultList();
			log.info("通过SQL查询数据[成功]");
		} catch (Exception e) {
			log.info("通过SQL查询数据[失败]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> querySqlToEntity(String sql, Map<String, Object> param, Class<? extends BaseBeanImpl> entity) throws DaoException {
		List<E> list = null;
		if(StringUtils.isEmpty(sql)) {
	    	return null;
	    }
		try {
			Query query = (Query)getQuery(sql, param, true);
			//query.addEntity(entity);
			list = query.getResultList();
			log.info("通过SQL查询数据[成功]");
		} catch (Exception e) {
			log.info("通过SQL查询数据[失败]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> querySqlToEntity(String sql, Map<String, Object> param, 
			Class<? extends BaseBeanImpl> entity, Integer start, Integer rows) throws DaoException {
		if (StringUtils.isEmpty(sql)) {
			return null;
		}
		try {
			Query query = (Query) getQuery(sql, param, true);
			if (null != start && null != rows) {
				query.setFirstResult(start);
				query.setMaxResults(rows);
			}
			//query.addEntity(entity);

			List<E> list = query.getResultList();

			log.info("通过SQL查询数据[成功]");
			return list;
		} catch (Exception e) {
			log.info("通过SQL查询数据[失败]");
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage(), e.getCause());
		}
	}
}
