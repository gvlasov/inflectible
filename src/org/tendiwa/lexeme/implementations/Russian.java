package org.tendiwa.lexeme.implementations;

import org.tendiwa.lexeme.Language;
import org.tendiwa.lexeme.Modifier;

public class Russian extends Language {

public Russian() {
	super("Русский", "ru_RU");
}

@Override
protected Modifier stringToModifier(String modifier) {
	return Modifiers.valueOf(modifier);
}

@Override
public String getMissingWord() {
	return "[параметр пропущен]";
}

public enum Modifiers implements Modifier {
	/**
	 * Мужской род
	 */
	Муж,
	/**
	 * Женский род
	 */
	Жен,
	/**
	 * Именительный падеж
	 */
	И,
	/**
	 * Родительный падеж
	 */
	Р,
	/**
	 * Дательный падеж
	 */
	Д,
	/**
	 * Винительный падеж
	 */
	В,
	/**
	 * Творительный падеж
	 */
	Т,
	/**
	 * Предложный падеж
	 */
	П,
	/**
	 * Первое склонение
	 */
	I,
	/**
	 * Второе склонение
	 */
	II,
	/**
	 * Третье склонение
	 */
	III,
	/**
	 * Множественное число
	 */
	Мн,
	/**
	 * Единственное число
	 */
	Ед
}
}
