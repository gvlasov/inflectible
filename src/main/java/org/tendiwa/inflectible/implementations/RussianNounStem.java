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
import org.tendiwa.inflectible.SpBasic;
import org.tendiwa.inflectible.Spelling;
import org.tendiwa.inflectible.inflection.Stem;

/**
 * Stem of a Russian noun.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 * @checkstyle MultipleStringLiteralsCheck (300 lines)
 * @checkstyle MethodNameCheck (400 lines)
 * @checkstyle BooleanExpressionComplexity (500 lines)
 * @checkstyle NestedIfDepthCheck (300 lines)
 * @checkstyle JavaNCSSCheck (300 lines)
 * @checkstyle MethodLengthCheck (300 lines)
 * @checkstyle CyclomaticComplexityCheck (300 lines)
 * @checkstyle ExecutableStatementCountCheck (300 lines)
 */
@SuppressWarnings(
        {
            "PMD.TooManyMethods",
            "PMD.ExcessiveMethodLength",
            "PMD.CyclomaticComplexity",
            "PMD.StdCyclomaticComplexity",
            "PMD.ModifiedCyclomaticComplexity",
            "PMD.NcssMethodCount",
            "PMD.GodClass"
        }
    )
final class RussianNounStem implements Stem {
    /**
     * Consonants of Russian language.
     */
    private static final Set<Character> CONSONANTS = ImmutableSet.of(
        'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н', 'п', 'р', 'с',
        'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'
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
     * Ctor.
     * @param persistent Grammemes persistent of a stem. May be a superset of
     *  the persistent grammemes of the stem's lexeme (see: suppletivism;
     *  grammeme Past Tense would be persistent for the stem went)
     * @param form Spelling of the headword from which other forms of this stem
     *  are derived.
     */
    RussianNounStem(
        final GrammaticalMeaning persistent,
        final Spelling form
    ) {
        this.meaning = persistent;
        this.headword = form;
    }

    @Override
    public Spelling form(final GrammaticalMeaning target) throws Exception {
        final GrammaticalMeaning combined = new GmCombined(
            ImmutableList.of(this.meaning, target)
        );
        final Grammeme gender = RussianGrammaticalCategory.Род.getGrammeme(
            combined
        );
        final Grammeme gramcase = RussianGrammaticalCategory.Падеж.getGrammeme(
            combined
        );
        final Grammeme number = RussianGrammaticalCategory.Число.getGrammeme(
            combined
        );
        final Spelling answer;
        if (gramcase == RussianGrammeme.И) {
            if (number == RussianGrammeme.Ед) {
                answer = this.headword;
            } else {
                if (this.мамаПапа(gender) || this.кот(gender)) {
                    answer = this.formWithEnding("ы");
                } else if (
                    this.тётяДядя(gender)
                        || this.метель(gender)
                        || this.гусь(gender)
                ) {
                    answer = this.formWithEnding("и");
                } else if (this.море(gender)) {
                    answer = this.formWithEnding("я");
                } else if (this.окно(gender)) {
                    answer = this.formWithEnding("а");
                } else {
                    answer = this.headword;
                }
            }
        } else if (gramcase == RussianGrammeme.Р) {
            if (number == RussianGrammeme.Ед) {
                if (this.мамаПапа(gender)) {
                    answer = this.formWithEnding("ы");
                } else if (this.тётяДядя(gender) || this.метель(gender)) {
                    answer = this.formWithEnding("и");
                } else if (this.кот(gender) || this.окно(gender)) {
                    answer = this.formWithEnding("a");
                } else if (this.море(gender) || this.гусь(gender)) {
                    answer = this.formWithEnding("я");
                } else {
                    answer = this.headword;
                }
            } else {
                if (this.мамаПапа(gender)) {
                    answer = this.stem();
                } else if (this.тётяДядя(gender)) {
                    answer = this.formWithEnding("ь");
                } else if (this.метель(gender) || this.гусь(gender)) {
                    answer = this.formWithEnding("ей");
                } else if (this.кот(gender)) {
                    answer = this.formWithEnding("ов");
                } else if (this.окно(gender)) {
                    throw new UnsupportedOperationException();
                } else if (this.море(gender)) {
                    answer = this.formWithEnding("ей");
                } else {
                    answer = this.headword;
                }
            }
        } else if (gramcase == RussianGrammeme.Д) {
            if (number == RussianGrammeme.Ед) {
                if (this.мамаПапа(gender) || this.тётяДядя(gender)) {
                    answer = this.formWithEnding("е");
                } else if (this.метель(gender)) {
                    answer = this.formWithEnding("и");
                } else if (this.кот(gender)) {
                    answer = this.formWithEnding("у");
                } else if (this.окно(gender)) {
                    throw new UnsupportedOperationException();
                } else if (this.море(gender) || this.гусь(gender)) {
                    answer = this.formWithEnding("ю");
                } else {
                    answer = this.headword;
                }
            } else {
                if (
                    this.мамаПапа(gender)
                        || this.кот(gender)
                        || this.окно(gender)
                    ) {
                    answer = this.formWithEnding("ам");
                } else if (
                    this.тётяДядя(gender)
                        || this.метель(gender)
                        || this.море(gender)
                        || this.гусь(gender)
                ) {
                    answer = this.formWithEnding("ям");
                } else {
                    answer = this.headword;
                }
            }
        } else if (gramcase == RussianGrammeme.В) {
            if (number == RussianGrammeme.Ед) {
                if (this.мамаПапа(gender)) {
                    answer = this.formWithEnding("у");
                } else if (this.тётяДядя(gender)) {
                    answer = this.formWithEnding("ю");
                } else if (this.кот(gender)) {
                    answer = this.formWithEnding("а");
                } else if (this.гусь(gender)) {
                    answer = this.formWithEnding("я");
                } else {
                    answer = this.headword;
                }
            } else {
                if (this.мамаПапа(gender)) {
                    answer = this.stem();
                } else if (this.тётяДядя(gender)) {
                    answer = this.formWithEnding("ь");
                } else if (
                    this.метель(gender)
                        || this.море(gender)
                        || this.гусь(gender)
                ) {
                    answer = this.formWithEnding("ей");
                } else if (this.кот(gender)) {
                    answer = this.formWithEnding("ов");
                } else if (this.окно(gender)) {
                    answer = this.formWithEnding("а");
                } else {
                    answer = this.headword;
                }
            }
        } else if (gramcase == RussianGrammeme.Т) {
            if (number == RussianGrammeme.Ед) {
                if (this.мамаПапа(gender)) {
                    answer = this.formWithEnding("ой");
                } else if (this.тётяДядя(gender)) {
                    answer = this.formWithEnding("ей");
                } else if (this.метель(gender)) {
                    answer = this.formWithEnding("ью");
                } else if (this.кот(gender)) {
                    answer = this.formWithEnding("ом");
                } else if (this.море(gender) || this.гусь(gender)) {
                    answer = this.formWithEnding("ем");
                } else {
                    answer = this.headword;
                }
            } else {
                if (this.мамаПапа(gender) || this.кот(gender)) {
                    answer = this.formWithEnding("ами");
                } else if (
                    this.тётяДядя(gender)
                        || this.метель(gender)
                        || this.море(gender)
                        || this.гусь(gender)
                ) {
                    answer = this.formWithEnding("ями");
                } else if (this.окно(gender)) {
                    throw new UnsupportedOperationException();
                } else {
                    answer = this.headword;
                }
            }
        } else if (gramcase == RussianGrammeme.П) {
            if (number == RussianGrammeme.Ед) {
                if (
                    this.мамаПапа(gender)
                        || this.тётяДядя(gender)
                        || this.кот(gender)
                        || this.море(gender)
                        || this.окно(gender)
                        || this.гусь(gender)
                ) {
                    answer = this.formWithEnding("е");
                } else if (this.метель(gender)) {
                    answer = this.formWithEnding("и");
                } else {
                    answer = this.headword;
                }
            } else {
                if (
                    this.мамаПапа(gender)
                        || this.кот(gender)
                        || this.окно(gender)
                    ) {
                    answer = this.formWithEnding("ах");
                } else if (
                    this.тётяДядя(gender)
                        || this.метель(gender)
                        || this.море(gender)
                        || this.гусь(gender)
                ) {
                    answer = this.formWithEnding("ях");
                } else {
                    answer = this.headword;
                }
            }
        } else {
            answer = this.headword;
        }
        return answer;
    }

    /**
     * Creates the spelling of the stem.
     * @return Spelling of the stem
     * @throws Exception If fails
     */
    private Spelling stem() throws Exception {
        final String answer;
        if (this.isPlural()) {
            answer = this.withoutLastLetter();
        } else if (
            this.lastChar() == 'а'
                || this.lastChar() == 'ь'
                || this.lastChar() == 'й'
                || this.lastChar() == 'е'
                || this.lastChar() == 'я'
                || this.lastChar() == 'о'
        ) {
            answer = this.withoutLastLetter();
        } else {
            answer = this.headword.string();
        }
        return new SpBasic(answer);
    }

    /**
     * Returns the last character of the headword.
     * @return Last character of the headword
     */
    private char lastChar() {
        return this.headword.string().charAt(
            this.headword.string().length() - 1
        );
    }

    /**
     * Returns the headword without the last letter.
     * @return Headword without the last letter
     */
    private String withoutLastLetter() {
        return this.headword.string().substring(
            0,
            this.headword.string().length() - 1
        );
    }

    /**
     * Checks if this stem is plural by default.
     * @return True iff this stem is plural by default
     * @throws Exception If fails
     */
    private boolean isPlural() throws Exception {
        return this.meaning.grammemes().contains(RussianGrammeme.Мн);
    }

    /**
     * Checks if this stem inflects like word окно.
     * @param gender Gender
     * @return True iff it inflects that way
     */
    private boolean окно(final Grammeme gender) {
        return gender == RussianGrammeme.Средн && this.lastChar() == 'о';
    }

    /**
     * Checks if this stem inflects like word море.
     * @param gender Gender
     * @return True iff it inflects that way
     */
    private boolean море(final Grammeme gender) {
        return gender == RussianGrammeme.Средн && this.lastChar() == 'е';
    }

    /**
     * Checks if this stem inflects like word кот.
     * @param gender Gender
     * @return True iff it inflects that way
     * @throws Exception If fails
     */
    private boolean кот(final Grammeme gender) throws Exception {
        return gender == RussianGrammeme.Муж
            && RussianNounStem.CONSONANTS.contains(this.lastChar())
            && this.lastChar() != 'й'
            || this.isPlural()
            && this.lastChar() == 'ы';
    }

    /**
     * Checks if this stem inflects like word гусь.
     * @param gender Gender
     * @return True iff it inflects that way
     */
    private boolean гусь(final Grammeme gender) {
        return gender == RussianGrammeme.Муж
            && (this.lastChar() == 'ь' || this.lastChar() == 'й');
    }

    /**
     * Checks if this stem inflects like word метель.
     * @param gender Gender
     * @return True iff it inflects that way
     * @throws Exception If fails
     */
    private boolean метель(final Grammeme gender) throws Exception {
        return gender == RussianGrammeme.Жен && this.lastChar() == 'ь'
            || this.isPlural() && this.lastChar() == 'и';
    }

    /**
     * Checks if this stem inflects like words тётя or дядя.
     * @param gender Gender
     * @return True iff it inflects that way
     */
    private boolean тётяДядя(final Grammeme gender) {
        return gender != RussianGrammeme.Средн && this.lastChar() == 'я';
    }

    /**
     * Checks if this stem inflects like words мама or папа.
     * @param gender Gender
     * @return True iff it inflects that way
     */
    private boolean мамаПапа(final Grammeme gender) {
        return gender != RussianGrammeme.Средн && this.lastChar() == 'а';
    }

    /**
     * Creates a word form derived from this stem with particular ending
     * appended after the stem.
     * @param ending Ending
     * @return Word form derived from this stem with particular ending
     *  appended after the stem
     * @throws Exception If fails
     */
    private Spelling formWithEnding(final String ending) throws Exception {
        return new SpStemWithEnding(this.stem(), ending);
    }
}
