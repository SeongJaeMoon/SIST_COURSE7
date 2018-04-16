package com.test.ex014;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex014/beans.xml");
		JavaCollection jc = (JavaCollection) context.getBean("javaCollection");

		jc.getAddressList();
		jc.getAddressSet();
		jc.getAddressMap();
		jc.getAddressProp();
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
