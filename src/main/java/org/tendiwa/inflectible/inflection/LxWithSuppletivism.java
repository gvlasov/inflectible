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
package org.tendiwa.inflectible.inflection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import org.tendiwa.inflectible.BasicLexeme;
import org.tendiwa.inflectible.GmCombined;
import org.tendiwa.inflectible.GmWithSimilarity;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Grammeme;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.PartOfSpeech;
import org.tendiwa.inflectible.Spelling;
import org.tenidwa.collections.utils.Collectors;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * Lexeme with its forms derived automatically from a smaller set of forms
 * using inflection rules of a language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class LxWithSuppletivism implements Lexeme {
    /**
     * Part of speech.
     */
    private final transient PartOfSpeech part;

    /**
     * Persistent grammatical meaning.
     */
    private final transient GrammaticalMeaning persistent;

    /**
     * Initially known word forms.
     */
    private final transient
        ImmutableMap<GrammaticalMeaning, Spelling> headwords;

    /**
     * Ctor.
     * @param pos Part of speech of this lexeme
     * @param pers Persistent grammatical meaning of the lexeme
     * @param forms Known word forms of this lexeme. New forms will be derived
     *  from these
     */
    public LxWithSuppletivism(
        final PartOfSpeech pos,
        final GrammaticalMeaning pers,
        final ImmutableMap<GrammaticalMeaning, Spelling> forms
    ) {
        this.part = pos;
        this.persistent = pers;
        this.headwords = forms;
    }

    @Override
    public Spelling defaultSpelling() throws Exception {
        return this.delegate().defaultSpelling();
    }

    @Override
    public Spelling wordForm(
        final GrammaticalMeaning grammemes
    ) throws Exception {
        return this.delegate().wordForm(grammemes);
    }

    @Override
    public GrammaticalMeaning persistentGrammemes() throws Exception {
        return this.delegate().persistentGrammemes();
    }

    /**
     * Creates a basic delegate.
     * @return Basic delegate
     * @throws Exception If fails
     */
    private Lexeme delegate() throws Exception {
        return new BasicLexeme(
            this.persistent,
            this.forms()
        );
    }

    /**
     * Generates all word forms of this lexeme.
     * @return All word forms of this lexeme
     * @throws Exception If fails
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    private ImmutableMap<GrammaticalMeaning, Spelling> forms()
        throws Exception {
        final ImmutableMap.Builder<GrammaticalMeaning, Spelling> builder =
            ImmutableMap.builder();
        final ImmutableSet<ImmutableSet<Grammeme>> headwordMeanings =
            this.headwords.keySet().stream()
            .map(Rethrowing.rethrowFunction(GrammaticalMeaning::grammemes))
            .collect(Collectors.toImmutableSet());
        builder.putAll(this.headwords);
        for (final GrammaticalMeaning meaning : this.part.meaningVariations()) {
            if (headwordMeanings.contains(meaning.grammemes())) {
                continue;
            }
            final GrammaticalMeaning closest =
                this.closestHeadwordMeaning(meaning);
            builder.put(
                meaning,
                this.part.lexeme(
                    this.headwords.get(closest),
                    new GmCombined(
                        ImmutableList.of(
                            this.persistent,
                            closest
                        )
                    )
                )
                    .wordForm(meaning)
            );
        }
        return builder.build();
    }

    /**
     * Returns the headword whose grammatical meaning is the closest to the
     * given meaning.
     * @param meaning Grammatical meaning
     * @return Headword whose grammatical meaning is the closest to the given
     *  meaning.
     * @checkstyle IllegalCatchCheck (22 lines)
     */
    @SuppressWarnings(
        {
            "PMD.AvoidCatchingGenericException",
            "PMD.OnlyOneReturn"
        }
        )
    private GrammaticalMeaning closestHeadwordMeaning(
        final GrammaticalMeaning meaning
    ) {
        return Collections.max(
            this.headwords.keySet(),
            (one, another) -> {
                final int compare;
                try {
                    compare = Integer.compare(
                        new GmWithSimilarity(one)
                            .similarity(meaning),
                        new GmWithSimilarity(another)
                            .similarity(meaning)
                    );
                } catch (final Exception ex) {
                    throw new IllegalStateException(ex);
                }
                return compare;
            }
        );
    }
}
