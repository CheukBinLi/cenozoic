package com.freepig.cenozoic.code.util;

import java.io.Serializable;
import java.util.List;

public interface HibernateUtil {

	public <T> List<T> getList(Class<?> c) throws Throwable;

	public <T> List<T> getListByHQL(String hql, Object... params) throws Throwable;

	public <T> List<T> getListBySQL(String sql, Object... params) throws Throwable;

	public <T> List<T> getListByHqlQueryName(String hql, Object... params) throws Throwable;

	public <T> List<T> getListBySqlQueryName(String sql, Object... params) throws Throwable;

	public <T> T get(Class<T> clazz, Serializable obj) throws Throwable;

	public void delete(Object obj) throws Throwable;

	public int deleteList(List<?> list) throws Throwable;

	public void update(Object o) throws Throwable;

	public int saveList(List<?> list) throws Throwable;

	public <T> T save(Object t) throws Throwable;
}
