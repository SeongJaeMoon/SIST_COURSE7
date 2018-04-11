package com.test4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// Spring Framework �غ�
		// -> IoC Container
		// ->��ü ����->��Ÿ������(xml)
		// ->����� ��û->���� ����(DI)->��ü ����
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/test4/beans.xml");

		Member mem1 = (Member) context.getBean("m2");
		System.out.println(mem1.memeberInfo());
		mem1.setId("hi");
		Member mem2 = (Member) context.getBean("m2");
		System.out.println(mem2.memeberInfo());
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
