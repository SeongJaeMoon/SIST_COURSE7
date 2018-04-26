package com.test.dao;

import java.util.List;

import com.test.domain.Employees;

public interface EmployeesDAO {
	
	public List<Employees> list(String key);
	public List<Employees> list(String key, String value);
	public List<Employees> regionList();
	public List<Employees> departmentList();
	public List<Employees> jobList();
	
	public int add(Employees emp);
	public int regionInsert(String reg_name);
	public int departmentInsert(String dept_name);
	public int jobInsert(Employees emp);
	
	public int getminbasicpay(String job_id);
	
	public int pictureAdd(Employees emp);
	public int pictureModify(Employees emp);

}
