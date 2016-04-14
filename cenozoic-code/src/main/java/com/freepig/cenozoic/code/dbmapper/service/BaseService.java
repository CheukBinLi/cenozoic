package com.freepig.cenozoic.code.dbmapper.service;

import java.util.List;
import java.util.Map;

public interface BaseService<entity> {

	entity getByPk(entity obj) throws Throwable;

	List<entity> getList() throws Throwable;

	entity save(entity obj) throws Throwable;

	void update(entity obj) throws Throwable;

	void delete(entity obj) throws Throwable;

}
