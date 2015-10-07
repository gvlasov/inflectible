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

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;

/**
 * Grammatical meaning defined by a set of grammemes.
 * <p>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class GmValidated implements GrammaticalMeaning {
    /**
     * Part of speech.
     */
    private final transient PartOfSpeech part;

    /**
     * Grammemes.
     */
    private final transient ImmutableSet<Grammeme> meaning;

    /**
     * Ctor.
     * @param pos Part of speech
     * @param grams Grammemes
     */
    public GmValidated(
        final PartOfSpeech pos,
        final ImmutableSet<Grammeme> grams
    ) {
        this.part = pos;
        this.meaning = grams;
    }

    // To be  refactored in #47
    @Override
    public ImmutableSet<Grammeme> grammemes() throws Exception {
        if (this.hasAlternativeGrammemes()) {
            throw new IllegalStateException(
                String.format(
                    Joiner.on("").join(
                        "Grammatical meaning can't contain grammemes ",
                        "of the same grammatical category: %s"
                    ),
                    this.meaning
                )
            );
        }
        if (this.hasAlienGrammemes()) {
            throw new IllegalStateException(
                String.format(
                    Joiner.on("").join(
                        "Grammatical meaning has grammemes that can't be ",
                        "present in part of speech \"%s\""
                    ),
                    this.part
                )
            );
        }
        return this.meaning;
    }

    /**
     * Checks if this grammatical meaning has grammemes that can't be used for
     * current part of speech.
     * <p/>
     * For example, if a grammatical meaning for an
     * EnglishNoun has a grammeme from the English grammatical category of
     * tense (present, past or future).
     * <p/>
     * Another example would be a grammatical meaning of a noun in one language
     * having an grammeme from another language.
     * @return True iff this grammatical meaning has grammemes alien to its
     *  part of speech.
     * @throws Exception If could not check for category usage
     */
    private boolean hasAlienGrammemes() throws Exception {
        boolean answer = false;
        for (final Grammeme grammeme : this.meaning) {
            if (!this.part.usesCategory(grammeme.category())) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    /**
     * Checks if any of grammemes are from the same
     * {@link org.tendiwa.inflectible.GrammaticalCategory}.
     * @return True iff any of grammemes are from the same grammatical category.
     */
    private boolean hasAlternativeGrammemes() {
        return this.meaning.stream()
            .map(Grammeme::category)
            .distinct()
            .count() != this.meaning.size();
    }
}
