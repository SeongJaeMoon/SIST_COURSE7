package com.test.ex021;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex021/beans.xml");
		
		TextEditor textEditor = (TextEditor) context.getBean("textEditor");
		
		textEditor.spellCheck();
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
