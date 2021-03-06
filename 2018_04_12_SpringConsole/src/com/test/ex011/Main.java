package com.test.ex011;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		//Sample 클래스의 특정 메소드 호출 과정 추가
		//->Spring Framework에 의한 객체 관리 설정
		//->beans.xml
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex011/beans.xml");
		
		Sample sample = (Sample)context.getBean("Sample");
		
		sample.method();
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
