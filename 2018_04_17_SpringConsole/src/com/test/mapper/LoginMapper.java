package com.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.model.Login;

public class LoginMapper implements RowMapper<Login> {

	@Override
	public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Login l = new Login();
		l.setId_(rs.getString("id_"));
		l.setPw_(rs.getString("pw_"));
		l.setGrade(rs.getInt("grade"));

		return l;
	}
}
