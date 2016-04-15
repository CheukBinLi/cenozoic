package com.freepig.cenozoic.web.front.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.freepig.cenozoic.code.dbmapper.service.UserService;

@Scope("prototype")
@Controller
@RequestMapping({ "/system/" })
public class SystemController extends AbstractController {

	@RequestMapping("{path}")
	public ModelAndView basePath(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path) throws IOException {
		//		String url = request.getParameter("url");
		//		if ("proxy".equals(path)) {
		//			return new ModelAndView("proxy", getParams(request)).addObject("data", JspProxy.getProxy(url));
		//		}
		System.err.println(path);
		return new ModelAndView(path, getParams(request));
	}

}
