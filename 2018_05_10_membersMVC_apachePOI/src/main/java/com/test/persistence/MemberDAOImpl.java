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
	public int memberAdd(Member m) throws Exception {
		return this.sqlSession.insert("com.test.mapper.MemberMapper.memberAdd", m);
	}

	@Override
	public List<Member> deptList() {
		return this.sqlSession.selectList("com.test.mapper.MemberMapper.deptList");
	}

	@Override
	public int totalCount() {
		return this.sqlSession.selectOne("com.test.mapper.MemberMapper.totalCount");
	}

	@Override
	public int memberRemove(Member m) throws Exception {
		return this.sqlSession.delete("com.test.mapper.MemberMapper.memberRemove", m);
	}

	@Override
	public int memberModify(Member m) throws Exception {
		return this.sqlSession.update("com.test.mapper.MemberMapper.memberModify", m);
	}

}
