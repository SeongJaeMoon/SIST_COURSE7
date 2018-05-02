package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.domain.GuestBook;

public class GuestBookMapper implements RowMapper<GuestBook> {

	@Override
	public GuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {

		String gid = rs.getString("gid");
		String name_ = rs.getString("name_");
		String content = rs.getString("content");
		String regDate = rs.getString("regDate");

		GuestBook gb = new GuestBook();
		gb.setGid(gid);
		gb.setName_(name_);
		gb.setContent(content);
		gb.setRegDate(regDate);
		
		return gb;
	}

}
