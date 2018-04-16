package com.test.ex018;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex018/beans.xml");

		Student student = (Student) context.getBean("student");
		System.out.println("Name : " + student.getName());
		System.out.println("Age : " + student.getAge());

		((ClassPathXmlApplicationContext)context).close();
	}

}
