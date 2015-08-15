package org.tendiwa.inflectible;

import java.util.List;

/**
 * Represents a part of text entry in .texts file in a form of [role][mo di fi ers] where this body is going to be
 * substituted by a particular lexeme.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class LexemeTemplate {
private final boolean isFirstLetterCapital;
private final String agreeingParameterName;
private List<Grammeme> grammemes;
private int paramNumber;
private int wordStartIndex;
private int wordEndIndex;

/**
 * @param paramNumber In the first bracket pair of a LexemeTemplate there is
 * a localizationId; {@paramNumber} index of that localizationId in MarkedUpText's header.
 * @param grammemes Grammemes from the second bracket pair, or an empty array
 * if there wasn't one.
 * @param wordStartIndex Index in {@link ParsedTextTemplate#rawMarkedUpText}
 * String on which the LexemeTemplate body starts.
 * @param wordEndIndex Index in {@link ParsedTextTemplate#rawMarkedUpText} String
 * on which the LexemeTemplate body starts.
 * @param firstLetterCapital
 * @param agreeingParameterName
 */
public LexemeTemplate(int paramNumber, List<Grammeme> grammemes, int wordStartIndex, int wordEndIndex, boolean firstLetterCapital, String agreeingParameterName) {
	this.paramNumber = paramNumber;
	this.grammemes = grammemes;
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

public List<Grammeme> getGrammemes() {
	return grammemes;
}

public int getWordStartIndex() {
	return wordStartIndex;
}

public int getWordEndIndex() {
	return wordEndIndex;
}
}
