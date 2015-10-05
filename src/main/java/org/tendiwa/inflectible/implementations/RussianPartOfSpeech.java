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

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.function.BiFunction;
import org.tendiwa.inflectible.AnyPartOfSpeech;
import org.tendiwa.inflectible.GmValidated;
import org.tendiwa.inflectible.GrammaticalCategory;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.PartOfSpeech;
import org.tendiwa.inflectible.Spelling;

/**
 * Part of speech in Russian language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public enum RussianPartOfSpeech implements PartOfSpeech {
    /**
     * Noun.
     * <p>Существительное.
     */
    Сущ(
        ImmutableSet.of(
            RussianGrammaticalCategory.Род,
            RussianGrammaticalCategory.Число,
            RussianGrammaticalCategory.Падеж
        ),
        ImmutableSet.<GrammaticalMeaning>of(
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.И
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.Р
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.Д
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.В
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.Т
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Ед,
                    RussianGrammeme.П
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.И
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Р
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Д
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.В
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Т
                )
            ),
            new GmValidated(
                new AnyPartOfSpeech(),
                ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.П
                )
            )
        ),
        RussianNoun::new
    ),

    /**
     * Verb.
     * <p>Глагол.
     */
    Глаг(
        ImmutableSet.of(
            RussianGrammaticalCategory.Время,
            RussianGrammaticalCategory.Лицо,
            RussianGrammaticalCategory.Число,
            RussianGrammaticalCategory.Форма
        ),
        ImmutableSet.<GrammaticalMeaning>of(),
        (spelling, meaning) -> new NotImplementedLexeme()
    ),

    /**
     * Adjective.
     * <p>Прилагательное.
     */
    Прил(
        ImmutableSet.of(
            RussianGrammaticalCategory.Род,
            RussianGrammaticalCategory.Число,
            RussianGrammaticalCategory.Падеж
        ),
        ImmutableSet.<GrammaticalMeaning>of(),
        (spelling, meaning) -> new NotImplementedLexeme()
    ),

    /**
     * Adverb.
     * <p>Наречие.
     */
    Нареч(
        ImmutableSet.<GrammaticalCategory>of(),
        ImmutableSet.<GrammaticalMeaning>of(),
        (spelling, meaning) -> new NotImplementedLexeme()
    ),

    /**
     * Pronoun.
     * <p>Местоимение.
     */
    Местоим(
        ImmutableSet.of(
            RussianGrammaticalCategory.Лицо,
            RussianGrammaticalCategory.Падеж,
            RussianGrammaticalCategory.Число,
            RussianGrammaticalCategory.Род
        ),
        ImmutableSet.<GrammaticalMeaning>of(),
        (spelling, meaning) -> new NotImplementedLexeme()
    );

    /**
     * Grammatical categories that can be used in this part of speech.
     */
    private final transient ImmutableSet<? extends GrammaticalCategory> used;

    /**
     * How to generate a lexeme from a spelling and persistent grammatical
     * meaning.
     */
    private final transient
        BiFunction<Spelling, GrammaticalMeaning, Lexeme> inflection;

    /**
     * All possible combinations of meanings a word in this part of speech can
     * assume in the process of inflection.
     */
    private final transient Set<GrammaticalMeaning> all;

    /**
     * Ctor.
     * @param categories Grammatical categories that can be used in this part of
     *  speech
     * @param meanings All possible combinations of meanings a word in this
     *  part of speech can assume in the process of inflection.
     * @param generator How to generate a lexeme from a spelling and persistent
     *  grammatical meaning.
     */
    RussianPartOfSpeech(
        final ImmutableSet<? extends GrammaticalCategory> categories,
        final Set<GrammaticalMeaning> meanings,
        final BiFunction<Spelling, GrammaticalMeaning, Lexeme> generator
    ) {
        this.used = categories;
        this.all = meanings;
        this.inflection = generator;
    }

    @Override
    public boolean usesCategory(final GrammaticalCategory category) {
        return this.used.contains(category);
    }

    @Override
    public Lexeme lexeme(
        final Spelling headword,
        final GrammaticalMeaning persistent
    ) {
        return this.inflection.apply(headword, persistent);
    }

    @Override
    public Set<GrammaticalMeaning> meaningVariations() throws Exception {
        return this.all;
    }
}
