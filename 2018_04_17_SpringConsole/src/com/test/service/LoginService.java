package com.test.service;

import org.springframework.context.ApplicationContext;

import com.test.dao.LoginJDBCTemplate;
import com.test.model.Login;

import java.util.*;

public class LoginService {

	private LoginJDBCTemplate loginJDBCTemplate;

	public LoginService(ApplicationContext context) {
		this.loginJDBCTemplate = (LoginJDBCTemplate) context.getBean("loginJDBCTemplate");
	}

	public Login login(Scanner sc) {
		Login ret = null;
		Login login = new Login();
		
		System.out.print("아이디:");
		String id_ = sc.nextLine();
		System.out.print("패스워드:");
		String pw_ = sc.nextLine();
		login.setId_(id_);
		login.setPw_(pw_);
		
		ret = this.loginJDBCTemplate.login(login);
		
		return ret;
	}
}
