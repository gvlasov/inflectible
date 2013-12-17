import java.io.*;
import java.util.*;

public abstract class Language {

private final String localizedName;
private Language fallbackLanguage;
private Map<String, VocabularyEntry> entires = new HashMap<>();
private Map<String, String> texts;

/**
 * @param localizedName
 * 	Name of the language in that language, for example "Русский" for Russian.
 */
protected Language(String localizedName) {
	this.localizedName = localizedName;
}

public String getName() {
	return localizedName;
}

public void setFallbackLanguage(Language language) {
	fallbackLanguage = language;
}

public String getLocalizedText(String textLocalizationId, Localizable... params) {
	texts.get(textLocalizationId);

}

public String getLocalizedWord(String wordLocalizationId, Modifier... modifiers) {
	return entires.get(wordLocalizationId).findWordForm(modifiers).toString();
}

/**
 * Reads {@code vocabulary} file and fills Language with word forms from that vocabulary.
 *
 * @param vocabulary
 * 	Vocabulary file. It is recommended to use .words extension.
 * @throws IOException
 * 	If file could not be opened.
 */
public void loadVocabulary(File vocabulary) {
	FileReader reader = null;
	try {
		reader = new FileReader(vocabulary);
	} catch (FileNotFoundException e) {
		throw new RuntimeException("Could not load vocabulary " + vocabulary + ": file does not exist")
	}
	BufferedReader bufReader = new BufferedReader(reader);
	String currentLine = null;
	VocabularyEntry entry = null;
	String localizationId = null;
	try {
		while ((currentLine = bufReader.readLine()) != null) {
			currentLine = currentLine.trim();
			if (currentLine == "" || currentLine.equals("}")) {
				// Empty line or end of body — just continue.
				continue;
			} else if (currentLine.endsWith("{")) {
				// Header parsing
				entry = new VocabularyEntry();
				String[] split = currentLine.split("[\"\\[\\]]");
				localizationId = split[0].trim();
				for (String modifier : split[1].split(" ")) {
					entry.addPermanentModifier(stringToModifier(modifier));
				}
				entires.put(localizationId, entry);
			} else {
				// Body parsing
				String[] split = currentLine.split("[\\[\\]]");
				String wordForm = split[0];
				String[] modifierStrings = split[1].split(" ");
				List<Modifier> modifiers = new LinkedList<>();
				for (String str : modifierStrings) {
					modifiers.add(stringToModifier(str));
				}
				assert entry.getBaseForm() != null;
				entry.addWordForm(wordForm, modifiers);
			}
		}
	} catch (IOException e) {
		throw new RuntimeException("Error reading from file " + vocabulary + ": file was successfully opened, but reading failed");
	}
}

public void loadCorpus(File corpus) {

	FileReader reader = null;
	try {
		reader = new FileReader(corpus);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		throw new RuntimeException("Could not load corpus " + corpus + ": file does not exist")
	}
	BufferedReader bufReader = new BufferedReader(reader);
	String currentLine = null;
	VocabularyEntry entry = null;
	String localizationId = null;
	try {
		while ((currentLine = bufReader.readLine()) != null) {
			currentLine = currentLine.trim();
		}
	} catch (IOException e) {
		throw new RuntimeException("Error reading from file " + corpus + ": file was successfully opened, but reading failed");
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

private class VocabularyEntry {
	private List<Modifier> permanentModifiers = new LinkedList<>();
	private List<WordForm> localizationToModifiers = new LinkedList<>();
	private WordForm baseForm;

	public void addPermanentModifier(Modifier modifier) {
		permanentModifiers.add(modifier);
	}

	public void addWordForm(String wordFormString, List<Modifier> modifiers) {
		WordForm wordForm = new WordForm(wordFormString, modifiers);
		if (baseForm == null) {
			baseForm = wordForm;
		}
		localizationToModifiers.add(wordForm);
	}

	public WordForm getBaseForm() {
		return baseForm;
	}

	public WordForm findWordForm(Modifier... modifiers) {
		int maxMatchingModifiers = 0;
		WordForm bestSuitedWordForm = null;
		for (WordForm form : localizationToModifiers) {
			int matchingModifiers = 0;
			for (Modifier modifier : modifiers) {
				if (form.modifiers.contains(modifier)) {
					matchingModifiers++;
					if (matchingModifiers > maxMatchingModifiers) {
						maxMatchingModifiers = matchingModifiers;
						bestSuitedWordForm = form;
					}
				}
			}
		}
		assert bestSuitedWordForm != null;
		return bestSuitedWordForm;
	}

}

private class WordForm {
	private final String asString;
	private final List<Modifier> modifiers;

	private WordForm(String asString, List<Modifier> modifiers) {
		this.asString = asString;
		this.modifiers = modifiers;
	}

	@Override
	public String toString() {
		return asString;
	}
}

private class MarkedUpText {
	private final String originalText;

	/**
	 * Text from file containing a header and body in the following form:
	 * <pre>
	 * text_localization_id (param1, param2, param3) {
	 *     Text about [param1] and [param2][Plur] with some mentioning of [param3][Gerund].
	 * }
	 * </pre>
	 *
	 * @param textEntry
	 */
	private MarkedUpText(String textEntry) {
		String textName = textEntry.split("[\\(\\{]")[0].trim();
		List<String> paramNames = Arrays.asList(
			textEntry.split("[\\(\\)]")[1].split(", ")
		);
		String actualText = textEntry.split("[{}]")[1].trim();
		originalText = textEntry;
		int lastIndex = 0;
		int actualTextLength = actualText.length();
		while (lastIndex < actualTextLength) {
			// Search for '[' until the end of text;
			// if a '[' is found, then extract that block describing a word form to a list of variableWords.
			int variableWordStart = textEntry.indexOf('[', lastIndex);
			if (variableWordStart == -1) {
				break;
			}
			int variableWordEnd = textEntry.indexOf(']');
			if (variableWordEnd == -1) {
				throw new RuntimeException("SyntaxError in text " + textName
					+ ": missing matching ']'");
			}
			boolean variableWordHasParams = false;
			if (actualText.length() > variableWordEnd + 1 && actualText.charAt(variableWordEnd + 1) == '[') {
				// If there are parameters given in the second [] block after a word,
				// for example [attacker][Plur]
				variableWordHasParams = true;
				variableWordEnd = textEntry.indexOf(']', variableWordEnd);

			}
			String variableWord = textEntry.substring(variableWordStart, variableWordEnd);
			String paramName = variableWord.substring(
				variableWordStart,
				variableWord.indexOf(']')
			);
			if (!paramNames.contains(paramName)) {
				throw new RuntimeException("Reference error in text " + textName
					+ ": parameter " + paramName + " is used in text but it is not declared in header"
				);
			}

		}
	}

	private String localize(Localizable... params) {

	}
}
}
