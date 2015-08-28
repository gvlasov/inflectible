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

import com.google.common.collect.ImmutableList;
import org.tendiwa.inflectible.GrammaticalCategory;
import org.tendiwa.inflectible.Grammeme;

/**
 * Grammatical categories of Russian language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public enum RussianGrammaticalCategory implements GrammaticalCategory {
    /**
     * Grammatical gender.
     * <p>Род.
     */
    Род(
        ImmutableList.of(
            RussianGrammeme.Муж,
            RussianGrammeme.Жен,
            RussianGrammeme.Средн
        )
    ),

    /**
     * Grammatical number.
     * <p>Число.
     */
    Число(ImmutableList.of(RussianGrammeme.Ед, RussianGrammeme.Мн)),

    /**
     * Grammatical case.
     * <p>Падеж.
     */
    Падеж(
        ImmutableList.of(
            RussianGrammeme.И,
            RussianGrammeme.Р,
            RussianGrammeme.Д,
            RussianGrammeme.В,
            RussianGrammeme.Т,
            RussianGrammeme.П
        )
    ),

    /**
     * Grammatical tense.
     * <p>Время.
     */
    Время(ImmutableList.of(RussianGrammeme.Наст, RussianGrammeme.Прош)),

    /**
     * Grammatical person.
     * <p>Лицо.
     */
    Лицо(
        ImmutableList.of(
            RussianGrammeme.I,
            RussianGrammeme.II,
            RussianGrammeme.III
        )
    ),

    /**
     * Verb form.
     * <p>Форма глагола
     */
    Форма(
        ImmutableList.of(
            RussianGrammeme.Инф,
            RussianGrammeme.Прич,
            RussianGrammeme.Дееприч
        )
    );

    /**
     * Grammemes of this grammatical category.
     */
    private final transient ImmutableList<? extends Grammeme> grammemes;

    /**
     * Ctor.
     * @param grams Grammemes of this grammatical category
     */
    RussianGrammaticalCategory(final ImmutableList<? extends Grammeme> grams) {
        this.grammemes = grams;
    }

    @Override
    public Grammeme defaultGrammeme() {
        return this.grammemes.get(0);
    }

    @Override
    public boolean containsGrammeme(final Grammeme grammeme) {
        return this.grammemes.contains(grammeme);
    }

}
