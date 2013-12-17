import java.util.Collection;
import java.util.HashSet;

public class LanguageRegistry {
private final Collection<Language> languages = new HashSet<Language>();
private Language fallbackLanguage;
private Language targetLanguage;

public void registerLanguage(Language language) {
	if (fallbackLanguage == null) {
		fallbackLanguage = language;
	}
	languages.add(language);
}

public void setTargetLanguage(Language language) {
	targetLanguage = language;
}

public String localizeText(String textId, Localizable... params) {

}
public String localizeWord(String wordId, Modifier flags) {

}

}
