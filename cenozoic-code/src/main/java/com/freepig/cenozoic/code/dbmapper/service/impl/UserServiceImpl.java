package com.freepig.cenozoic.code.dbmapper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freepig.cenozoic.code.dbmapper.dao.UserDao;
import com.freepig.cenozoic.code.dbmapper.entity.User;
import com.freepig.cenozoic.code.dbmapper.service.UserService;
import com.freepig.cenozoic.code.util.ObjectFill;

@Component
public class UserServiceImpl extends ObjectFill implements UserService {

	@Autowired
	private UserDao userDao;

	public User getByPk(Map<String, Object> params) throws NumberFormatException, Throwable {
		if (params.containsKey("id"))
			return userDao.get(Integer.valueOf(params.get("id").toString()));
		return null;
	}

	public List<User> getList() throws Throwable {
		return userDao.getList();
	}

	public User save(Map<String, Object> params) throws IllegalArgumentException, IllegalAccessException, InstantiationException, Throwable {
		return userDao.save(fillObject(User.class, params));
	}

	public void update(Map<String, Object> params) throws Throwable {
		userDao.update(fillObject(User.class, params));
	}

	public void delete(Map<String, Object> params) throws NumberFormatException, Throwable {
		if (params.containsKey("id"))
			userDao.delete(new User(Integer.valueOf(params.get("id").toString())));
	}

	public User login(Map<String, Object> params) throws Throwable {
		//		return (User) userDao.getListByHql(null, null).get(0);
		return userDao.login(params);
	}

	public User getByPk(User obj) throws Throwable {
		return userDao.get(obj.getId());
	}

	public User save(User obj) throws Throwable {
		return userDao.save(obj);
	}

	public void update(User obj) throws Throwable {
		userDao.update(obj);
	}

	public void delete(User obj) throws Throwable {
		userDao.delete(obj);

	}

}
