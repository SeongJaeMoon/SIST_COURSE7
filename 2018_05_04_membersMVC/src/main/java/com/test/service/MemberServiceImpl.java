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
		return this.memberDAO.totalCount();
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
