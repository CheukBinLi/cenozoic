package com.freepig.cenozoic.web.front.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.freepig.cenozoic.code.util.QueryFactory;

@Scope("prototype")
@Controller
@RequestMapping({ "/*", "/**", "/", "" })
public class BaseController {

	@Autowired
	private QueryFactory queryFactory;

	@RequestMapping("{path}")
	public ModelAndView basePath(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path) throws IOException {
		//		String url = request.getParameter("url");
		//		if ("proxy".equals(path)) {
		//			return new ModelAndView("proxy", getParams(request)).addObject("data", JspProxy.getProxy(url));
		//		}
		return new ModelAndView(path, getParams(request));
	}

	@ResponseBody
	@RequestMapping("x1")
	public Object aa() {
		String[] o = new String[] { "123", "321", "123" };
		//		com.fasterxml.jackson.core.JsonProcessingException
		System.out.println(queryFactory.getXQL("com.freepig.cenozoic.code.entity.dbmapper.user.xxx", true));
		System.err.println(queryFactory.getXQL("com.freepig.cenozoic.code.entity.dbmapper.userAAXXA.xxx", true));
		System.err.println(queryFactory.getXQL("com.freepig.cenozoic.code.entity.dbmapper.userAAXXA.xxx", false));
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
