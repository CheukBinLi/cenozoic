package com.freepig.cenozoic.code.util;

import java.io.Serializable;
import java.util.List;

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

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public <T> List<T> getList(Class<?> c) throws Throwable {
		List list = getSession().createQuery(String.format("FROM %s a", c.getSimpleName())).list();
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

	public <T> List<T> getListByHqlQueryName(String hql, Object... params) throws Throwable {
		System.err.println("末实现");
		return null;
	}

	public <T> List<T> getListBySqlQueryName(String sql, Object... params) throws Throwable {
		System.err.println("末实现");
		return null;
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

	protected Query fillParams(Query q, Object... o) {
		if (o == null) {
			return q;
		}
		for (int i = 0; i < o.length; i++) {
			q.setParameter(i, o[i]);
		}
		return q;
	}

}
