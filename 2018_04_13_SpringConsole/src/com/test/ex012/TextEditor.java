package com.test.ex012;

public class TextEditor {
	private SpellChecker spellChecker;

	// a setter method to inject the dependency.
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Inside setSpellChecker.");
		this.spellChecker = spellChecker;
	}

	// a getter method to return spellChecker
	public SpellChecker getSpellChecker() {
		return this.spellChecker;
	}

	public void spellCheck() {
		this.spellChecker.checkSpelling();
	}
}