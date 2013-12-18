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
private Object currentDictionarySource;
private URL currentCorpus;

/**
 * @param localizedName
 * 	Name of the language in that language, for example "Русский" for Russian.
 */
protected Language(String localizedName, String localeName) {
	this.localizedName = localizedName;
	this.localeName = localeName;
}
public Collection<Word> getLoadedWords() {
	return entires.values();
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
 * Translates description of a lexeme to an actual String with that lexeme.
 *
 * @param wordLocalizationId
 * 	A word wrapped in quotation marks in headers of entries in .words files (dictionaries).
 * @param capitalizeFirstLetter
 * 	Should the first letter of a word be capitalized.
 * @param modifiers
 * 	Modifiers that determine the particular lexeme to return.
 * @return Particular lexeme to be inserted in a text intended for an end user.
 */
public String getLocalizedWord(String wordLocalizationId, boolean capitalizeFirstLetter, Modifier... modifiers) {
	if (!capitalizeFirstLetter) {
		if (!entires.containsKey(wordLocalizationId)) {
			throw new RuntimeException("No word with localizationId=" + wordLocalizationId + " found in dictionaries");
		}
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
 * Reads {@code dictionary} file and fills Language with word forms from that dictionary.
 *
 * @param dictionaryStream
 * 	Input stream for dictionary contents.
 */
public void loadDictionary(InputStream dictionaryStream) {
	if (dictionaryStream == null) {
		throw new NullPointerException("Argument can't be null");
	}
	BufferedReader bufReader = new BufferedReader(new InputStreamReader(dictionaryStream));
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
							throw new LanguageException("Error in language " + localeName + " in dictionary " + currentDictionarySource, e);
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

public void loadDictionary(URL url) {
	try {
		currentDictionarySource = url;
		loadDictionary(url.openStream());
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
					markedUpText = new MarkedUpText(this, builder.toString(), lineNumber);
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
public void loadCorpus(URL corpus) {
	try {
		currentCorpus = corpus;
		loadCorpus(corpus.openStream());
	} catch (IOException e) {
		throw new RuntimeException(e);
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

}
