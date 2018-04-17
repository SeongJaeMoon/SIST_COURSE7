package com.test.dao;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.test.mapper.DeptMapper;
import com.test.mapper.EmployeeMapper;
import com.test.mapper.JobMapper;
import com.test.mapper.RegionMapper;
import com.test.model.Employee;

public class EmployeeJDBCTemplate implements EmployeeDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	
	@Override
	public List<Employee> list(String key) {
		/*
		 SELECT eid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, basicpay, extrapay, (basicpay+extrapay) AS pay
		 FROM employees e 
		 INNER JOIN jobs j ON e.job_id = j.job_id
		 INNER JOIN departments d ON e.dept_id = d.dept_id
		 INNER JOIN regions r ON e.reg_id = r.reg_id 
		 ORDER BY "key";
		 */
		
		String sql = "SELECT eid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, basicpay, extrapay, pay FROM employeesView";
		
		switch(key) {
		case "eid":
			sql += " ORDER BY eid";
			break;
		case "name_":
			sql += " ORDER BY name_";
			break;
		case "dept_name": 
			sql += " ORDER BY dept_name";
			break;
		case "job_title":
			sql += " ORDER BY job_title";
			break;
		case "reg_name":
			sql += " ORDER BY reg_name";
			break;
		}
		
		List<Employee>ret = this.jdbcTemplateObject.query(sql, new EmployeeMapper());
		
		return ret;
	}

	@Override
	public List<Employee> list(String key, String value) {
		/*
		 SELECT eid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, basicpay, extrapay, (basicpay+extrapay) AS pay
		 FROM employees e 
		 INNER JOIN jobs j ON e.job_id = j.job_id
		 INNER JOIN departments d ON e.dept_id = d.dept_id
		 INNER JOIN regions r ON e.reg_id = r.reg_id 
		 ORDER BY "";
		 */
		String sql = "SELECT eid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, basicpay, extrapay, pay FROM employeesView";
		
		switch (key) {
		case "eid":
			sql += " WHERE eid = ?";
			break;
		case "name_":
			sql += " WHERE INSTR(name_, ?) > 0";
			break;
		case "reg_name":
			sql += " WHERE INSTR(reg_name, ?) > 0";
			break;
		case "dept_name":
			sql += " WHERE INSTR(dept_name, ?) > 0";
			break;
		case "job_title":
			sql += " WHERE INSTR(job_title, ?) > 0";
			break;
		}
		sql += " ORDER BY eid";
		List<Employee>ret = this.jdbcTemplateObject.query(sql, new Object[] {value}, new EmployeeMapper());
		return ret;
	}

	@Override
	public int empDelete(String eid) throws DataAccessException{
		String sql = "DELETE FROM employees WHERE eid = ?";
		/*int ret = this.jdbcTemplateObject.queryForObject(sql, new Object[] {eid}, Integer.class);*/
		int ret = this.jdbcTemplateObject.update(sql, eid);
		return ret;
	}

	@Override
	public int empAdd(Employee emp) throws DataAccessException{
		String sql = "INSERT INTO employees (eid, name_, ssn, hiredate, phone, reg_id, dept_id, job_id, basicpay, extrapay) VALUES ((SELECT CONCAT('E',LPAD(IFNULL(SUBSTR(MAX(eid),4), 0) + 1, 2, 0)) AS newEid FROM employees e), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int ret = this.jdbcTemplateObject.update(sql, emp.getName_(), emp.getSsn(), emp.getHiredate(), emp.getPhone(), emp.getReg_id(), emp.getDept_id(), emp.getJob_id(), emp.getBasicpay(), emp.getExtrapay());
		return ret;
	}

	@Override
	public List<Employee> regionList(){
		String sql = "SELECT reg_id, reg_name, (SELECT COUNT(*) FROM employees WHERE reg_id = r.reg_id) AS deleteCheck FROM regions r ORDER BY reg_id";
		List<Employee>ret = this.jdbcTemplateObject.query(sql, new RegionMapper()); 
		return ret;
	}

	@Override
	public int regionAdd(String reg_name) throws DataAccessException{
		
		String sql = "INSERT INTO regions (reg_id, reg_name) VALUES ((SELECT * FROM (SELECT CONCAT('REG', LPAD(IFNULL(SUBSTRING(MAX(reg_id), 4), 0) + 1, 2, 0)) AS newMid FROM regions) m), ?)";
		int ret = this.jdbcTemplateObject.update(sql, reg_name);
		return ret;
	}

	@Override
	public int regionDelete(String reg_id) throws DataAccessException{
		
		String sql = "DELETE FROM regions WHERE reg_id=?";
		int ret = this.jdbcTemplateObject.update(sql, reg_id);
		return ret;
	}

	@Override
	public List<Employee> deptList(){
		
		String sql = "SELECT dept_id, dept_name, (SELECT COUNT(*) FROM employees WHERE dept_id = d.dept_id) AS deleteCheck FROM departments d ORDER BY dept_id";
		List<Employee> ret = this.jdbcTemplateObject.query(sql, new DeptMapper());
		return ret;
	}

	@Override
	public int deptAdd(String dept_name) throws DataAccessException{
		
		String sql = "INSERT INTO departments (dept_id, dept_name) VALUES ((SELECT * FROM (SELECT CONCAT('DEPT', LPAD(IFNULL(SUBSTRING(MAX(dept_id), 5), 0) + 1, 2, 0)) AS newMid FROM departments) m), ?)";
		int ret = this.jdbcTemplateObject.update(sql, dept_name);
		return ret;
	}

	@Override
	public int deptDelete(String dept_id) throws DataAccessException{
		
		String sql = "DELETE FROM departments WHERE dept_id=?";
		int ret = this.jdbcTemplateObject.update(sql, dept_id);
		return ret;
	}

	@Override
	public List<Employee> jobList() {
		
		String sql = "SELECT job_id, job_title, (SELECT COUNT(*) FROM employees WHERE job_id = d.job_id) AS deleteCheck FROM jobs d ORDER BY job_id";
		List<Employee> ret = this.jdbcTemplateObject.query(sql, new JobMapper());
		return ret;
	}

	@Override
	public int jobAdd(String job_title) throws DataAccessException {
		
		String sql = "INSERT INTO jobs (job_id, job_title) VALUES ((SELECT * FROM (SELECT CONCAT('JOB', LPAD(IFNULL(SUBSTRING(MAX(job_id), 4), 0) + 1, 2, 0)) AS newMid FROM jobs) m), ?)";
		int ret = this.jdbcTemplateObject.update(sql, job_title);
		return ret;
	}

	@Override
	public int jobDelete(String job_id) throws DataAccessException {
		
		String sql = "DELETE FROM jobs WHERE job_id=?";
		int ret = this.jdbcTemplateObject.update(sql, job_id);
		return ret;
	}

	
}
