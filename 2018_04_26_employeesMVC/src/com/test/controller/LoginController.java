package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		
		return "login/loginForm";  ///WEB-INF/views/login/loginForm.jsp
	}

	@RequestMapping("/login")
	public String login(HttpSession session) {
		
		//id_, pw_ 수신
		//데이터베이스 질의
		//성공 여부 판단
		//실패시->loginFail
		//성공시->전용 세션(관리자, 강사) 생성 및 전용 페이지 이동
		
		session.setAttribute("instructorLoginInfo", "TEST");
		
		return "redirect:/instructor/list";
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
