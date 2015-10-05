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
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.tendiwa.inflectible.GmCombined;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Grammeme;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.SpBasic;
import org.tendiwa.inflectible.Spelling;
import org.tendiwa.inflectible.inflection.Stem;

/**
 * A Russian noun.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class RussianNoun implements Lexeme {
    /**
     * Consonants of Russian language.
     */
    private static final Set<Character> CONSONANTS = ImmutableSet.of(
        'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н', 'п', 'р', 'с',
        'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'
    );

    /**
     * Headword.
     */
    private final transient Spelling headword;

    /**
     * Persistent grammatical meaning.
     */
    private final transient GrammaticalMeaning persistent;

    /**
     * Ctor.
     * @param hword Headword of this noun.
     * @param meaning Persistent grammatical meaning of this noun.
     */
    RussianNoun(final Spelling hword, final GrammaticalMeaning meaning) {
        this.headword = hword;
        this.persistent = meaning;
    }

    @Override
    public Spelling defaultSpelling() throws Exception {
        return this.headword;
    }

    @Override
    public Spelling wordForm(final GrammaticalMeaning target) throws Exception {
        return new SpBasic(
            String.format(
                "%s%s",
                this.stem().spelling(),
                this.declension().ending(
                    new GmCombined(
                        ImmutableList.of(this.persistent, target)
                    )
                )
            )
        );
    }

    @Override
    public GrammaticalMeaning persistentGrammemes() throws Exception {
        return this.persistent;
    }

    // To be refactored in #47
    /**
     * Returns the stem of this noun.
     * @return Stem of this noun.
     * @throws Exception If could not construct stem
     */
    private Stem stem() throws Exception {
        return new RussianNounStem(
            this.headword,
            this.persistent,
            this.declension()
        );
    }

    /**
     * Returns declension of this noun.
     * @return Declension of this noun.
     * @throws Exception If could not detect declension
     */
    private Declension declension() throws Exception {
        final Grammeme gender = this.gender();
        final Declension declension;
        if (this.мамаПапа(gender)) {
            declension = RussianDeclension.МамаПапа;
        } else if (this.тётяДядя(gender)) {
            declension = RussianDeclension.ДядяТётя;
        } else if (this.гусь(gender)) {
            declension = RussianDeclension.Гусь;
        } else if (this.кот(gender)) {
            declension = RussianDeclension.Кот;
        } else if (this.метель(gender)) {
            declension = RussianDeclension.Метель;
        } else if (this.море(gender)) {
            declension = RussianDeclension.Море;
        } else if (this.окно(gender)) {
            declension = RussianDeclension.Окно;
        }  else {
            declension = RussianDeclension.Кофе;
        }
        return declension;
    }

    /**
     * Returns grammatical gender of this noun.
     * @return Grammatical gender of this noun.
     * @throws Exception If getting the gender grammeme fails.
     */
    private Grammeme gender() throws Exception {
        return RussianGrammaticalCategory.Род.getGrammeme(this.persistent);
    }

    /**
     * Checks if this stem inflects like word окно.
     * @param gender Gender
     * @return True iff it inflects that way
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean окно(final Grammeme gender) {
        return gender == RussianGrammeme.Средн
            && this.headword.lastChar() == 'о';
    }

    /**
     * Checks if this stem inflects like word море.
     * @param gender Gender
     * @return True iff it inflects that way
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean море(final Grammeme gender) {
        return gender == RussianGrammeme.Средн
            && this.headword.lastChar() == 'е';
    }

    /**
     * Checks if this stem inflects like word кот.
     * @param gender Gender
     * @return True iff it inflects that way
     * @throws Exception If fails
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean кот(final Grammeme gender) throws Exception {
        return this.султан(gender) || this.штаны();
    }

    /**
     * Checks if this stem inflects like word штаны.
     * @return True iff it inflects that way
     * @checkstyle MethodNameCheck (3 lines)
     * @throws Exception If could not decide whether this word is plural or not
     */
    private boolean штаны() throws Exception {
        return this.isPlural() && this.headword.lastChar() == 'ы';
    }

    /**
     * Checks if this stem inflects like word султан.
     * @param gender Gender
     * @return True iff it inflects that way
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean султан(final Grammeme gender) {
        return gender == RussianGrammeme.Муж
            && RussianNoun.CONSONANTS.contains(this.headword.lastChar())
            && this.headword.lastChar() != 'й';
    }

    /**
     * Checks if this stem inflects like word гусь.
     * @param gender Gender
     * @return True iff it inflects that way
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean гусь(final Grammeme gender) {
        return gender == RussianGrammeme.Муж
            && (this.headword.lastChar() == 'ь'
            || this.headword.lastChar() == 'й');
    }

    /**
     * Checks if this stem inflects like word метель.
     * @param gender Gender
     * @return True iff it inflects that way
     * @throws Exception If fails
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean метель(
        final Grammeme gender
    ) throws Exception {
        return gender == RussianGrammeme.Жен && this.headword.lastChar() == 'ь'
            || this.isPlural() && this.headword.lastChar() == 'и';
    }

    /**
     * Checks if this stem inflects like words тётя or дядя.
     * @param gender Gender
     * @return True iff it inflects that way
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean тётяДядя(final Grammeme gender) {
        return gender != RussianGrammeme.Средн
            && this.headword.lastChar() == 'я';
    }

    /**
     * Checks if this stem inflects like words мама or папа.
     * @param gender Gender
     * @return True iff it inflects that way
     * @checkstyle MethodNameCheck (3 lines)
     */
    private boolean мамаПапа(final Grammeme gender) {
        return gender != RussianGrammeme.Средн
            && this.headword.lastChar() == 'а';
    }

    /**
     * Checks if this noun is plural.
     * @return True iff this noun is plural.
     * @throws Exception If fails
     */
    private boolean isPlural() throws Exception {
        // @checkstyle LineLengthCheck (2 lines)
        return RussianGrammaticalCategory.Число.getGrammeme(this.persistent) == RussianGrammeme.Мн;
    }
}
