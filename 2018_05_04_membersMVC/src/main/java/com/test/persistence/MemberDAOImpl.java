package com.test.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.Member;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<Member> list(Map<String, String> map) {
		return this.sqlSession.selectList("com.test.mapper.MemberMapper.list", map);
	}

	@Override
	public int memberAdd(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> deptList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalCount() {
		return this.sqlSession.selectOne("com.test.mapper.MemberMapper.totalCount");
	}

	@Override
	public int memberRemove(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memberModify(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
