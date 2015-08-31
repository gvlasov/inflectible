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
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.SpBasic;

/**
 * Unit tests for {@link RussianNounStem}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class RussianNounStemTest {
    /**
     * {@link RussianNounStem} can produce forms of nouns like кот, голод,
     * нос.
     * @throws Exception If fails
     */
    @Test
    public void producesFormPrivet() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Муж),
                new SpBasic("привет")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Мн,
                        RussianGrammeme.И
                    )
                )
                .string(),
            CoreMatchers.equalTo("приветы")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words like мама, папа,
     * коза, голова.
     * @throws Exception If fails
     */
    @Test
    public void producesFormKoza() throws Exception {
        final RussianNounStem stem = new RussianNounStem(
            () -> ImmutableSet.of(RussianGrammeme.Жен),
            new SpBasic("коза")
        );
        MatcherAssert.assertThat(
            stem.form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Мн,
                        RussianGrammeme.Д
                    )
                )
                .string(),
            CoreMatchers.equalTo("козам")
        );
        MatcherAssert.assertThat(
            stem.form(
                () -> ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Р
                )
            )
                .string(),
            CoreMatchers.equalTo("коз")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words like метель,
     * голень, шелупонь.
     * @throws Exception If fails
     */
    @Test
    public void producesFormShelupon() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Жен),
                new SpBasic("шелупонь")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Мн,
                        RussianGrammeme.П
                    )
                )
                .string(),
            CoreMatchers.equalTo("шелупонях")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words like гусь, конь,
     * Игорь.
     * @throws Exception If fails
     */
    @Test
    public void producesFormIgor() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Муж),
                new SpBasic("Игорь")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Мн,
                        RussianGrammeme.П
                    )
                )
                .string(),
            CoreMatchers.equalTo("Игорях")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words like море, горе.
     * @throws Exception If fails
     */
    @Test
    public void producesFormGore() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Средн),
                new SpBasic("горе")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Ед,
                        RussianGrammeme.Т
                    )
                )
                .string(),
            CoreMatchers.equalTo("горем")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words like дядя, тётя,
     * пилуля, сынуля.
     * @throws Exception If fails
     */
    @Test
    public void producesFormPilula() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Жен),
                new SpBasic("пилюля")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Ед,
                        RussianGrammeme.В
                    )
                )
                .string(),
            CoreMatchers.equalTo("пилюлю")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words like окно, сукно,
     * бревно.
     * @throws Exception If fails
     */
    @Test
    public void producesFormBrevno() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Средн),
                new SpBasic("бревно")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Мн,
                        RussianGrammeme.В
                    )
                )
                .string(),
            CoreMatchers.equalTo("бревна")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words whose headword is
     *  plural, like штаны, трусы, нарты.
     * @throws Exception If fails
     */
    @Test
    public void producesFormFromPluralHeadwordShtany() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Мн),
                new SpBasic("штаны")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.П
                    )
                )
                .string(),
            CoreMatchers.equalTo("штанах")
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words whose headword is
     *  plural, like сани, гусли.
     * @throws Exception If fails
     */
    @Test
    public void producesFormFromPluralHeadwordGusly() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Мн),
                new SpBasic("гусли")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Т
                    )
                )
                .string(),
            CoreMatchers.equalTo("гуслями")
        );
    }

    /**
     * {@link RussianNounStem} can retains the same form for every
     *  grammatical meaning in a lexeme that isn't affected by inflection.
     * @throws Exception If fails
     */
    @Test
    public void retainsUninflectibleWords() throws Exception {
        final String coffee = "кофе";
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Муж),
                new SpBasic(coffee)
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Ед,
                        RussianGrammeme.П
                    )
                )
                .string(),
            CoreMatchers.equalTo(coffee)
        );
    }

    /**
     * {@link RussianNounStem} can produce forms of words like буй, хуй,
     *  долбоклюй, Ной.
     * @throws Exception If fails
     */
    @Test
    public void producesFormBooy() throws Exception {
        MatcherAssert.assertThat(
            new RussianNounStem(
                () -> ImmutableSet.of(RussianGrammeme.Муж),
                new SpBasic("буй")
            )
                .form(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Ед,
                        RussianGrammeme.П
                    )
                )
                .string(),
            CoreMatchers.equalTo("буе")
        );
    }
}
