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
 * Grammemes of English grammar.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public enum EnglishGrammeme implements Grammeme {
    /**
     * Singular.
     */
    Sing(EnglishGrammaticalCategory.Number),

    /**
     * Plural.
     */
    Plur(EnglishGrammaticalCategory.Number),

    /**
     * Present tense.
     */
    Present(EnglishGrammaticalCategory.Tense),

    /**
     * Past tense.
     */
    Past(EnglishGrammaticalCategory.Tense),

    /**
     * First person.
     */
    I(EnglishGrammaticalCategory.Person),

    /**
     * Third person.
     */
    III(EnglishGrammaticalCategory.Person),

    /**
     * Infinitive.
     */
    Inf(EnglishGrammaticalCategory.Form),

    /**
     * Gerund.
     */
    Ger(EnglishGrammaticalCategory.Form);

    /**
     * Grammatical category of this grammeme.
     */
    private transient GrammaticalCategory category;

    /**
     * Ctor.
     * @param cat Grammatical category of this grammeme
     */
    EnglishGrammeme(final GrammaticalCategory cat) {
        this.category = cat;
    }

    @Override
    public GrammaticalCategory category() {
        return this.category;
    }
    /**
     * Sets grammatical category for this grammeme. A dirty hack to allow a
     * two-way relation between elements of enums.
     * @param cat Grammatical category
     */
    void setCategory(final GrammaticalCategory cat) {
        this.category = cat;
    }
}
