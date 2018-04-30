package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dao.LoginJDBCTemplate;
import com.test.domain.Login;

@Controller
public class LoginController {
	
	@Autowired
	private LoginJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/main")
	public String loginForm() {
		return "login/loginForm";  ///WEB-INF/views/login/loginForm.jsp
	}

	@RequestMapping("/login")
	public String login(Login login, HttpSession session) {
		String uri = "redirect:/loginFail";
		try {
			
			Login result = this.jdbcTemplate.login(login);
			int grade = result.getGrade();
			if (grade == 0) {
				session.setAttribute("adminLoginInfo", result);
				uri = "redirect:/admin/list";
			} else if (grade == 1) {
				session.setAttribute("instructorLoginInfo", result);
				uri = "redirect:/instructor/list";
			} else {
				System.out.println("잘못된 등급 정보입니다.");
			}
			
			
		} catch (DataAccessException e) {
			System.out.printf("로그인 실패!: id(%s), pw(%s)", login.getId_(), login.getPw_());
		}
		return uri;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/logoutForm";
	}

	@RequestMapping("/logoutForm")
	public String logoutForm() {
		return "login/logoutForm";  ///WEB-INF/views/login/logoutForm.jsp
	}
	
	
	@RequestMapping("/loginFail")
	public String loginFail() {
		return "login/loginFail";  ///WEB-INF/views/login/loginFail.jsp
	}


}
