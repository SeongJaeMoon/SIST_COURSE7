package com.test.dao;

import java.util.List;

import com.test.domain.AdminGuestBook;

public interface AdminGuestBookDAO {

	public List<AdminGuestBook> guestBookList(String key, String value);
	public int totalCount();
	public int blind(AdminGuestBook gb);
	public List<AdminGuestBook> picList(String key, String value);
	public int pictureAdd(AdminGuestBook gb);
	public int pictureRemove(String pid);
	
}
