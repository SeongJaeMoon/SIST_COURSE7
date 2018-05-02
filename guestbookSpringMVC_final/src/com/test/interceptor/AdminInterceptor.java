package com.test.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		Boolean login = false;
		if (session != null) {
			if (session.getAttribute("adminLoginInfo") != null) {
				login = true;
			}
		}

		if (login) {
		} else {
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title></title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Error:access denied!");
			out.println("</body>");
			out.println("</html>");
		}

		return login;
	}

}
