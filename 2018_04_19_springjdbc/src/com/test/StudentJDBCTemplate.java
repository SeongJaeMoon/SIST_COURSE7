package com.test;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate implements StudentDAO {

	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Student> studentList() {
		String sql = "SELECT id, name_, age FROM student";
		List<Student> result = this.jdbcTemplateObject.query(sql, new StudentMapper());
		return result;
	}


}
