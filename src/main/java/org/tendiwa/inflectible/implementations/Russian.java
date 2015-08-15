package org.tendiwa.inflectible.implementations;

import org.tendiwa.inflectible.Grammeme;
import org.tendiwa.inflectible.LanguageWithFallback;

/**
 * Russian language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class Russian extends LanguageWithFallback {

	public Russian() {
		super("Русский", "ru_RU", Russian.Grammemes.class, new English());
	}

	@Override
	public String missingWord() {
		return "[параметр пропущен]";
	}


	public enum Grammemes implements Grammeme {
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
