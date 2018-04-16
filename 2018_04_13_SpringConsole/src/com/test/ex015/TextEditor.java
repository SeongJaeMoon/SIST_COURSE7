package com.test.ex015;

public class TextEditor {
	
	private SpellChecker spellChecker;

	public void setSpellChecker(SpellChecker spellChecker) {
		 System.out.println("Inside setSpellChecker." );
		this.spellChecker = spellChecker;
	}

	public void spellCheck() {
		this.spellChecker.checkSpelling();
	}
}