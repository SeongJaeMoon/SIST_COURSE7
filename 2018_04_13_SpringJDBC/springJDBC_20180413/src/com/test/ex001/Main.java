package com.test.ex001;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex001/beans.xml");

		((ClassPathXmlApplicationContext) context).close();

	}

}
