package com.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.model.Employee;

public class DeptMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee e = new Employee();
		e.setDept_id(rs.getString("dept_id"));
		e.setDept_name(rs.getString("dept_name"));

		return e;
	}
}
