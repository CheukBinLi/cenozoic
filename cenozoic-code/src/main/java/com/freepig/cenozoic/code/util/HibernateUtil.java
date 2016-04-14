package com.freepig.cenozoic.code.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface HibernateUtil {

	/***
	 * 列表
	 * @param c 实体类文件
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getList(Class<?> c) throws Throwable;

	/***
	 *  带分页列表
	 * @param c
	 * @param page
	 * @param size
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getList(Class<?> c, int page, int size) throws Throwable;

	/***
	 *  HQL列表查询
	 * @param hql hql
	 * @param params 参数(?)
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListByHQL(String hql, Object... params) throws Throwable;

	/***
	 * 
	 *  带分页HQL列表查询
	 * @param hql hql
	 * @param page 页码
	 * @param size 条目
	 * @param params 参数( ? )
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListByHQL(String hql, int page, int size, Object... params) throws Throwable;

	/***
	 * SQL列表查询
	 * @param sql sql
	 * @param params 参数( ? )
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListBySQL(String sql, Object... params) throws Throwable;

	/***
	 * 
	 *  带分页SQL列表查询
	 * @param ql sql
	 * @param page 页码
	 * @param size 条目
	 * @param params 参数( ?  )
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListBySQL(String sql, int page, int size, Object... params) throws Throwable;

	/***
	 * query模板查询
	 * @param queryName 查询语句名 
	 * @param params 参数( :name  )
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListByHqlQueryName(String queryName, Map<String, Object> params) throws Throwable;

	/***
	 * 
	 * query模板查询
	 * * @param queryName 查询语句名 
	 * @param params 参数( :name  )
	 * @param page
	 * @param size
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListByHqlQueryName(String queryName, Map<String, Object> params, int page, int size) throws Throwable;

	/***
	 * query模板查询
	 * * @param queryName 查询语句名 
	 * @param params 参数( :name  )
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListBySqlQueryName(String queryName, Map<String, Object> params) throws Throwable;

	/***
	 * query模板查询
	 * * @param queryName 查询语句名 
	 * @param params 参数( :name  )
	 * @param page
	 * @param size
	 * @return
	 * @throws Throwable
	 */
	public <T> List<T> getListBySqlQueryName(String queryName, Map<String, Object> params, int page, int size) throws Throwable;

	/***
	 * 通过ID查询
	 * @param clazz
	 * @param obj
	 * @return
	 * @throws Throwable
	 */
	public <T> T get(Class<T> clazz, Serializable obj) throws Throwable;

	/***
	 * 通过ID删除
	 * @param obj
	 * @throws Throwable
	 */
	public void delete(Object obj) throws Throwable;

	/***
	 * 批量删除
	 * @param list
	 * @return
	 * @throws Throwable
	 */
	public int deleteList(List<?> list) throws Throwable;

	/***
	 * 更新
	 * @param o
	 * @throws Throwable
	 */
	public void update(Object o) throws Throwable;

	/***
	 * 批量更新
	 * @param o
	 * @throws Throwable
	 */
	public int updateList(List<?> list) throws Throwable;

	/***
	 * 批量插入
	 * @param list
	 * @return
	 * @throws Throwable
	 */
	public int saveList(List<?> list) throws Throwable;

	/***
	 * 插入
	 * @param t
	 * @return
	 * @throws Throwable
	 */
	public <T> T save(Object t) throws Throwable;

	/***
	 * 插入或更新
	 * @param t
	 * @throws Throwable
	 */
	public void saveOrUpdate(Object t) throws Throwable;
}
