package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.test.domain.GuestBook;

public class GuestBookJDBCTemplate implements GuestBookDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public int guestBookAdd(GuestBook gb) throws DataAccessException {
		String sql = "INSERT INTO guestbook (gid, name_, pw, content, regDate, clientIP, blind) VALUES ((SELECT * FROM (SELECT CONCAT('G', LPAD(IFNULL(SUBSTRING(MAX(gid), 2), 0) + 1, 5, 0)) AS newGid FROM guestbook) g), ?, ?, ?, NOW(), ?,  0)";
		int result = this.jdbcTemplate.update(sql, gb.getName_(), gb.getPw(), gb.getContent(), gb.getClientIP());
		return result;
	}

	@Override
	public List<GuestBook> guestBookList(String key, String value) {
		//주의) 일반사용자 모드로 작성해야 한다.
		String sql = "SELECT gid, name_, content, regDate FROM guestbook WHERE blind = 0";
		switch (key) {
		case "ALL":
			sql += "";
			break;
		case "name_":
			sql += " AND INSTR(name_, ?)";
			break;
		case "content":
			sql += " AND INSTR(content, ?)";
			break;
		case "regDate":
			sql += " AND INSTR(regDate, ?)";
			break;
		}
		sql += " ORDER BY gid DESC";
		
		List<GuestBook> result =  null;
		if (key.equals("ALL")) {
			result = this.jdbcTemplate.query(sql, new GuestBookMapper());
		} else {
			result = this.jdbcTemplate.query(sql, new GuestBookMapper(), value);
		}
		return result;
	}

	@Override
	public List<GuestBook> guestBookList(String key, String value, int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalCount() {
		//주의) 일반사용자 모드로 작성해야 한다.
		String sql = "SELECT COUNT(*) AS totalCount FROM guestbook WHERE blind = 0";
		int result = this.jdbcTemplate.queryForObject(sql, Integer.class);
		return result;
	}

	@Override
	public List<GuestBook> picList() {
		String sql = "SELECT pid, picName, picContent FROM pictureList ORDER BY pid";
		List<GuestBook> result =   this.jdbcTemplate.query(sql, new RowMapper<GuestBook>() {

			@Override
			public GuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {

				String pid = rs.getString("pid");
				String picName = rs.getString("picName");
				String picContent = rs.getString("picContent");

				GuestBook gb = new GuestBook();
				gb.setPid(pid);
				gb.setPicName(picName);
				gb.setPicContent(picContent);
				
				return gb;
			}
			
		});
		return result;
	}

	@Override
	public int guestbookRemove(GuestBook gb) throws DataAccessException {
		//주의) 일반사용자 모드로 작성해야 한다.
		String sql = "DELETE FROM guestbook WHERE blind=0 AND gid=? AND pw=?";
		int result = this.jdbcTemplate.update(sql, gb.getGid(), gb.getPw());
		return result;
	}

}
