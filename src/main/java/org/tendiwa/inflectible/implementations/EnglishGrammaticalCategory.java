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
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Grammeme;
import org.tenidwa.collections.utils.Collectors;

/**
 * Grammatical categories of English language.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public enum EnglishGrammaticalCategory implements GrammaticalCategory {
    /**
     * Grammatical number.
     */
    Number,

    /**
     * Grammatical tense.
     */
    Tense,

    /**
     * Grammatical person.
     */
    Person,

    /**
     * Verb form.
     */
    Form;

    @Override
    public Grammeme defaultGrammeme() {
        return this.grammemes().get(0);
    }

    @Override
    public boolean containsGrammeme(final Grammeme grammeme) {
        return this.grammemes().contains(grammeme);
    }

    @Override
    public Grammeme getGrammeme(
        final GrammaticalMeaning meaning
    ) throws Exception {
        return meaning.grammemes()
            .stream()
            .filter(g->g.category() == this)
            .findFirst()
            .get();
    }

    /**
     * Returns grammemes of this grammatical category.
     * @return Grammemes of this grammatical category
     */
    private ImmutableList<Grammeme> grammemes() {
        return ImmutableList
            .copyOf(EnglishGrammeme.values())
            .stream()
            .filter(grammeme -> grammeme.category() == this)
            .collect(Collectors.toImmutableList());
    }
}
