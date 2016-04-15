package com.freepig.cenozoic.web.front.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.freepig.cenozoic.code.util.SessionNames;

public class SystemInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean isLogin = null == request.getSession().getAttribute(SessionNames.USER_ID);
		if (!isLogin)
			return true;
		//		request.getRequestDispatcher("/index.html").forward(request, response);
		response.sendRedirect("../index.html");
		return false;
	}

}
