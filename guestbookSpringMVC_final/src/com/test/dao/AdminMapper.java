package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.domain.AdminGuestBook;

public class AdminMapper implements RowMapper<AdminGuestBook> {

	@Override
	public AdminGuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {

		String gid = rs.getString("gid");
		String name_ = rs.getString("name_");
		String content = rs.getString("content");
		String regDate = rs.getString("regDate");
		String clientIP = rs.getString("clientIP");
		int blind = rs.getInt("blind");

		AdminGuestBook gb = new AdminGuestBook();
		gb.setGid(gid);
		gb.setName_(name_);
		gb.setContent(content);
		gb.setRegDate(regDate);
		gb.setClientIP(clientIP);
		gb.setBlind(blind);
		
		return gb;
	}

}
