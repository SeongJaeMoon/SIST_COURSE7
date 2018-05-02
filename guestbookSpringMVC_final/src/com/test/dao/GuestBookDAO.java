package com.test.dao;

import java.util.List;

import com.test.domain.GuestBook;

public interface GuestBookDAO {

	public int guestBookAdd(GuestBook gb);
	public List<GuestBook> guestBookList(String key, String value);
	public List<GuestBook> guestBookList(String key, String value, int offset, int count);
	public int totalCount();
	public List<GuestBook> picList();
	public int guestbookRemove(GuestBook gb);
	
}
