package com.test;

import java.util.List;

public interface MemberDAO {

	//전체 명단 출력용 메소드
	public List<Member> list();
		
	//전체 출력 및 검색 결과 출력용 메소드
	public List<Member> list(String key, String value);
	
	//회원정보 입력용 메소드
	public int memberAdd(Member m);
	
	//부서 정보 읽기용 메소드
	public List<Member> deptList();
	
	//전체 인원수 확인용 메소드
	public int totalCount();
	
	// 회원 정보 삭제 메소드
	public int memberRemove(String mid_);
	
	// 회원 정보 수정 메소드
	public int memberModify(Member m);
}
