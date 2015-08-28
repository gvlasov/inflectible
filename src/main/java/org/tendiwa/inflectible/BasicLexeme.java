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
package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * {@link Lexeme} defined by its set of persistent grammemes and a map of
 * possible spellings.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class BasicLexeme implements Lexeme {
    /**
     * Persistent grammatical meaning.
     */
    private final transient GrammaticalMeaning persistent;

    /**
     * Word forms.
     */
    private final transient ImmutableMap<GrammaticalMeaning, Spelling> forms;

    /**
     * Ctor.
     * @param grammemes Persistent grammemes
     * @param spellings Map from grammatical meanings to spellings
     */
    public BasicLexeme(
        final GrammaticalMeaning grammemes,
        final ImmutableMap<GrammaticalMeaning, Spelling> spellings
    ) {
        this.persistent = grammemes;
        this.forms = spellings;
    }

    @Override
    public Spelling defaultSpelling() {
        return this.searchableMap().get(
            this.searchableMap().keySet().iterator().next()
        );
    }

    @Override
    public Spelling wordForm(final GrammaticalMeaning grammemes) {
        int bestScore = 0;
        final GmWithSimilarity similar = new GmWithSimilarity(grammemes);
        Spelling bestMatch = this.defaultSpelling();
        for (final GrammaticalMeaning meaning : this.forms.keySet()) {
            final int score = similar.similarity(meaning);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = this.forms.get(meaning);
            }
        }
        return bestMatch;
    }

    @Override
    public GrammaticalMeaning persistentGrammemes() {
        return this.persistent;
    }

    // To be refactored in #47
    /**
     * Creates a map from grammeme sets to spellings. Unlike in
     * {@link BasicLexeme#forms}, in this map we can efficiently search for
     * elements with particular grammatical meaning.
     * @return Searchable map
     */
    private ImmutableMap<ImmutableSet<Grammeme>, Spelling> searchableMap() {
        final ImmutableMap.Builder<ImmutableSet<Grammeme>, Spelling> builder =
            ImmutableMap.builder();
        for (final GrammaticalMeaning meaning : this.forms.keySet()) {
            builder.put(meaning.grammemes(), this.forms.get(meaning));
        }
        return builder.build();
    }
}
