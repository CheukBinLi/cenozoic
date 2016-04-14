package com.freepig.cenozoic.code.dbmapper.service;

import java.util.Map;

import com.freepig.cenozoic.code.dbmapper.entity.User;

public interface UserService extends BaseService<User> {

	User login(Map<String, Object> params) throws Throwable;

}
