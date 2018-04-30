package com.test.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.test.domain.Login;

public class LoginJDBCTemplate implements LoginDAO {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Login login(Login login) {
		String sql = "SELECT id_, grade FROM login WHERE id_=? AND pw_=?";
		Login result = this.jdbcTemplate.queryForObject(sql, new LoginMapper(), login.getId_(), login.getPw_());
		return result;
	}
	
}
