package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.GuestBook;

@Repository
public class GuestBookDAOImpl implements GuestBookDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<GuestBook> guestBookList(Map<String, Object> map) {
		return this.sqlSession.selectList("com.test.mapper.GuestBookMapper.guestBookList", map);
	}

	@Override
	public int totalCount() {
		return this.sqlSession.selectOne("com.test.mapper.GuestBookMapper.totalCount");
	}

	@Override
	public List<GuestBook> picList() {
		return this.sqlSession.selectList("com.test.mapper.GuestBookMapper.picList");
	}

	@Override
	public int guestBookAdd(GuestBook gb) throws Exception {
		return this.sqlSession.insert("com.test.mapper.GuestBookMapper.guestBookAdd", gb);
	}

	@Override
	public int guestbookRemove(GuestBook gb)  throws Exception {
		return this.sqlSession.delete("com.test.mapper.GuestBookMapper.guestbookRemove", gb);
	}

}
