package com.test.ex021;

import javax.inject.Inject;

public class TextEditor {
	
	@Inject
	private SpellChecker spellChecker;

	public void spellCheck() {
		this.spellChecker.checkSpelling();
	}
}