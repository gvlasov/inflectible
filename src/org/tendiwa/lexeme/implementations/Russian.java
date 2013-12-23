package org.tendiwa.lexeme.implementations;

import com.google.common.collect.Lists;
import org.tendiwa.lexeme.*;

import java.util.List;

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

@Override
public List<Modifier> processTemplate(LexemeTemplate lexemeTemplate, Localizable localizable) {
	if (localizable instanceof LocalizableNumber) {
		// Согласование с числительным выраженным цифрами (не словом).
		int asInt = Integer.parseInt(localizable.getLocalizationId());
		if (asInt % 10 >= 2 && asInt % 10 <= 4 && asInt % 100 > 20) {
			// 2 медведя, но 12 медведей
			if (
				!lexemeTemplate.getModifiers().contains(Russian.Modifiers.Р)
					&& !lexemeTemplate.getModifiers().contains(Russian.Modifiers.В)
					&& !lexemeTemplate.getModifiers().contains(Russian.Modifiers.Д)
					&& !lexemeTemplate.getModifiers().contains(Russian.Modifiers.Т)
					&& !lexemeTemplate.getModifiers().contains(Russian.Modifiers.П)
				) {
				return Lists.<Modifier>newArrayList(Modifiers.Мн, Modifiers.Р, Modifiers.Числ2До4);
			} else {
				return null;
			}
		} else if (asInt % 10 == 1) {
			return Lists.<Modifier>newArrayList(Modifiers.Ед);
		} else {
			return Lists.<Modifier>newArrayList(Modifiers.Мн, Modifiers.Р);
		}
	} else {
		return null;
	}
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
	Ед,
	/**
	 * Существительное
	 */
	Сущ,
	/**
	 * Глагол
	 */
	Глаг,
	/**
	 * Настоящее время
	 */
	Наст,
	/**
	 * Числительное, заканчивающееся на цифру от 2 до 4, но не 12, 13 и не 14.
	 */
	Числ2До4

}
}
