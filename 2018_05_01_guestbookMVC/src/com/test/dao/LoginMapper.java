package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.domain.Login;

public class LoginMapper  implements RowMapper<Login> {

	@Override
	public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
		Login login = new Login();
		login.setId(rs.getString("id"));
		login.setName_(rs.getString("name_"));
		login.setPhone(rs.getString("phone"));
		login.setEmail(rs.getString("email"));
		login.setRegDate(rs.getString("regDate"));
		return login;
	}

}
