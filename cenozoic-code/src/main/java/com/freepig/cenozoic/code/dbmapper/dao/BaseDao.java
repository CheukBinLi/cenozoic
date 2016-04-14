package com.freepig.cenozoic.code.dbmapper.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<entity, ID> {

	entity get(ID o) throws Throwable;

	List<entity> getList() throws Throwable;

	entity save(entity o) throws Throwable;

	void update(entity o) throws Throwable;

	void delete(entity o) throws Throwable;

	//	List<entity> getListByHql(String hql, Object params) throws Throwable;

}
