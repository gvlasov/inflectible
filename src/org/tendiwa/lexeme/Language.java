package org.tendiwa.lexeme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public abstract class Language {

private final String localizedName;
private final String localeName;
private Language fallbackLanguage;
private Map<String, Word> entires = new HashMap<>();
private Map<String, MarkedUpText> texts = new HashMap<>();
private Object currentVocabularySource;

/**
 * @param localizedName
 * 	Name of the language in that language, for example "Русский" for Russian.
 */
protected Language(String localizedName, String localeName) {
	this.localizedName = localizedName;
	this.localeName = localeName;
}

public String getName() {
	return localizedName;
}

public void setFallbackLanguage(Language language) {
	fallbackLanguage = language;
}

public String getLocalizedText(String textLocalizationId, Localizable... params) {
	return texts.get(textLocalizationId).localize(params);
}

/**
 * Translates description of a lexemet to an actual String with that lexeme.
 *
 * @param wordLocalizationId
 * 	A word wrapped in quotation marks in headers of entires in .words files (vocabularies).
 * @param capitalizeFirstLetter
 * 	Should the first letter of a word be capitalized.
 * @param modifiers
 * 	Modifiers that determine the particular lexeme to return.
 * @return Particular lexeme to be inserted in a text intended for an end user.
 */
public String getLocalizedWord(String wordLocalizationId, boolean capitalizeFirstLetter, Modifier... modifiers) {
	if (!capitalizeFirstLetter) {
		return entires.get(wordLocalizationId).findWordForm(modifiers).toString();
	} else {
		String lexeme = entires.get(wordLocalizationId).findWordForm(modifiers).toString();
		return Character.toUpperCase(lexeme.charAt(0)) + lexeme.substring(1);
	}
}

public String getLocalizedWord(String wordLocalizationId, Modifier... modifiers) {
	return getLocalizedWord(wordLocalizationId, false, modifiers);
}

/**
 * Reads {@code vocabulary} file and fills Language with word forms from that vocabulary.
 *
 * @param vocabularyStream
 * 	Input stream for vocabulary contents.
 */
public void loadVocabulary(InputStream vocabularyStream) {
	if (vocabularyStream == null) {
		throw new NullPointerException("Argument can't be null");
	}
	BufferedReader bufReader = new BufferedReader(new InputStreamReader(vocabularyStream));
	String currentLine = null;
	Word entry = null;
	String localizationId = null;
	int lineNumber = 1;
	try {
		while ((currentLine = bufReader.readLine()) != null) {
			currentLine = currentLine.trim();
			if (currentLine == "" || currentLine.equals("}")) {
				// Empty line or end of body — just continue.
				continue;
			} else if (currentLine.endsWith("{")) {
				// Header parsing
				assert entry == null || entry.getBaseForm() != null;
				entry = new Word();
				String[] split = currentLine.split("[\"\\[\\]]");
				localizationId = split[1].trim();
				if (split.length == 5) {
					// If there are parameters in after the base form the word header
					for (String modifier : split[3].split(" ")) {
						entry.addPermanentModifier(stringToModifier(modifier));
					}
				} else if (split.length != 3) {
					// split.length == 3 when there are not parameters in word header.
					// Any other length signals of a syntax error.
					throw new SyntaxError("At line " + lineNumber + ": " + currentLine);
				}
				entires.put(localizationId, entry);
			} else {
				// Body parsing
				String[] split = currentLine.split("[\\[\\]]");
				String wordForm = split[0].trim();
				List<Modifier> modifiers = new LinkedList<>();
				if (split.length >= 2) {
					int firstIndex = currentLine.indexOf('[');
					if (firstIndex == -1 || currentLine.indexOf(']') < firstIndex) {
						throw new SyntaxError("At line " + lineNumber + ": opening [ must precede closing ]");
					}
					// If there are parameters after a lexeme
					String[] modifierStrings = split[1].split(" ");
					for (String str : modifierStrings) {
						try {
							modifiers.add(stringToModifier(str));
						} catch (IllegalArgumentException e) {
							throw new LanguageException("Error in language " + localeName + " in vocabuary "+currentVocabularySource, e);
						}
					}
				}
				entry.addWordForm(wordForm, modifiers);
			}
			lineNumber++;
		}
	} catch (IOException e) {
		throw new RuntimeException("Error reading from stream");
	}
}

public void loadVocabulary(URL url) {
	try {
		currentVocabularySource = url;
		loadVocabulary(url.openStream());
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
}

public void loadCorpus(InputStream corpusStream) {
	BufferedReader bufReader = new BufferedReader(new InputStreamReader(corpusStream));
	String currentLine = null;
	Word entry = null;
	String localizationId = null;
	StringBuilder builder = new StringBuilder();
	try {
		int lineNumber = 1;
		while ((currentLine = bufReader.readLine()) != null) {
			currentLine = currentLine.trim() + "\n";
			if (currentLine.startsWith("//")) {
				// Ignore comments
				continue;
			}
			builder.append(currentLine);
			if (currentLine.charAt(0) == '}') {
				// When the end of a raw text is reached, save that raw text in a localizable form.
				MarkedUpText markedUpText;
				try {
					markedUpText = new MarkedUpText(builder.toString(), lineNumber);
				} catch (ReferenceException e) {
					throw new RuntimeException("Error in language " + localeName, e);
				}
				texts.put(markedUpText.getTextName(), markedUpText);
				builder = new StringBuilder();
			}
			lineNumber++;
		}
	} catch (IOException e) {
		throw new RuntimeException("Error reading from stream");
	}
}

/**
 * Implementation translate a String instance to a Modifier instance. This method in introduced to get the base
 * implementation of Language acquainted with concrete implementation's Modifiers enum. So, in most cases, the body of
 * this method will simply be:
 * <pre>
 *     Modifiers.valueOf(modifier);
 * </pre>
 * Where {@code Modifiers} is an inner enum in your concrete implementation.
 *
 * @param modifier
 * 	Name of enum instance. It is perfectly fine to name instances in your localized language (like {@code
 * 	Modifiers.Муж}), and thus it is fine to pass a localized string as this parameter.
 * @return A modifier enum instance whose {@code enumInstance.valueOf().equals(modifier)}
 */
protected abstract Modifier stringToModifier(String modifier);

public abstract String getMissingWord();

/**
 * An entry from a .words file. Contains possible form that this word can take (lexemes) and modifiers for those forms.
 */
private class Word {
	private List<Modifier> permanentModifiers = new LinkedList<>();
	private List<Lexeme> localizationToModifiers = new LinkedList<>();
	private Lexeme baseForm;

	public void addPermanentModifier(Modifier modifier) {
		permanentModifiers.add(modifier);
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
				if (form.modifiers.contains(modifier)) {
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

private class Lexeme {
	private final String asString;
	private final List<Modifier> modifiers;

	private Lexeme(String asString, List<Modifier> modifiers) {
		this.asString = asString;
		this.modifiers = modifiers;
	}

	@Override
	public String toString() {
		return asString;
	}
}

private class MarkedUpText {

	/**
	 * First line is header, lines after it are text body. The closing curly brace is omitted.
	 */
	private final String rawMarkedUpText;
	/**
	 * First word from entry header.
	 */
	private final String textName;
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
	private MarkedUpText(String textEntry, int startTextLineInFile) {
		this.rawMarkedUpText = textEntry = textEntry.replaceAll("\n}\n", "");
		textName = textEntry.split("[\\(\\{]")[0].trim();
		List<String> paramNames = Arrays.asList(
			textEntry.split("[\\(\\)]")[1].split(", ")
		);
		// Raw text without header and the last closing curly brace.
		String actualText = textEntry.split("[{}]")[1].trim();
		int lastIndex = textEntry.indexOf('\n') + 1;
		int textEntryLength = textEntry.length();
		int variableWordIndex = 0;
		int lineNumberInFile = startTextLineInFile - 1;
		while (lastIndex < textEntryLength) {
			lineNumberInFile++;
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
			if (!paramNames.contains(paramName.toLowerCase())) {
				throw new ReferenceException("Error in text \"" + textName
					+ "\" at line " + lineNumberInFile + ": parameter " + paramName.toLowerCase() + " is used in text but it is not declared in header"
				);
			}
			Modifier[] modifiers;
			if (variableWordHasModifiers) {
				String[] modifierStrings = variableWord
					.substring(variableWord.indexOf(']') + 2, variableWord.length() - 1)
					.split(" ");
				modifiers = new Modifier[modifierStrings.length];
				for (int i = 0; i < modifierStrings.length; i++) {
					modifiers[i] = stringToModifier(modifierStrings[i]);
				}
			} else {
				modifiers = new Modifier[0];
			}
			lexemeTemplates.add(new LexemeTemplate(variableWordIndex++, modifiers, variableWordStart, variableWordEnd, firstLetterCapital));
		}
	}

	private String getTextName() {
		return textName;
	}

	/**
	 * Builds a final text with particular lexemes from this raw MarkedUpText.
	 *
	 * @param params
	 * 	Particular concepts to be put in an appropriate form.
	 * @return A final localized text readable by end user.
	 */
	private String localize(Localizable... params) {
		StringBuilder builder = new StringBuilder();
		int shiftFromReplacedWords = 0;
		int lastIndexInOriginalText = 1;
		for (LexemeTemplate template : lexemeTemplates) {
			String localizedWord;
			if (template.paramNumber >= params.length) {
				localizedWord = Language.this.getMissingWord();
			} else {
				localizedWord = getLocalizedWord(
					params[template.paramNumber].getLocalizationId(),
					template.isFirstLetterCapital,
					template.modifiers
				);
			}
			// Original raw text after previous localized word (or raw text beginning) and before current localized word.
			int beginIndex = lastIndexInOriginalText - 1;
			int endIndex = template.wordStartIndex;
			String substring = rawMarkedUpText.substring(
				beginIndex,
				endIndex
			);
			builder.append(
				substring
			);
			builder.append(localizedWord);
			lastIndexInOriginalText = template.wordEndIndex + 1;
			shiftFromReplacedWords -= localizedWord.length() - (template.wordEndIndex - template.wordStartIndex);
		}
		if (lastIndexInOriginalText < rawMarkedUpText.length()) {
			builder.append(rawMarkedUpText.substring(lastIndexInOriginalText, rawMarkedUpText.length()));
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
}
