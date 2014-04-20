package org.tendiwa.lexeme;

import java.util.*;

/**
 * An object of this class is created whenever the {@link Language#getLocalizedText(String, Localizable...)} method is
 * called. The purpose of this class is to store modifiers that were not available at {@link MarkedUpText} creation when
 * {@link Language#loadCorpus(java.io.InputStream)} was called, but are available with particular denotations.
 */
public class TextCase {
private final Language language;
private final MarkedUpText text;
private final Localizable[] denotations;
Map<LexemeTemplate, List<Modifier>> modifiers = new HashMap<>();

public TextCase(Language language, MarkedUpText text, Localizable... denotations) {
	this.language = language;
	this.text = text;
	this.denotations = denotations;
	for (LexemeTemplate lexemeTemplate : text.getLexemeTemplates()) {
		createCaseModifiers(lexemeTemplate);
	}
	for (LexemeTemplate lexemeTemplate : text.getLexemeTemplates()) {
		String agreeingParameterName = lexemeTemplate.getAgreeingParameterName();
		if (agreeingParameterName != null) {
			establishAgreement(
				text.getTemplateByParamName(agreeingParameterName),
				lexemeTemplate
			);
		}
	}
}

String localize() {
	StringBuilder builder = new StringBuilder();
	int lastIndexInOriginalText = 1;
	for (LexemeTemplate template : text.getLexemeTemplates()) {
		String localizedWord;
		if (template.getParamNumber() >= denotations.length) {
			localizedWord = language.getMissingWord();
		} else {
			localizedWord = language.getLocalizedWord(
				denotations[template.getParamNumber()].getLocalizationId(),
				template.isFirstLetterCapital(),
				obtainAllModifiers(template)
			);
		}
		// Original raw text after previous localized word (or raw text beginning) and before current localized word.
		builder.append(text.rawMarkedUpText.substring(
			lastIndexInOriginalText - 1,
			template.getWordStartIndex()
		));
		builder.append(localizedWord);
		lastIndexInOriginalText = template.getWordEndIndex() + 1;
	}
	if (lastIndexInOriginalText <= text.rawMarkedUpText.length()) {
		builder.append(text.rawMarkedUpText.substring(lastIndexInOriginalText - 1, text.rawMarkedUpText.length()));
	}
	String s = builder.toString();
	return s.substring(s.indexOf('\n') + 1);
}

/**
 * Returns an array of all template modifiers and case modifiers for a {@link LexemeTemplate}.
 *
 * @param template
 * 	A template whose modifiers are to be returned.
 * @return An array of {@code template}'s modifiers.
 */
private List<Modifier> obtainAllModifiers(LexemeTemplate template) {
	List<Modifier> caseModifiers = new LinkedList<>(template.getModifiers());
	caseModifiers.addAll(modifiers.get(template));
	return caseModifiers;
}

/**
 * Takes case and template modifiers from template {@code source} and adds them to case modifiers of template {@code
 * destination}.
 *
 * @param source
 * 	A template to take parameters from.
 * @param destination
 * 	A template to add parameters to.
 */
private void establishAgreement(LexemeTemplate source, LexemeTemplate destination) {
	modifiers.get(destination).addAll(
		source.getModifiers()
	);
	modifiers.get(destination).addAll(
		modifiers.get(source)
	);
}

/**
 * @param lexemeTemplate
 */
private void createCaseModifiers(LexemeTemplate lexemeTemplate) {
	List<Modifier> caseModifiers = language.processTemplate(lexemeTemplate, denotations[lexemeTemplate.getParamNumber()]);
	if (caseModifiers == null) {
		caseModifiers = new LinkedList<>();
	}
	modifiers.put(
		lexemeTemplate,
		caseModifiers
	);
}
}
