package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.AdminGuestBook;
import com.test.persistence.AdminGuestBookDAO;

@Service
public class AdminGuestBookServiceImpl implements AdminGuestBookService {

	@Inject
	private AdminGuestBookDAO adminGuestBookDAO;
	
	@Override
	public List<AdminGuestBook> guestBookList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int blind(AdminGuestBook gb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AdminGuestBook> picList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int pictureAdd(AdminGuestBook gb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pictureRemove(AdminGuestBook gb) {
		// TODO Auto-generated method stub
		return 0;
	}

}
