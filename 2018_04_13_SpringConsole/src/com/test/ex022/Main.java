package com.test.ex022;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(TextEditorConfig.class);

		TextEditor textEditor = context.getBean(TextEditor.class);
		textEditor.spellCheck();

		((AnnotationConfigApplicationContext) context).close();

	}

}
