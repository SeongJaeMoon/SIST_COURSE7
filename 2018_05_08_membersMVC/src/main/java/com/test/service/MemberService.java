package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.domain.Member;

public interface MemberService {
	
	public List<Member> list(Map<String, String> map);
	public int memberAdd(Member m)  throws Exception;
	public List<Member> deptList();
	public int totalCount();
	public int memberRemove(Member m) throws Exception;
	public int memberModify(Member m) throws Exception;
	
}
