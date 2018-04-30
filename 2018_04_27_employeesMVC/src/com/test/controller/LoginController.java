package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		//id_, pw_ 수신
		//데이터베이스 질의
		//성공 여부 판단
		//실패시->loginFail
		//성공시->전용 세션(관리자, 강사, 수강생) 생성 및 전용 페이지 이동
		Login result = this.jdbcTemplate.login(login);
		
		String uri = "redirect:/loginFail";
		if (result != null) {
			int grade = result.getGrade();
			if (grade == 0) {
				session.setAttribute("adminLoginInfo", result);
				uri = "redirect:/employee/list";
			} else if (grade == 1) {
				session.setAttribute("instructorLoginInfo", result);
				uri = "redirect:/instructor/list";
			} else if (grade == 2) {
				session.setAttribute("studentLoginInfo", result);
				uri = "redirect:/student/list";
			} else {
				System.out.println("잘못된 등급 정보입니다.");
			}
		}
		return uri;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		//세션 소멸
		session.invalidate();
		
		return "redirect:/logoutForm";
	}

	@RequestMapping("/logoutForm")
	public String logoutForm() {
		
		return "login/logoutForm";  ///WEB-INF/views/login/logoutForm.jsp
	}

}
