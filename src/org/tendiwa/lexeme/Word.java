package org.tendiwa.lexeme;

import java.util.LinkedList;
import java.util.List;

/**
 * An entry from a .words file. Contains possible form that this word can take (lexemes) and modifiers for those forms.
 */
class Word {
private List<Modifier> permanentModifiers = new LinkedList<>();
private List<Lexeme> localizationToModifiers = new LinkedList<>();
private Lexeme baseForm;

public void addPermanentModifier(Modifier modifier) {
	permanentModifiers.add(modifier);
}
@Override
public String toString() {
	return baseForm.toString();
}

public void addWordForm(String wordFormString, List<Modifier> modifiers) {
	Lexeme lexeme = new Lexeme(wordFormString, modifiers);
	if (baseForm == null) {
		baseForm = lexeme;
	}
	localizationToModifiers.add(lexeme);
}

public Lexeme getBaseForm() {
	return baseForm;
}

public Lexeme findWordForm(Modifier... modifiers) {
	int maxMatchingModifiers = 0;
	Lexeme bestSuitedLexeme = null;
	for (Lexeme form : localizationToModifiers) {
		int matchingModifiers = 0;
		for (Modifier modifier : modifiers) {
			if (form.getModifiers().contains(modifier)) {
				matchingModifiers++;
				if (matchingModifiers > maxMatchingModifiers) {
					maxMatchingModifiers = matchingModifiers;
					bestSuitedLexeme = form;
				}
			}
		}
	}
	if (maxMatchingModifiers == 0) {
		return baseForm;
	}
//		if (bestSuitedLexeme == null) {
//			throw new RuntimeException("A lexeme of word "+baseForm+" for modifiers "+Arrays.asList(modifiers)+" could not be found");
//		}
	return bestSuitedLexeme;
}

}
