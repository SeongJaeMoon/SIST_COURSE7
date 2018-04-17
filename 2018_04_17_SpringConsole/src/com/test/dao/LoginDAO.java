package com.test.dao;

import org.springframework.dao.DataAccessException;

import com.test.model.Login;

public interface LoginDAO {
	public Login login(Login l) throws DataAccessException;
}
