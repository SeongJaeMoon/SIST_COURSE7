package com.test.ex011;

public class Sample {
	
	//���� ��ü�� �����ϱ� ���� ���� �߰�
	//->������ or setter
	//->����� �ڷ����� Interface�� ����
	private Super sub;
	
	
	public Sample() {
	}
	public Sample(Super sub) {
		this.sub = sub;
	}
	
	
	public void method() {
		//�������̽� Super�� �ڽ� Ŭ����(SubClass01 �Ǵ� SubClass02)�� ���� �������̵� �޼ҵ� ȣ��
		this.sub.method();
	}

}
