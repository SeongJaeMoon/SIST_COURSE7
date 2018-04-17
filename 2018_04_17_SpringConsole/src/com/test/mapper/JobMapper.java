package com.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.model.Employee;

public class JobMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee e = new Employee();
		e.setJob_id(rs.getString("job_id"));
		e.setJob_title(rs.getString("job_title"));

		return e;
	}
}
