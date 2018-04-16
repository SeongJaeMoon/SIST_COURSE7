package com.test.ex022;

public class TextEditor {
	
	private SpellChecker spellChecker;
	
	public TextEditor() {
	}
	
	public TextEditor(SpellChecker spellChecker) {
		this.spellChecker = spellChecker;
	}

	public void spellCheck() {
		this.spellChecker.checkSpelling();
	}
}