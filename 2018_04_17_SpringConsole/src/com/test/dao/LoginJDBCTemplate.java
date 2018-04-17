package com.test.dao;

import javax.sql.DataSource;
import java.util.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.test.mapper.LoginMapper;
import com.test.model.Login;

public class LoginJDBCTemplate implements LoginDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Login login(Login l) throws DataAccessException {
		
		String sql = "SELECT id_, grade, pw_ FROM login WHERE id_ = ? AND pw_ = ?";
		
		Login ret = this.jdbcTemplateObject.queryForObject(sql, new Object[]{l.getId_(), l.getPw_()}, new LoginMapper());
		
		return ret;
	}

}
