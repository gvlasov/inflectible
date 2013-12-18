package org.tendiwa.lexeme;

import java.util.*;

class MarkedUpText {

/**
 * First line is header, lines after it are text body. The closing curly brace is omitted.
 */
private final String rawMarkedUpText;
/**
 * First word from entry header.
 */
private final String textName;
private final Language language;
/**
 * Words sorted by start index.
 */
private List<LexemeTemplate> lexemeTemplates = new LinkedList<>();

/**
 * Text from file containing a header and body in the following form:
 * <pre>
 * text_localization_id (param1, param2, param3) {
 *     Text about [param1] and [param2][Plur] with some
 *     mentioning of [param3][Gerund].
 * }
 * </pre>
 *
 * @param textEntry
 */
MarkedUpText(Language language, String textEntry, int startTextLineInFile) {
	this.language = language;
	this.rawMarkedUpText = textEntry = textEntry.replaceAll("\n}\n", "");
	textName = textEntry.split("[\\(\\{]")[0].trim();
	assert textEntry.matches("^.+\\(.*\\)\\s*\\{\\s*\\n[\\s\\S]*") : textEntry;
	// Map parameter names to parameter order number
	List<String> paramNames = Arrays.asList(
		textEntry.split("[\\(\\)]")[1].split(", ")
	);
	Map<String, Integer> paramsToParamNumbers = new HashMap<>();
	int paramOrder = 0;
	for (String param : paramNames) {
		paramsToParamNumbers.put(param, paramOrder++);
	}
	// Raw text without header and the last closing curly brace.
	int lastIndex = textEntry.indexOf('\n') + 1;
	int textEntryLength = textEntry.length();

	while (lastIndex < textEntryLength) {
		// Search for '[' until the end of text;
		// if a '[' is found, then extract that block describing a word form to a list of lexemeTemplates.
		int variableWordStart = textEntry.indexOf('[', lastIndex);
		if (variableWordStart == -1) {
			break;
		}
		int variableWordEnd = textEntry.indexOf(']', variableWordStart) + 1;
		if (variableWordEnd == 0) {
			// -1 + 1
			throw new RuntimeException("SyntaxError in text " + textName
				+ ": missing matching ']'");
		}
		boolean variableWordHasModifiers = false;
		if (textEntry.length() > variableWordEnd + 1 && textEntry.charAt(variableWordEnd) == '[') {
			// If there are parameters given in the second [] block after a word,
			// for example [attacker][Plur]
			variableWordHasModifiers = true;
			variableWordEnd = textEntry.indexOf(']', variableWordEnd) + 1;
		}
		lastIndex = variableWordEnd + 1;
		String variableWord = textEntry.substring(variableWordStart, variableWordEnd);
		String paramName = variableWord.substring(
			1,
			variableWord.indexOf(']')
		);
		boolean firstLetterCapital = Character.isUpperCase(paramName.charAt(0));
		String paramNameLowercase = paramName.toLowerCase();
		if (!paramNames.contains(paramNameLowercase)) {
			throw new ReferenceException("Error in text \"" + textName
				+ "\": parameter " + paramNameLowercase + " is used in text but it is not declared in header"
			);
		}
		Modifier[] modifiers;
		if (variableWordHasModifiers) {
			String[] modifierStrings = variableWord
				.substring(variableWord.indexOf(']') + 2, variableWord.length() - 1)
				.split(" ");
			modifiers = new Modifier[modifierStrings.length];
			for (int i = 0; i < modifierStrings.length; i++) {
				modifiers[i] = language.stringToModifier(modifierStrings[i]);
			}
		} else {
			modifiers = new Modifier[0];
		}
		lexemeTemplates.add(new LexemeTemplate(paramsToParamNumbers.get(paramNameLowercase), modifiers, variableWordStart, variableWordEnd, firstLetterCapital));
	}
}

String getTextName() {
	return textName;
}

/**
 * Builds a final text with particular lexemes from this raw MarkedUpText.
 *
 * @param params
 * 	Particular concepts to be put in an appropriate form.
 * @return A final localized text readable by end user.
 */
String localize(Localizable... params) {
	StringBuilder builder = new StringBuilder();
	int lastIndexInOriginalText = 1;
	for (LexemeTemplate template : lexemeTemplates) {
		String localizedWord;
		if (template.paramNumber >= params.length) {
			localizedWord = language.getMissingWord();
		} else {
			localizedWord = language.getLocalizedWord(
				params[template.paramNumber].getLocalizationId(),
				template.isFirstLetterCapital,
				template.modifiers
			);
		}
		// Original raw text after previous localized word (or raw text beginning) and before current localized word.
		builder.append(rawMarkedUpText.substring(
			lastIndexInOriginalText - 1,
			template.wordStartIndex
		));
		builder.append(localizedWord);
		lastIndexInOriginalText = template.wordEndIndex + 1;
	}
	if (lastIndexInOriginalText < rawMarkedUpText.length()) {
		builder.append(rawMarkedUpText.substring(lastIndexInOriginalText-1, rawMarkedUpText.length()));
	}
	String s = builder.toString();
	return s.substring(s.indexOf('\n') + 1);
}

/**
 * Represents a part of text entry in .texts file in a form of [role][mo di fi ers] where this template is going to be
 * substituted by a particular lexeme.
 */
private class LexemeTemplate {
	private final boolean isFirstLetterCapital;
	private Modifier[] modifiers;
	private int paramNumber;
	private int wordStartIndex;
	private int wordEndIndex;

	/**
	 * @param paramNumber
	 * 	In the first bracket pair of a LexemeTemplate there is a localizationId; {@paramNumber} index of that
	 * 	localizationId in MarkedUpText's header.
	 * @param modifiers
	 * 	Modifiers from the second bracket pair, or an empty array if there wasn't one.
	 * @param wordStartIndex
	 * 	Index in {@link MarkedUpText#rawMarkedUpText} String on which the LexemeTemplate template starts.
	 * @param wordEndIndex
	 * 	Index in {@link MarkedUpText#rawMarkedUpText} String on which the LexemeTemplate template starts.
	 * @param firstLetterCapital
	 * 	Should the first letter of lexeme be capitalized.
	 */
	public LexemeTemplate(int paramNumber, Modifier[] modifiers, int wordStartIndex, int wordEndIndex, boolean firstLetterCapital) {
		this.paramNumber = paramNumber;
		this.modifiers = modifiers;
		this.wordStartIndex = wordStartIndex;
		this.wordEndIndex = wordEndIndex;
		this.isFirstLetterCapital = firstLetterCapital;
	}

}
}
