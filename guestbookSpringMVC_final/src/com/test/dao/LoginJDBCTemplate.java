package com.test.dao;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.test.domain.Login;

public class LoginJDBCTemplate implements LoginDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Login login(Login login) throws DataAccessException {
		/*
		CREATE OR REPLACE VIEW loginInfoView
		AS
		SELECT mid_, id, pw, name_, phone, email, regDate 
			FROM loginInfo l INNER JOIN members m USING(id);
		*/
		String sql = "SELECT id, name_, phone, email, regDate FROM loginInfoView WHERE id=? AND pw=?";
		Login result = this.jdbcTemplate.queryForObject(sql, new LoginMapper(), login.getId(), login.getPw());
		return result;
	}

}
