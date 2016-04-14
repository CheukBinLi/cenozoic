package com.freepig.cenozoic.code.dbmapper.service;

import java.util.List;
import java.util.Map;

public interface BaseService2<entity> {

	entity getByPk(Map<String, Object> params) throws Throwable;

	List<entity> getList() throws Throwable;

	entity save(Map<String, Object> params) throws Throwable;

	void update(Map<String, Object> params) throws Throwable;

	void delete(Map<String, Object> params) throws Throwable;

}
