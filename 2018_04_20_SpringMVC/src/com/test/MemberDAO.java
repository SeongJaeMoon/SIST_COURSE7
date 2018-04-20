package com.test;

import java.util.List;

public interface MemberDAO {

	//��ü ��� ��¿� �޼ҵ�
	public List<Member> list();
		
	//��ü ��� �� �˻� ��� ��¿� �޼ҵ�
	public List<Member> list(String key, String value);
	
	//ȸ������ �Է¿� �޼ҵ�
	public int memberAdd(Member m);
	
	//�μ� ���� �б�� �޼ҵ�
	public List<Member> deptList();
	
	//��ü �ο��� Ȯ�ο� �޼ҵ�
	public int totalCount();
	
	// ȸ�� ���� ���� �޼ҵ�
	public int memberRemove(String mid_);
	
	// ȸ�� ���� ���� �޼ҵ�
	public int memberModify(Member m);
}
