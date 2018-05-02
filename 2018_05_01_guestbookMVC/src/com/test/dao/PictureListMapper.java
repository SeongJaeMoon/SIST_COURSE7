package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.domain.AdminGuestBook;

public class PictureListMapper implements RowMapper<AdminGuestBook> {

	@Override
	public AdminGuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {

		String pid = rs.getString("pid");
		String picName = rs.getString("picName");
		String picContent = rs.getString("picContent");

		AdminGuestBook gb = new AdminGuestBook();
		gb.setPid(pid);
		gb.setPicName(picName);
		gb.setPicContent(picContent);
		
		return gb;
	}

}
