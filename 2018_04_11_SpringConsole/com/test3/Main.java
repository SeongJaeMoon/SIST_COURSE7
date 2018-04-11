package com.test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		// Spring Framework 준비
		// -> IoC Container
		// ->객체 관리->메타데이터(xml)
		// ->사용자 요청->의존 주입(DI)->객체 제공
		
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
