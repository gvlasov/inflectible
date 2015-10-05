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
import org.tendiwa.inflectible.GrammaticalCategory;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.PartOfSpeech;
import org.tendiwa.inflectible.Spelling;

/**
 * Parts of speech in English language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public enum EnglishPartOfSpeech implements PartOfSpeech {

    /**
     * Noun.
     */
    Noun(
        ImmutableSet.of(
            EnglishGrammaticalCategory.Number
        ),
        ImmutableSet.<GrammaticalMeaning>of(),
        (spelling, meaning) -> new NotImplementedLexeme()
    ),

    /**
     * Verb.
     */
    Verb(
        ImmutableSet.of(
            EnglishGrammaticalCategory.Number,
            EnglishGrammaticalCategory.Form,
            EnglishGrammaticalCategory.Person,
            EnglishGrammaticalCategory.Tense
        ),
        ImmutableSet.<GrammaticalMeaning>of(),
        (spelling, meaning) -> new NotImplementedLexeme()
    ),

    /**
     * Adjective.
     */
    Adjective(
        ImmutableSet.<GrammaticalCategory>of(),
        ImmutableSet.<GrammaticalMeaning>of(),
        (spelling, meaning) -> new NotImplementedLexeme()
    );

    /**
     * Grammatical categories that can be used in this part of speech.
     */
    private final transient ImmutableSet<? extends GrammaticalCategory> used;

    /**
     * Rules of inflection for this part of speech.
     */
    private final transient Set<GrammaticalMeaning> all;

    /**
     * How to generate a lexeme from a spelling and persistent grammatical
     * meaning.
     */
    private final transient
        BiFunction<Spelling, GrammaticalMeaning, Lexeme> inflection;

    /**
     * Ctor.
     * @param categories Grammatical categories that can be used in this part of
     *  speech
     * @param meanings All possible combinations of meanings a word in this
     *  part of speech can assume in the process of inflection.
     * @param generator How to generate a lexeme from a spelling and persistent
     *  grammatical meaning.
     */
    EnglishPartOfSpeech(
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
    ) throws Exception {
        return this.inflection.apply(headword, persistent);
    }

    @Override
    public Set<GrammaticalMeaning> meaningVariations() throws Exception {
        return this.all;
    }
}
