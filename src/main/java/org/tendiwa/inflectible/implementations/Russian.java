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

import org.tendiwa.inflectible.AbstractLanguage;
import org.tendiwa.inflectible.Grammeme;

/**
 * Russian language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class Russian extends AbstractLanguage {
    /**
     * Ctor.
     */
    public Russian() {
        super(Russian.Grammemes.class);
    }

    /**
     * Grammemes of Russian grammar.
     */
    public enum Grammemes implements Grammeme {
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
         * First declension.
         * <p>Первое склонение.
         */
        I,
        /**
         * Second declension.
         * <p>Второе склонение.
         */
        II,
        /**
         * Third declension.
         * <p>Третье склонение.
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
         * A number ending with a digit 2, 3 or 4, but not numbers 12, 13
         * and 14.
         * <p>Числительное, заканчивающееся на цифру от 2 до 4, но не 12, 13 и
         * не 14.
         */
        Числ2До4
    }
}
