package org.tendiwa.lexeme.implementations;

import com.google.common.collect.Lists;
import java.util.List;
import org.tendiwa.lexeme.Grammeme;
import org.tendiwa.lexeme.LanguageWithFallback;
import org.tendiwa.lexeme.LexemeTemplate;
import org.tendiwa.lexeme.Localizable;
import org.tendiwa.lexeme.LocalizableNumber;

/**
 * Russian language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class Russian extends LanguageWithFallback {

	public Russian() {
		super("Русский", "ru_RU", new English());
	}

	@Override
	public Grammeme stringToModifier(String modifier) {
		return Modifiers.valueOf(modifier);
	}

	@Override
	public String missingWord() {
		return "[параметр пропущен]";
	}

	@Override
	public List<Grammeme> processTemplate(LexemeTemplate lexemeTemplate, Localizable localizable) {
		if (localizable instanceof LocalizableNumber) {
			// Согласование с числительным, выраженным цифрами (не словом).
			int asInt = Integer.parseInt(localizable.getLocalizationId());
			if (asInt % 10 >= 2 && asInt % 10 <= 4 && asInt % 100 > 20) {
				// 2 медведя, но 12 медведей
				if (
					!lexemeTemplate.getGrammemes().contains(Russian.Modifiers.Р)
						&& !lexemeTemplate.getGrammemes().contains(Russian.Modifiers.В)
						&& !lexemeTemplate.getGrammemes().contains(Russian.Modifiers.Д)
						&& !lexemeTemplate.getGrammemes().contains(Russian.Modifiers.Т)
						&& !lexemeTemplate.getGrammemes().contains(Russian.Modifiers.П)
					) {
					return Lists.<Grammeme>newArrayList(Modifiers.Мн, Modifiers.Р, Modifiers.Числ2До4);
				} else {
					return null;
				}
			} else if (asInt % 10 == 1) {
				return Lists.<Grammeme>newArrayList(Modifiers.Ед);
			} else {
				return Lists.<Grammeme>newArrayList(Modifiers.Мн, Modifiers.Р);
			}
		} else {
			return null;
		}
	}

	public enum Modifiers implements Grammeme {
		/**
		 * Мужской род.
		 * <p>Masculine gender.
		 */
		Муж,
		/**
		 * Женский род.
         * <p>Feminine gender.
		 */
		Жен,
		/**
		 * Именительный падеж.
         * <p>Nominative case.
		 */
		И,
		/**
		 * Родительный падеж.
         * <p>Genitive case.
		 */
		Р,
		/**
		 * Дательный падеж.
         * <p>Dative case.
		 */
		Д,
		/**
		 * Винительный падеж.
         * <p>Accusative case.
		 */
		В,
		/**
		 * Творительный падеж.
         * <p>Instrumental case.
		 */
		Т,
		/**
		 * Предложный падеж.
         * <p>Prepositional case.
		 */
		П,
		/**
		 * Первое склонение.
         * <p>First declension.
		 */
		I,
		/**
		 * Второе склонение.
         * <p>Second declension.
		 */
		II,
		/**
		 * Третье склонение.
         * <p>Third declension>
		 */
		III,
		/**
		 * Множественное число.
         * <p>Plural.
		 */
		Мн,
		/**
		 * Единственное число.
         * <p>Singular.
		 */
		Ед,
		/**
		 * Существительное
         * <p>Noun
		 */
		Сущ,
		/**
		 * Глагол.
         * <p>Verb.
		 */
		Глаг,
		/**
		 * Настоящее время.
         * <p>Present tense.
		 */
		Наст,
		/**
		 * Числительное, заканчивающееся на цифру от 2 до 4, но не 12, 13 и не 14.
         * <p>A number ending with a digit 2, 3 or 4, but not numbers 12, 13
         * and 14.
		 */
		Числ2До4
	}
}
