/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Georgy Vlasov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tendiwa.inflectible.implementations;

import org.tendiwa.inflectible.Grammeme;

/**
 * Grammemes of Russian grammar.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public enum RussianGrammeme implements Grammeme {
    /**
     * Masculine gender.
     * <p>Мужской род.
     */
    Муж,
    /**
     * Feminine gender.
     * <p>Женский род.
     */
    Жен,
    /**
     * Neuter gender.
     * <p>Средний род
     */
    Средн,
    /**
     * Nominative case.
     * <p>Именительный падеж.
     */
    И,
    /**
     * Genitive case.
     * <p>Родительный падеж.
     */
    Р,
    /**
     * Dative case.
     * <p>Дательный падеж.
     */
    Д,
    /**
     * Accusative case.
     * <p>Винительный падеж.
     */
    В,
    /**
     * Instrumental case.
     * <p>Творительный падеж.
     */
    Т,
    /**
     * Prepositional case.
     * <p>Предложный падеж.
     */
    П,
    /**
     * First person.
     * <p>Первое лицо.
     */
    I,
    /**
     * Second person.
     * <p>Второе лицо.
     */
    II,
    /**
     * Third person.
     * <p>Третье лицо.
     */
    III,
    /**
     * Plural number.
     * <p>Множественное число.
     */
    Мн,
    /**
     * Singular number.
     * <p>Единственное число.
     */
    Ед,
    /**
     * Present tense.
     * <p>Настоящее время.
     */
    Наст,
    /**
     * Past tense.
     * <p>Прошедшее время.
     */
    Прош,

    /**
     * Infinitive.
     * <p>Неопределённая форма глагола.
     */
    Инф,

    /**
     * Participle.
     * <p>Причастие как форма глагола.
     */
    Прич,

    /**
     * Transgressive.
     * <p>Деепричастие как форма глагола.
     * @see <a href=
     * "https://en.wikipedia.org/wiki/Transgressive_(linguistics)">
     * Transgressive (linguistics) on Wikipedia</a>
     */
    Дееприч
}
