package com.freepig.cenozoic.code.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class AbstractHibernateUtil implements HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private QueryFactory queryFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public <T> List<T> getList(Class<?> c) throws Throwable {
		return getList(c, -1, -1);
	}

	public <T> List<T> getList(Class<?> c, int page, int size) throws Throwable {
		Query query = getSession().createQuery(String.format("FROM %s a", c.getSimpleName()));
		List list = page > 0 ? page(query, page, size).list() : query.list();
		return null == list ? null : list;
	}

	public <T> List<T> getListByHQL(String hql, Object... params) throws Throwable {
		Query query = getSession().createQuery(hql);
		List list = fillParams(query, params).list();
		return null == list ? null : list;
	}

	public <T> List<T> getListBySQL(String sql, Object... params) throws Throwable {
		Query query = getSession().createSQLQuery(sql);
		List list = fillParams(query, params).list();
		return null == list ? null : list;
	}

	public <T> List<T> getListByHqlQueryName(String queryName, Map<String, Object> params) throws Throwable {
		return getListByHqlQueryName(queryName, params, -1, -1);
	}

	public <T> List<T> getListByHqlQueryName(String queryName, Map<String, Object> params, int page, int size) throws Throwable {
		Query query = fillParams(getSession().createQuery(queryFactory.getXQL(queryName, params, true)), params);
		List list = page > 0 ? page(query, page, size).list() : query.list();
		return null == list ? null : list;
	}

	public <T> List<T> getListBySqlQueryName(String queryName, Map<String, Object> params) throws Throwable {
		return getListBySqlQueryName(queryName, params, -1, -1);
	}

	public <T> List<T> getListBySqlQueryName(String queryName, Map<String, Object> params, int page, int size) throws Throwable {
		Query query = fillParams(getSession().createSQLQuery(queryFactory.getXQL(queryName, params, true)), params);
		List list = page > 0 ? page(query, page, size).list() : query.list();
		return null == list ? null : list;
	}

	public <T> T get(Class<T> clazz, Serializable obj) throws Throwable {
		return getSession().get(clazz, obj);
	}

	public void delete(Object obj) throws Throwable {
		getSession().delete(obj);
	}

	public int deleteList(List<?> list) throws Throwable {
		int count = 0;
		Session s = getSession();
		for (int i = 0, len = list.size(); i < len; i++) {
			s.delete(list.get(i));
			if (i % 50 == 0) {
				s.flush();
				s.clear();
			}
			count++;
		}
		return count;
	}

	public void update(Object o) throws Throwable {
		getSession().update(o);
	}

	public int updateList(List<?> list) throws Throwable {
		int count = 0;
		Session s = getSession();
		for (int i = 0, len = list.size(); i < len; i++) {
			s.update(list.get(i));
			if (i % 50 == 0) {
				s.flush();
				s.clear();
			}
			count++;
		}
		return count;
	}

	public int saveList(List<?> list) throws Throwable {
		int count = 0;
		Session s = getSession();
		for (int i = 0, len = list.size(); i < len; i++) {
			s.save(list.get(i));
			if (i % 50 == 0) {
				s.flush();
				s.clear();
			}
			count++;
		}
		return count;
	}

	public <T> T save(Object o) throws Throwable {
		Object result = getSession().save(o);
		return (T) (null == result ? null : result);
	}

	public void saveOrUpdate(Object t) throws Throwable {
		getSession().saveOrUpdate(t);
	}

	protected Query fillParams(Query q, Object... o) {
		if (null == o || null == q) {
			return q;
		}
		for (int i = 0, len = o.length; i < len; i++) {
			q.setParameter(i, o[i]);
		}
		return q;
	}

	protected Query fillParams(Query q, Map<String, ?> o) {
		if (null == o || null == q) {
			return q;
		}
		for (Entry<String, ?> en : o.entrySet())
			q.setParameter(en.getKey(), en.getValue());
		return q;
	}

	protected Query page(Query q, int pageNum, int size) {
		if (pageNum >= 0 && size >= 0) {
			q.setFirstResult(size * (pageNum - 1));
			q.setMaxResults(size);
		}
		return q;
	}

}
