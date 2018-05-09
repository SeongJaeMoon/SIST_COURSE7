package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.domain.Login;
import com.test.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String login(Login login, HttpSession session) {
		
		Login l = null;
		String uri = "redirect:/login/loginFail";
		l = this.loginService.login(login);
		if (l != null) {
			session.setAttribute("adminLoginInfo", l);
			uri = "redirect:/admin/booklist";
		}
		
		return uri;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login/logoutForm";
	}

	@RequestMapping("/logoutForm")
	public String logoutForm() {
		
		return "login/logoutForm"; //WEB-INF/views/login/logoutForm.jsp
	}

	@RequestMapping("/loginFail")
	public String loginFail() {
		
		return "login/loginFail"; //WEB-INF/views/login/loginFail.jsp
	}

	
}
