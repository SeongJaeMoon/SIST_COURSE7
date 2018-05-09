package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.domain.GuestBook;

public interface GuestBookService {

	public List<GuestBook> guestBookList(Map<String, Object> map);
	public int totalCount();
	public List<GuestBook> picList();
	public int guestBookAdd(GuestBook gb) throws Exception;
	public int guestbookRemove(GuestBook gb) throws Exception;
	
}
