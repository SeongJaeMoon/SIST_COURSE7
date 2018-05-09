package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.domain.AdminGuestBook;

public interface AdminGuestBookService {

	public List<AdminGuestBook> guestBookList(Map<String, Object> map);
	public int totalCount();
	public int blind(AdminGuestBook gb);
	public List<AdminGuestBook> picList(Map<String, Object> map);
	public int pictureAdd(AdminGuestBook gb);
	public int pictureRemove(AdminGuestBook gb);
	
}
