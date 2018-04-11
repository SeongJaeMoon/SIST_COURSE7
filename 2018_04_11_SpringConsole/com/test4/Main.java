package com.test4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// Spring Framework 준비
		// -> IoC Container
		// ->객체 관리->메타데이터(xml)
		// ->사용자 요청->의존 주입(DI)->객체 제공
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/test4/beans.xml");

		Member mem1 = (Member) context.getBean("m2");
		System.out.println(mem1.memeberInfo());
		mem1.setId("hi");
		Member mem2 = (Member) context.getBean("m2");
		System.out.println(mem2.memeberInfo());
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
