package com.test.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.test.domain.AdminGuestBook;

public class AdminJDBCTemplate implements AdminGuestBookDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<AdminGuestBook> guestBookList(String key, String value) {

		// 주의) 관리자 모드로 작성해야 한다.
		String sql = "SELECT gid, name_, content, regDate, clientIP, blind FROM guestbook";
		switch (key) {
		case "ALL":
			break;
		case "name_":
			sql += " WHERE INSTR(name_, ?)";
			break;

		case "content":
			sql += " WHERE INSTR(content, ?)";
			break;

		case "regDate":
			sql += " WHERE INSTR(CAST(regDate AS CHAR), ?)";
			break;
		}
		sql += " ORDER BY gid DESC";
		
		List<AdminGuestBook> result = null;
		if (key.equals("ALL")) {
			result = this.jdbcTemplate.query(sql, new AdminMapper());
		} else {
			result = this.jdbcTemplate.query(sql, new AdminMapper(), value);
		}
		
		return result;
	}

	@Override
	public int totalCount() {
		// 주의) 관리자 모드로 작성해야 한다.
		String sql = "SELECT COUNT(*) AS totalcount FROM guestbook";
		int result = this.jdbcTemplate.queryForObject(sql, Integer.class);
		return result;
	}

	@Override
	public int blind(AdminGuestBook gb) throws DataAccessException {
		String sql = "UPDATE guestbook SET blind=? WHERE gid=?";
		int result = this.jdbcTemplate.update(sql, gb.getBlind(), gb.getGid());
		return result;
	}

	@Override
	public List<AdminGuestBook> picList(String key, String value) {
		String sql = "SELECT pid, picName, picContent FROM pictureList";
		if (key.equals("pid")) {
			sql += " WHERE pid =? ";
		}
		sql += " ORDER BY pid";
		
		List<AdminGuestBook> result = null;
		if (key.equals("pid")) {
			result = this.jdbcTemplate.query(sql, new PictureListMapper(), value);
		} else {
			result = this.jdbcTemplate.query(sql, new PictureListMapper());
		}
		
		return result;
	}

	@Override
	public int pictureAdd(AdminGuestBook gb) throws DataAccessException {
		String sql = "INSERT INTO pictureList (pid, picName, picContent) VALUES ((SELECT * FROM (SELECT CONCAT('P', LPAD(IFNULL(SUBSTRING(MAX(pid), 2), 0) + 1, 3, 0)) AS newPid FROM pictureList) p), ?, ?)";
		int result = this.jdbcTemplate.update(sql, gb.getPicName(), gb.getPicContent());
		return result;
	}

	@Override
	public int pictureRemove(String pid) throws DataAccessException {
		String sql = "DELETE FROM picturelist WHERE pid = ?";
		int result = this.jdbcTemplate.update(sql, pid);
		return result;
	}

}
