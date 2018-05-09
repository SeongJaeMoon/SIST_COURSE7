package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.AdminGuestBook;

@Repository
public class AdminGuestBookDAOImpl implements AdminGuestBookDAO {

	@Inject
	private SqlSession sqlSession;

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
