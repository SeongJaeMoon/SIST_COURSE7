package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.domain.Employees;

public class EmployeesMapper implements RowMapper<Employees> {

	@Override
	public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employees e = new Employees();
		e.setEid(rs.getString("eid"));
		e.setName_(rs.getString("name_"));
		e.setSsn(rs.getString("ssn"));
		e.setHiredate(rs.getString("hiredate"));
		e.setPhone(rs.getString("phone"));
		e.setReg_id(rs.getString("reg_id"));
		e.setDept_id(rs.getString("dept_id"));
		e.setJob_id(rs.getString("job_id"));
		e.setReg_name(rs.getString("reg_name"));
		e.setDept_name(rs.getString("dept_name"));
		e.setJob_title(rs.getString("job_title"));
		e.setBasicpay(rs.getInt("basicpay"));
		e.setExtrapay(rs.getInt("extrapay"));
		e.setPay(rs.getInt("pay"));
		return e;
	}

}
