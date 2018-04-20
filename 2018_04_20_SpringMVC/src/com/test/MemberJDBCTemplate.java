package com.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberJDBCTemplate implements MemberDAO{

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Member> list() {
		/*
		 * CREATE VIEW membersView AS SELECT mid_, name_, phone, email, regDate,
		 * deptName FROM members INNER JOIN dept USING(deptID) ORDER BY mid_;
		 */
		
		String sql = "SELECT mid_, name_, phone, email, regDate, deptName FROM membersview ORDER BY mid_";
		List<Member> ret = this.jdbcTemplate.query(sql, new MemberMapper());
		return ret;
	}

	@Override
	public List<Member> list(String key, String value) {
		/*
		 * CREATE VIEW membersview AS SELECT mid_, name_, phone, email, regDate,
		 * deptName FROM members INNER JOIN dept USING(deptID) ORDER BY mid_;
		 */
		
		String sql = "SELECT mid_, name_, phone, email, regDate, deptName FROM membersview";
		
		switch (key) {
		case "ALL":
			sql += "";
			break;
		case "mid_":
			sql += " WHERE mid_ = ?";
			break;
		case "name_":
			sql += " WHERE INSTR(LOWER(name_), LOWER(?))";
			break;
		case "phone":
			sql += " WHERE INSTR(phone, ?)";
			break;
		case "email":
			sql += " WHERE INSTR(email, ?)";
			break;
		case "regDate":
			sql += " WHERE INSTR(regDate, ?)";
			break;
		case "deptName":
			sql += " WHERE INSTR(deptName, ?)";
			break;
		}
		sql += " ORDER BY mid_";
		
		List<Member> ret = null;
		
		if(key.equals("ALL")) {
			ret = this.jdbcTemplate.query(sql, new MemberMapper());
		}else {
			ret = this.jdbcTemplate.query(sql, new Object[]{value}, new MemberMapper());
		}
		
		return ret;
	}

	@Override
	public int memberAdd(Member m) {
		String sql = "INSERT INTO members (mid_, name_, phone, email, regDate, deptId) VALUES ((SELECT * FROM (SELECT CONCAT('M', LPAD(IFNULL(SUBSTRING(MAX(mid_), 2), 0) + 1, 2, 0)) AS newMid FROM members) m) , ?, ?, ?, ?, ?)";

		int ret = 0;
		try {
			ret = this.jdbcTemplate.update(sql, m.getName_(), m.getPhone(), m.getEmail(), m.getRegDate(), m.getDeptId());
		}catch(Exception e) {
	
		}

		return ret;
	}

	@Override
	public List<Member> deptList() {
		String sql = "SELECT deptId, deptName FROM dept ORDER BY deptId";
		
		List<Member> ret = this.jdbcTemplate.query(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int row) throws SQLException {
				Member m = new Member();
				m.setDeptId(rs.getString("deptId"));
				m.setDeptName(rs.getString("deptName"));
				
				return m;
			}
		});
		
		return ret;
	}

	@Override
	public int totalCount() {
		String sql = "SELECT COUNT(*) AS totalCount FROM membersview";
		
		int ret = this.jdbcTemplate.queryForObject(sql, Integer.class);
		
		return ret;
	}

	@Override
	public int memberRemove(String mid_) {
		String sql = "DELETE FROM members WHERE mid_=?";
		int ret = 0;
		try {
			ret = this.jdbcTemplate.update(sql, mid_);
		}catch(Exception e) {
			
		}
		return ret;
	}

	@Override
	public int memberModify(Member m) {
		String sql = "UPDATE members SET name_ = ?, phone = ?, email = ?, regDate = ?, deptId = ? WHERE mid_ = ?";
		int ret = 0;
		try {
			ret = this.jdbcTemplate.update(sql, m.getName_(), m.getPhone(), m.getEmail(), m.getRegDate(), m.getDeptId(), m.getMid_());
		}catch(Exception e) {	
		}
		return ret;
		
	}

}
