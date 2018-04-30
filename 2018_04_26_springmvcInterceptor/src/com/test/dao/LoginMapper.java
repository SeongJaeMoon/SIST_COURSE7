package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.domain.Login;

public class LoginMapper implements RowMapper<Login> {

	@Override
	public Login mapRow(ResultSet rs, int rowNum) throws SQLException {

		Login login = new Login();
		login.setId_(rs.getString("id_"));
		login.setGrade(rs.getInt("grade"));
		
		return login;
	}

}
