package org.tendiwa.lexeme.implementations;

import org.tendiwa.lexeme.Language;
import org.tendiwa.lexeme.Modifier;

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

public enum Modifiers implements Modifier {
	Ger,
	Sing,
	Plur,
	III
}
}
