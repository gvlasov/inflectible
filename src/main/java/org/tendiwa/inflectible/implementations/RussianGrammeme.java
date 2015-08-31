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

import org.tendiwa.inflectible.GrammaticalCategory;
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
    Муж(RussianGrammaticalCategory.Род),
    /**
     * Feminine gender.
     * <p>Женский род.
     */
    Жен(RussianGrammaticalCategory.Род),
    /**
     * Neuter gender.
     * <p>Средний род
     */
    Средн(RussianGrammaticalCategory.Род),
    /**
     * Nominative case.
     * <p>Именительный падеж.
     */
    И(RussianGrammaticalCategory.Падеж),
    /**
     * Genitive case.
     * <p>Родительный падеж.
     */
    Р(RussianGrammaticalCategory.Падеж),
    /**
     * Dative case.
     * <p>Дательный падеж.
     */
    Д(RussianGrammaticalCategory.Падеж),
    /**
     * Accusative case.
     * <p>Винительный падеж.
     */
    В(RussianGrammaticalCategory.Падеж),
    /**
     * Instrumental case.
     * <p>Творительный падеж.
     */
    Т(RussianGrammaticalCategory.Падеж),
    /**
     * Prepositional case.
     * <p>Предложный падеж.
     */
    П(RussianGrammaticalCategory.Падеж),
    /**
     * First person.
     * <p>Первое лицо.
     */
    I(RussianGrammaticalCategory.Лицо),
    /**
     * Second person.
     * <p>Второе лицо.
     */
    II(RussianGrammaticalCategory.Лицо),
    /**
     * Third person.
     * <p>Третье лицо.
     */
    III(RussianGrammaticalCategory.Лицо),
    /**
     * Plural number.
     * <p>Множественное число.
     */
    Ед(RussianGrammaticalCategory.Число),
    /**
     * Present tense.
     * <p>Настоящее время.
     */
    Мн(RussianGrammaticalCategory.Число),
    /**
     * Singular number.
     * <p>Единственное число.
     */
    Наст(RussianGrammaticalCategory.Время),
    /**
     * Past tense.
     * <p>Прошедшее время.
     */
    Прош(RussianGrammaticalCategory.Время),
    /**
     * Infinitive.
     * <p>Неопределённая форма глагола.
     */
    Инф(RussianGrammaticalCategory.Форма),
    /**
     * Finite form.
     * <p>Определённая форма глагола.
     */
    Опред(RussianGrammaticalCategory.Форма),
    /**
     * Participle.
     * <p>Причастие как форма глагола.
     */
    Прич(RussianGrammaticalCategory.Форма),
    /**
     * Transgressive.
     * <p>Деепричастие как форма глагола.
     * @see <a href=
     *  "https://en.wikipedia.org/wiki/Transgressive_(linguistics)">
     *  Transgressive (linguistics) on Wikipedia</a>
     */
    Дееприч(RussianGrammaticalCategory.Форма);

    /**
     * Grammatical category of this grammeme.
     */
    private final transient GrammaticalCategory category;

    /**
     * Ctor.
     * @param cat Grammatical category of this grammeme
     */
    RussianGrammeme(final GrammaticalCategory cat) {
        this.category = cat;
    }

    @Override
    public GrammaticalCategory category() {
        return this.category;
    }
}
