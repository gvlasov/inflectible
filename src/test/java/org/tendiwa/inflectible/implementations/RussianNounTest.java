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
import org.junit.Ignore;
import org.junit.Test;
import org.tendiwa.inflectible.SpBasic;

/**
 * Unit tests for {@link RussianNoun}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class RussianNounTest {
    /**
     * {@link RussianNoun} can produce forms of nouns like кот, голод, нос.
     * @throws Exception If fails
     */
    @Test
    public void producesFormPrivet() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("привет"),
                () -> ImmutableSet.of(RussianGrammeme.Муж)
            )
                .wordForm(
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
     * {@link RussianNoun} can produce forms of words like мама, папа, коза,
     * голова.
     * @throws Exception If fails
     */
    @Test
    public void producesFormKoza() throws Exception {
        final RussianNoun noun = new RussianNoun(
            new SpBasic("коза"),
            () -> ImmutableSet.of(RussianGrammeme.Жен)
        );
        MatcherAssert.assertThat(
            noun.wordForm(
                () -> ImmutableSet.of(
                    RussianGrammeme.Мн,
                    RussianGrammeme.Д
                )
            )
                .string(),
            CoreMatchers.equalTo("козам")
        );
        MatcherAssert.assertThat(
            noun.wordForm(
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
     * {@link RussianNoun} can produce forms of words like метель, голень,
     * шелупонь.
     * @throws Exception If fails
     */
    @Test
    public void producesFormShelupon() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("шелупонь"),
                () -> ImmutableSet.of(RussianGrammeme.Жен)
            )
                .wordForm(
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
     * {@link RussianNoun} can produce forms of words like гусь, конь, Игорь.
     * @throws Exception If fails
     */
    @Test
    public void producesFormIgor() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("Игорь"),
                () -> ImmutableSet.of(RussianGrammeme.Муж)
            )
                .wordForm(
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
     * {@link RussianNoun} can produce forms of words like море, горе.
     * @throws Exception If fails
     */
    @Test
    public void producesFormGore() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("горе"),
                () -> ImmutableSet.of(RussianGrammeme.Средн)
            )
                .wordForm(
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
     * {@link RussianNoun} can produce forms of words like дядя, тётя, пилуля,
     * сынуля.
     * @throws Exception If fails
     */
    @Test
    public void producesFormPilula() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("пилюля"),
                () -> ImmutableSet.of(RussianGrammeme.Жен)
            )
                .wordForm(
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
     * {@link RussianNoun} can produce forms of words like окно, сукно, бревно.
     * @throws Exception If fails
     */
    @Test
    public void producesFormBrevno() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("бревно"),
                () -> ImmutableSet.of(RussianGrammeme.Средн)
            )
                .wordForm(
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
     * {@link RussianNoun} can produce forms of words whose headword is plural,
     * like штаны, трусы, нарты.
     * @throws Exception If fails
     */
    @Test
    public void producesFormFromPluralHeadwordShtany() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("штаны"),
                () -> ImmutableSet.of(RussianGrammeme.Мн)
            )
                .wordForm(
                    () -> ImmutableSet.of(
                        RussianGrammeme.П
                    )
                )
                .string(),
            CoreMatchers.equalTo("штанах")
        );
    }

    /**
     * {@link RussianNoun} can produce forms of words whose headword is plural,
     * like сани, гусли.
     * @throws Exception If fails
     */
    @Test
    public void producesFormFromPluralHeadwordGusly() throws Exception {
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic("гусли"),
                () -> ImmutableSet.of(RussianGrammeme.Мн)
            )
                .wordForm(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Т
                    )
                )
                .string(),
            CoreMatchers.equalTo("гуслями")
        );
    }

    /**
     * {@link RussianNoun} can retain the same form for every grammatical
     * meaning in a lexeme that isn't affected by inflection.
     * @throws Exception If fails
     */
    @Test
    public void retainsUninflectibleWords() throws Exception {
        final String coffee = "кофе";
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic(coffee),
                () -> ImmutableSet.of(RussianGrammeme.Муж)
            )
                .wordForm(
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
     * {@link RussianNoun} can produce forms of words like буй, хуй,
     *  долбоклюй, Ной.
     * @throws Exception If fails
     */
    @Test
    @Ignore
    public void producesFormBooy() throws Exception {
        final String word = "буй";
        MatcherAssert.assertThat(
            new RussianNoun(
                new SpBasic(word),
                () -> ImmutableSet.of(RussianGrammeme.Муж)
            )
                .wordForm(
                    () -> ImmutableSet.of(
                        RussianGrammeme.Ед,
                        RussianGrammeme.И
                    )
                )
                .string(),
            CoreMatchers.equalTo(word)
        );
    }
}
