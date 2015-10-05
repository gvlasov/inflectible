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
import java.util.Collection;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Spelling;
import org.tendiwa.inflectible.inflection.Stem;

/**
 * Stem of a Russian noun.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
final class RussianNounStem implements Stem {
    /**
     * Possible endings of headwords in some declensions.
     */
    private static final Collection<Character> ENDINGS = ImmutableSet.of(
        'а', 'ь', 'й', 'е', 'я', 'о'
    );

    /**
     * Grammatical meaning of this stem.
     */
    private final transient GrammaticalMeaning meaning;

    /**
     * Spelling of the headword from which other forms of this stem are derived.
     */
    private final transient Spelling headword;

    /**
     * Declension of this noun.
     */
    private final transient Declension declension;

    /**
     * Ctor.
     * @param form Spelling of the headword from which other forms of this stem
     *  are derived.
     * @param persistent Grammemes persistent of a stem. May be a superset of
     *  the persistent grammemes of the stem's lexeme (see: suppletivism;
     *  grammeme Past Tense would be persistent for the stem <i>went</i>
     *  because GO is a suppletive lexeme, but a word form <i>listened</i> of a
     *  non-suppletive lexeme LISTEN would not have any persistent grammemes)
     * @param decl Declension Declension of the noun
     */
    RussianNounStem(
        final Spelling form,
        final GrammaticalMeaning persistent,
        final Declension decl
    ) {
        this.headword = form;
        this.meaning = persistent;
        this.declension = decl;
    }

    // To be refactored in #47
    @Override
    public String spelling() throws Exception {
        final String answer;
        if (this.isUniflectible()) {
            answer = this.headword.string();
        } else if (this.isPlural()) {
            answer = this.headword.withoutLastLetter();
        } else if (this.headwordHasEnding()) {
            answer = this.headword.withoutLastLetter();
        } else {
            answer = this.headword.string();
        }
        return answer;
    }

    /**
     * Checks if this word does not inflect.
     * @return True iff this word does not inflect
     */
    private boolean isUniflectible() {
        return this.declension == RussianDeclension.Кофе;
    }

    /**
     * Checks if the headword is plural.
     * @return True iff the headword is plural.
     * @throws Exception If could not check
     */
    private boolean isPlural() throws Exception {
        return this.meaning.grammemes().contains(RussianGrammeme.Мн);
    }

    /**
     * Checks if the headword has an ending after its stem.
     * @return True iff the headword has an ending after its stem.
     */
    private boolean headwordHasEnding() {
        return RussianNounStem.ENDINGS.contains(this.headword.lastChar());
    }
}
