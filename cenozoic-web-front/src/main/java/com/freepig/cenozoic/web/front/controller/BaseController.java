package com.freepig.cenozoic.web.front.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.freepig.cenozoic.code.dbmapper.entity.User;
import com.freepig.cenozoic.code.dbmapper.service.UserService;
import com.freepig.cenozoic.code.util.QueryFactory;

import freemarker.template.TemplateException;

@Scope("prototype")
@Controller
@RequestMapping({ "/*", "/**", "/", "" })
public class BaseController {

	@Autowired
	private QueryFactory queryFactory;

	@Autowired
	private UserService userService;
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping("{path}")
	public ModelAndView basePath(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path) throws IOException {
		//		String url = request.getParameter("url");
		//		if ("proxy".equals(path)) {
		//			return new ModelAndView("proxy", getParams(request)).addObject("data", JspProxy.getProxy(url));
		//		}
		return new ModelAndView(path, getParams(request));
	}

	@RequestMapping("x2")
	public void aa1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		User u = new User("13122222222", "AABBCC");
		//		userService.save(u);
		u = userService.getByPk(new User(7));
		Map<String, Object> x = getParams(request);
		x.remove("id");
		u = userService.login(x);
		System.err.println(String.format("id:%s name:%s phone:%s", u.getId(), u.getName(), u.getPassword()));
	}

	@ResponseBody
	@RequestMapping("x1")
	public Object aa() throws TemplateException, IOException {
		String[] o = new String[] { "123", "321", "123" };
		//		com.fasterxml.jackson.core.JsonProcessingException
		System.out.println(queryFactory.getXQL("com.freepig.cenozoic.code.entity.dbmapper.user.xxx", null, true));
		System.err.println(queryFactory.getXQL("com.freepig.cenozoic.code.entity.dbmapper.userAAXXA.xxx", null, true));
		System.err.println(queryFactory.getXQL("com.freepig.cenozoic.code.entity.dbmapper.userAAXXA.xxx", null, false));
		return o;
	}

	protected final Map<String, Object> getParams(HttpServletRequest request) {
		Enumeration<String> en = request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		String name;
		while (en.hasMoreElements()) {
			name = en.nextElement();
			map.put(name, request.getParameter(name));
		}
		return map;
	}

}
