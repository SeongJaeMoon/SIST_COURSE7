package com.test.ex019;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
	
	@Autowired
	private SpellChecker spellChecker;

	public void spellCheck() {
		this.spellChecker.checkSpelling();
	}
}