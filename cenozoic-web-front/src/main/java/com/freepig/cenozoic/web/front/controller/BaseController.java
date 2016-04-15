package com.freepig.cenozoic.web.front.controller;

import java.io.IOException;
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

import com.freepig.cenozoic.code.dbmapper.entity.User;
import com.freepig.cenozoic.code.dbmapper.service.UserService;

@Scope("prototype")
@Controller
@RequestMapping({ "/*", "/", "/**/**" })
public class BaseController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping("{path}")
	public ModelAndView basePath(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path) throws IOException {
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
	public Object aa() throws Throwable {
		String[] o = new String[] { "123", "321", "123" };
		//		userService.checkUser("13122222222");
		return o;
	}

	@ResponseBody
	@RequestMapping("user_check")
	public Object userCheck(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return userService.checkUser(getParams(request)) ? 1 : 0;
	}

}
