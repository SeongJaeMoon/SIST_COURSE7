package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.GuestBook;
import com.test.persistence.GuestBookDAO;

@Service
public class GuestBookServiceImpl implements GuestBookService {

	@Inject
	private GuestBookDAO guestBookDAO;
	
	@Override
	public List<GuestBook> guestBookList(Map<String, Object> map) {
		return this.guestBookDAO.guestBookList(map);
	}

	@Override
	public int totalCount() {
		return this.guestBookDAO.totalCount();
	}

	@Override
	public List<GuestBook> picList() {
		return this.guestBookDAO.picList();
	}

	@Override
	public int guestBookAdd(GuestBook gb) throws Exception {
		return this.guestBookDAO.guestBookAdd(gb);
	}

	@Override
	public int guestbookRemove(GuestBook gb) throws Exception {
		return this.guestBookDAO.guestbookRemove(gb);
	}

}
