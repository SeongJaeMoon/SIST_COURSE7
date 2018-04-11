package com.test2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		// Spring Framework �غ�
		// -> IoC Container
		// ->��ü ����->��Ÿ������(xml)
		// ->����� ��û->���� ����(DI)->��ü ����
		ApplicationContext context = new FileSystemXmlApplicationContext("C:\\Temp\\beans.xml");

		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
