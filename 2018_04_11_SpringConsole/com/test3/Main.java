package com.test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		// Spring Framework �غ�
		// -> IoC Container
		// ->��ü ����->��Ÿ������(xml)
		// ->����� ��û->���� ����(DI)->��ü ����
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/test3/beans.xml");

		Member mem1 = (Member) context.getBean("m1");
		System.out.println(mem1.memeberInfo());
		Member mem2 = (Member) context.getBean("m2");
		System.out.println(mem2.memeberInfo());
		Member mem3 = (Member) context.getBean("m3");
		System.out.println(mem3.memeberInfo());
		
	
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
