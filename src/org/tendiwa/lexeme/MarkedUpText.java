package org.tendiwa.lexeme;

import java.util.*;

class MarkedUpText {

/**
 * First line is header, lines after it are text body. The closing curly brace is omitted.
 */
final String rawMarkedUpText;
/**
 * First word from entry header.
 */
private final String textName;
private final Language language;
private final Map<String, Integer> paramsToParamNumbers;
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
	paramsToParamNumbers = new HashMap<>();
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
		List<Modifier> modifiers;
		String agreeingParameterName = null;
		if (variableWordHasModifiers) {
			// If there is a ; in lexeme parameters, then we'll save what is after it to use for agreement later.
			int indexOfSemicolon = variableWord.indexOf(';');
			if (indexOfSemicolon != -1) {
				String[] split = variableWord.split(";");
				if (split.length > 1) {
					agreeingParameterName = split[1].substring(0, split[1].length() - 1);
				}
				variableWord = variableWord.substring(0, indexOfSemicolon) + "]";
			}

			String[] modifierStrings = variableWord
				.substring(variableWord.indexOf(']') + 2, variableWord.length() - 1)
				.split(" ");
			modifiers = new LinkedList<>();
			for (int i = 0; i < modifierStrings.length; i++) {
				if (modifierStrings[i].length() == 0) {
					continue;
				}
				modifiers.add(language.stringToModifier(modifierStrings[i]));
			}
		} else {
			modifiers = new LinkedList<>();
		}
		lexemeTemplates.add(new LexemeTemplate(paramsToParamNumbers.get(paramNameLowercase), modifiers, variableWordStart, variableWordEnd, firstLetterCapital, agreeingParameterName));
	}
}

List<LexemeTemplate> getLexemeTemplates() {
	return lexemeTemplates;
}

String getTextName() {
	return textName;
}

LexemeTemplate getTemplateByParamName(String paramName) {
	return lexemeTemplates.get(paramsToParamNumbers.get(paramName));
}

}
