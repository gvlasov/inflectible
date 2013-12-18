package org.tendiwa.lexeme;

import java.util.List;

class Lexeme {
private final String asString;
private final List<Modifier> modifiers;

Lexeme(String asString, List<Modifier> modifiers) {
	this.asString = asString;
	this.modifiers = modifiers;
}
public List<Modifier> getModifiers() {
	return modifiers;
}

@Override
public String toString() {
	return asString;
}
}
