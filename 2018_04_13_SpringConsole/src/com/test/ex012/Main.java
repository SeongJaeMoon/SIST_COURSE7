package com.test.ex012;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		//Sample Ŭ������ Ư�� �޼ҵ� ȣ�� ���� �߰�
		//->Spring Framework�� ���� ��ü ���� ����
		//->beans.xml
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex012/beans.xml");

		TextEditor textEditor = (TextEditor)context.getBean("textEditor");
		textEditor.getSpellChecker();
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
