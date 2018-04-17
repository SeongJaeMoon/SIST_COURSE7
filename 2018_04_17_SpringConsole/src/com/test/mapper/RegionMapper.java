package com.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.model.Employee;

public class RegionMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee e = new Employee();
		e.setReg_id(rs.getString("reg_id"));
		e.setReg_name(rs.getString("reg_name"));

		return e;
	}
}
