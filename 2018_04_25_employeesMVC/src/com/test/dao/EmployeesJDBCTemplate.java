package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.test.domain.Employees;

public class EmployeesJDBCTemplate implements EmployeesDAO {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public List<Employees> list(String key) {
		/*
		CREATE VIEW empView AS
		SELECT 
		    e.eid AS eid,
		    name_,
		    ssn,
		    hiredate,
		    phone,
		    e.reg_id,
		    reg_name,
		    e.dept_id,
		    dept_name,
		    e.job_id,
		    job_title,
		    basicpay,
		    extrapay,
		    basicpay + extrapay AS pay,
		    picName
		FROM
		    employees e
		        INNER JOIN
		    regions r ON e.reg_id = r.reg_id
		        INNER JOIN
		    departments d ON e.dept_id = d.dept_id
		        INNER JOIN
		    jobs j ON e.job_id = j.job_id
		    LEFT OUTER JOIN empPictures p ON e.eid = p.eid
		ORDER BY eid;
		*/
		
		String sql = "SELECT eid, name_, ssn, hiredate, phone, reg_id, reg_name, dept_id, dept_name, job_id, job_title, basicpay, extrapay, pay, picName FROM  empView";
		switch (key) {
		case "eid":
			sql += " ORDER BY eid";
			break;
		case "name_":
			sql += " ORDER BY name_";
			break;
		case "ssn":
			sql += " ORDER BY ssn";
			break;
		case "phone":
			sql += " ORDER BY phone";
			break;
		case "hiredate":
			sql += " ORDER BY hiredate";
			break;
		case "reg_name":
			sql += " ORDER BY reg_name";
			break;
		case "dept_name":
			sql += " ORDER BY dept_name";
			break;
		case "job_title":
			sql += " ORDER BY job_title";
			break;
		case "basicpay":
			sql += " ORDER BY basicpay";
			break;
		case "extrapay":
			sql += " ORDER BY extrapay";
			break;
		case "pay":
			sql += " ORDER BY pay";
			break;
		}

		List<Employees> result = this.jdbcTemplate.query(sql, new EmployeesMapper());

		return result;
	}

	@Override
	public List<Employees> list(String key, String value) {
		/*
		CREATE VIEW empView AS
		SELECT 
		    e.eid AS eid,
		    name_,
		    ssn,
		    hiredate,
		    phone,
		    e.reg_id,
		    reg_name,
		    e.dept_id,
		    dept_name,
		    e.job_id,
		    job_title,
		    basicpay,
		    extrapay,
		    basicpay + extrapay AS pay,
		    picName
		FROM
		    employees e
		        INNER JOIN
		    regions r ON e.reg_id = r.reg_id
		        INNER JOIN
		    departments d ON e.dept_id = d.dept_id
		        INNER JOIN
		    jobs j ON e.job_id = j.job_id
		    LEFT OUTER JOIN empPictures p ON e.eid = p.eid
		ORDER BY eid;
		*/
		
		String sql = "SELECT eid, name_, ssn, hiredate, phone, reg_id, reg_name, dept_id, dept_name, job_id, job_title, basicpay, extrapay, pay, picName FROM  empView";
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
		
		List<Employees> result = this.jdbcTemplate.query(sql, new EmployeesMapper(), value);

		return result;
	}
	
	
	@Override
	public List<Employees> regionList() {
		String sql = "SELECT reg_id, reg_name, (SELECT COUNT(*) FROM employees WHERE reg_id = r.reg_id) AS deleteCheck FROM regions r ORDER BY reg_id";

		List<Employees> result = this.jdbcTemplate.query(sql, new RowMapper<Employees>() {

			@Override
			public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {

				Employees e = new Employees();
				e.setReg_id(rs.getString("reg_id"));
				e.setReg_name(rs.getString("reg_name"));
				e.setDeleteCheck(rs.getInt("deleteCheck"));

				return e;
			}

		});

		return result;
	}

	@Override
	public List<Employees> departmentList() {
		String sql = "SELECT dept_id, dept_name, (SELECT COUNT(*) FROM employees WHERE dept_id = d.dept_id) AS deleteCheck FROM departments d ORDER BY dept_id";

		List<Employees> result = this.jdbcTemplate.query(sql, new RowMapper<Employees>() {

			@Override
			public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {

				Employees e = new Employees();
				e.setDept_id(rs.getString("dept_id"));
				e.setDept_name(rs.getString("dept_name"));
				e.setDeleteCheck(rs.getInt("deleteCheck"));

				return e;
			}

		});

		return result;
	}

	@Override
	public List<Employees> jobList() {
		String sql = "SELECT job_id, job_title, minBasicPay, (SELECT COUNT(*) FROM employees WHERE job_id = d.job_id) AS deleteCheck FROM jobs d ORDER BY job_id";

		List<Employees> result = this.jdbcTemplate.query(sql, new RowMapper<Employees>() {

			@Override
			public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {

				Employees e = new Employees();
				e.setJob_id(rs.getString("job_id"));
				e.setJob_title(rs.getString("job_title"));
				e.setMinBasicPay(rs.getInt("minBasicPay"));
				e.setDeleteCheck(rs.getInt("deleteCheck"));

				return e;
			}

		});

		return result;
	}

	@Override
	public int add(Employees e) throws DataAccessException {
		String sql = "INSERT INTO employees  (eid, name_, ssn, hiredate, phone, reg_id, dept_id, job_id, basicpay, extrapay) VALUES ((SELECT * FROM (SELECT CONCAT('E', LPAD(IFNULL(SUBSTRING(MAX(eid), 2), 0) + 1, 4, 0)) AS newMid FROM employees) m), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = this.jdbcTemplate.update(sql, e.getName_(), e.getSsn(), e.getHiredate(), e.getPhone(), e.getReg_id(), e.getDept_id(), e.getJob_id(), e.getBasicpay(), e.getExtrapay());
		return result;
	}
	

	@Override
	public int regionInsert(String reg_name) throws DataAccessException {
		String sql = "INSERT INTO regions (reg_id, reg_name) VALUES ((SELECT * FROM (SELECT CONCAT('REG', LPAD(IFNULL(SUBSTRING(MAX(reg_id), 4), 0) + 1, 2, 0)) AS newMid FROM regions) m), ?)";
		int result = this.jdbcTemplate.update(sql, reg_name);
		return result;
	}


	@Override
	public int departmentInsert(String dept_name) throws DataAccessException  {
		String sql = "INSERT INTO departments (dept_id, dept_name) VALUES ((SELECT * FROM (SELECT CONCAT('DEPT', LPAD(IFNULL(SUBSTRING(MAX(dept_id), 5), 0) + 1, 2, 0)) AS newMid FROM departments) m), ?)";
		int result = this.jdbcTemplate.update(sql, dept_name);
		return result;
	}


	@Override
	public int jobInsert(Employees e) throws DataAccessException  {
		String sql = "INSERT INTO jobs (job_id, job_title, minBasicPay) VALUES ((SELECT * FROM (SELECT CONCAT('JOB', LPAD(IFNULL(SUBSTRING(MAX(job_id), 4), 0) + 1, 2, 0)) AS newMid FROM jobs) m), ?, ?)";
		int result = this.jdbcTemplate.update(sql, e.getJob_title(), e.getMinBasicPay());
		return result;
	}


	@Override
	public int getminbasicpay(String job_id) {
		String sql = "SELECT minbasicpay FROM jobs WHERE job_id=?";
		int result = this.jdbcTemplate.queryForObject(sql, Integer.class, job_id);
		return result;
	}


	@Override
	public int pictureAdd(Employees e) throws DataAccessException {
		String sql = "INSERT INTO empPictures (eid, picName) VALUES (?, ?)";
		int result = this.jdbcTemplate.update(sql, e.getEid(), e.getPicName());
		return result;
	}

	@Override
	public int pictureModify(Employees e) throws DataAccessException {
		String sql = "UPDATE empPictures SET picName=? WHERE eid=?";
		int result = this.jdbcTemplate.update(sql, e.getPicName(), e.getEid());
		return result;
	}

}
