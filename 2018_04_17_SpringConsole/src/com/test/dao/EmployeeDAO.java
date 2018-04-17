package com.test.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.test.model.Employee;

public interface EmployeeDAO {

	public List<Employee>list(String key);
	public List<Employee>list(String key, String value);
	public int empDelete(String eid) throws DataAccessException;
	public int empAdd(Employee emp) throws DataAccessException;
	
	public List<Employee> regionList();
	public int regionAdd(String reg_name) throws DataAccessException;
	public int regionDelete(String reg_id) throws DataAccessException;
	
	public List<Employee> deptList();
	public int deptAdd(String dept_name) throws DataAccessException;
	public int deptDelete(String dept_id) throws DataAccessException;
	
	public List<Employee> jobList();
	public int jobAdd(String job_title) throws DataAccessException;
	public int jobDelete(String job_id) throws DataAccessException;
	
}
