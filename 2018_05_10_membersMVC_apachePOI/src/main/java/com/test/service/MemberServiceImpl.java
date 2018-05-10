package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.Member;
import com.test.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO memberDAO;

	@Override
	public List<Member> list(Map<String, String> map) {
		return this.memberDAO.list(map);
	}

	@Override
	public int memberAdd(Member m)  throws Exception {
		return this.memberDAO.memberAdd(m);
	}

	@Override
	public List<Member> deptList() {
		return this.memberDAO.deptList();
	}

	@Override
	public int totalCount() {
		return this.memberDAO.totalCount();
	}

	@Override
	public int memberRemove(Member m) throws Exception {
		return this.memberDAO.memberRemove(m);
	}

	@Override
	public int memberModify(Member m) throws Exception {
		return this.memberDAO.memberModify(m);
	}

}
