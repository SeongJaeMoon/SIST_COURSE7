package com.test.ex005;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex005/beans.xml");
		
		HelloWorld hello = (HelloWorld) context.getBean("HelloWorld");
		
		hello.getMessage();

		((ClassPathXmlApplicationContext)context).close();
		
	}

}
