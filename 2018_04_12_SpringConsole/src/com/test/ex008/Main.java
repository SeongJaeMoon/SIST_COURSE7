package com.test.ex008;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex008/beans.xml");
		
		HelloWorld hello1 = (HelloWorld) context.getBean("HelloWorld");
		hello1.getMessage();


		HelloIndia hello2 = (HelloIndia) context.getBean("HelloIndia");
		hello2.getMessage();

		((ClassPathXmlApplicationContext)context).close();
		
	}

}
