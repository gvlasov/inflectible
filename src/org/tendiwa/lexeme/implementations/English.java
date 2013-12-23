package org.tendiwa.lexeme.implementations;

import org.tendiwa.lexeme.Language;
import org.tendiwa.lexeme.LexemeTemplate;
import org.tendiwa.lexeme.Localizable;
import org.tendiwa.lexeme.Modifier;

import java.util.List;

public class English extends Language {
public English() {
	super("English", "en_US");
}

protected Modifier stringToModifier(String modifier) {
	return English.Modifiers.valueOf(modifier);
}

@Override
public String getMissingWord() {
	return "[parameter_missing]";
}

@Override
public List<Modifier> processTemplate(LexemeTemplate lexemeTemplate, Localizable localizable) {
	return null;
}

public enum Modifiers implements Modifier {
	Ger,
	Sing,
	Plur,
	III
}
}
