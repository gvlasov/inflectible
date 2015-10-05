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
import lombok.EqualsAndHashCode;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Grammeme;

/**
 * Grammatical meaning used for identification of Russian noun word forms.
 * Contains only those grammemes which can be present in Russian nouns.
 * <p/>
 * Implements {@link Object#equals(Object)} and {@link Object#hashCode()}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
@EqualsAndHashCode(of = {"gramnumber", "gramcase"})
public final class GmOfRussianNoun implements GrammaticalMeaning {
    /**
     * Grammatical number.
     */
    private final transient Grammeme gramnumber;

    /**
     * Grammatical case.
     */
    private final transient Grammeme gramcase;

    /**
     * Ctor.
     * @param num Grammatical number
     * @param cas Grammatical case
     */
    public GmOfRussianNoun(final Grammeme num, final Grammeme cas) {
        this.gramnumber = num;
        this.gramcase = cas;
    }

    /**
     * Ctor. Picks only {@link RussianGrammaticalCategory#Число} (number) and
     * {@link RussianGrammaticalCategory#Падеж} (case) from a grammatical
     * meaning.
     * @param meaning Grammatical meaning
     * @throws Exception If could not extract number or case
     */
    public GmOfRussianNoun(final GrammaticalMeaning meaning) throws Exception {
        this(
            RussianGrammaticalCategory.Число.getGrammeme(meaning),
            RussianGrammaticalCategory.Падеж.getGrammeme(meaning)
        );
    }

    // To be refactored in #47
    @Override
    public ImmutableSet<Grammeme> grammemes() throws Exception {
        return ImmutableSet.of(this.gramnumber, this.gramcase);
    }
}
