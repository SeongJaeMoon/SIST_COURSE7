package com.test.ex020;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex020/beans.xml");

		Student student = (Student) context.getBean("student");
		System.out.println("Name : " + student.getName());
		System.out.println("Age : " + student.getAge());

		((ClassPathXmlApplicationContext)context).close();
	}

}
