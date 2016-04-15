package com.freepig.cenozoic.code.dbmapper.dao;

import java.util.Map;

import com.freepig.cenozoic.code.dbmapper.entity.User;

public interface UserDao extends BaseDao<User, Integer> {

	/***
	 * 
	 * @param params(phone,password)
	 * @return
	 */
	User login(Map<String, Object> params) throws Throwable;

	int checkUser(Map<String, Object> params) throws Throwable;

}
