package com.test.ex011;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		//Sample Ŭ������ Ư�� �޼ҵ� ȣ�� ���� �߰�
		//->Spring Framework�� ���� ��ü ���� ����
		//->beans.xml
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex011/beans.xml");
		
		Sample sample = (Sample)context.getBean("Sample");
		
		sample.method();
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
