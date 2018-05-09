package com.test.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.Login;
import com.test.persistence.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDAO loginDAO;
	
	@Override
	public Login login(Login l) {
		return this.loginDAO.login(l);
	}

}
