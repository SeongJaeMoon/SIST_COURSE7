package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dao.LoginJDBCTemplate;
import com.test.domain.Login;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/login")
	public String login(Login login, HttpSession session) {
		
		//id, pw를 이용한 로그인 액션 처리
		//->성공한 경우 세션 객체 생성
		Login l = null;
		String uri = "redirect:/login/loginFail";
		try {
			l = this.jdbcTemplate.login(login);
			session.setAttribute("adminLoginInfo", l);
			uri = "redirect:/admin/booklist";
		}catch(DataAccessException e) {
			System.out.println(e.getMessage());
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
