package org.tendiwa.lexeme;

import java.util.List;

/**
 * Represents a part of text entry in .texts file in a form of [role][mo di fi ers] where this template is going to be
 * substituted by a particular lexeme.
 */
public class LexemeTemplate {
private final boolean isFirstLetterCapital;
private final String agreeingParameterName;
private List<Modifier> modifiers;
private int paramNumber;
private int wordStartIndex;
private int wordEndIndex;

/**
 * @param paramNumber
 * 	In the first bracket pair of a LexemeTemplate there is a localizationId; {@paramNumber} index of that localizationId
 * 	in MarkedUpText's header.
 * @param modifiers
 * 	Modifiers from the second bracket pair, or an empty array if there wasn't one.
 * @param wordStartIndex
 * 	Index in {@link org.tendiwa.lexeme.MarkedUpText#rawMarkedUpText} String on which the LexemeTemplate template
 * 	starts.
 * @param wordEndIndex
 * 	Index in {@link org.tendiwa.lexeme.MarkedUpText#rawMarkedUpText} String on which the LexemeTemplate template
 * 	starts.
 * @param firstLetterCapital
 * @param agreeingParameterName
 */
public LexemeTemplate(int paramNumber, List<Modifier> modifiers, int wordStartIndex, int wordEndIndex, boolean firstLetterCapital, String agreeingParameterName) {
	this.paramNumber = paramNumber;
	this.modifiers = modifiers;
	this.wordStartIndex = wordStartIndex;
	this.wordEndIndex = wordEndIndex;
	this.isFirstLetterCapital = firstLetterCapital;
	this.agreeingParameterName = agreeingParameterName;
}

String getAgreeingParameterName() {
	return agreeingParameterName;
}

public int getParamNumber() {
	return paramNumber;
}

public boolean isFirstLetterCapital() {
	return isFirstLetterCapital;
}

public List<Modifier> getModifiers() {
	return modifiers;
}

public int getWordStartIndex() {
	return wordStartIndex;
}

public int getWordEndIndex() {
	return wordEndIndex;
}
}
