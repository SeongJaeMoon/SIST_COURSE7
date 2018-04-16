package com.test.ex016;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/test/ex016/beans.xml");
		
		TextEditor textEditor = (TextEditor) context.getBean("TextEditor");
		
		textEditor.spellCheck();
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
