package com.freepig.cenozoic.code.dbmapper.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freepig.cenozoic.code.dbmapper.dao.UserDao;
import com.freepig.cenozoic.code.dbmapper.entity.User;
import com.freepig.cenozoic.code.util.HibernateUtil;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateUtil hibernateUtil;

	public User get(Integer id) throws Throwable {
		return hibernateUtil.get(User.class, id);
	}

	public List<User> getList() throws Throwable {
		return hibernateUtil.getList(User.class);
	}

	public User save(User o) throws Throwable {
		return hibernateUtil.save(o);
	}

	public void update(User o) throws Throwable {
		hibernateUtil.update(o);
	}

	public void delete(User o) throws Throwable {
		hibernateUtil.delete(o);
	}

	@SuppressWarnings("rawtypes")
	public User login(Map<String, Object> params) throws Throwable {
		List list = hibernateUtil.getListByHqlQueryName(hibernateUtil.queryNameFormat(User.class, "login"), params);
		if (null != list)
			return (User) (list.size() > 0 ? list.get(0) : null);
		return null;
	}

	//	@SuppressWarnings({ "rawtypes", "unchecked" })
	//	public List<User> getListByHql(String hql, Object params) throws Throwable {
	//		List list = hibernateUtil.getListByHQL("from com.freepig.cenozoic.code.dbmapper.entity.User u", null);
	//		return list;
	//	}

}
