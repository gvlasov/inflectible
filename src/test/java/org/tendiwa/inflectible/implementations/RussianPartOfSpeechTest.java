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
import org.mockito.Mockito;
import org.tendiwa.inflectible.GrammaticalCategory;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.SpBasic;
import org.tendiwa.inflectible.Spelling;

/**
 * Unit tests for {@link RussianPartOfSpeech}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class RussianPartOfSpeechTest {
    /**
     * {@link RussianPartOfSpeech} can tell if it is using a particular
     * {@link org.tendiwa.inflectible.GrammaticalCategory}.
     * @throws Exception If fails
     */
    @Test
    public void canTellIfUsesCategory() throws Exception {
        MatcherAssert.assertThat(
            RussianPartOfSpeech.Глаг.usesCategory(
                Mockito.mock(GrammaticalCategory.class)
            ),
            CoreMatchers.is(false)
        );
        MatcherAssert.assertThat(
            RussianPartOfSpeech.Глаг.usesCategory(
                RussianGrammaticalCategory.Число
            ),
            CoreMatchers.is(true)
        );
    }

    /**
     * {@link RussianPartOfSpeech} can generate spellings for Russian nouns.
     * @throws Exception If fails
     */
    @Test
    public void canGenerateNouns() throws Exception {
        MatcherAssert.assertThat(
            RussianPartOfSpeech.Сущ.lexeme(
                new SpBasic("Игорь"),
                () -> ImmutableSet.of(RussianGrammeme.Муж)
            )
                .wordForm(() -> ImmutableSet.of(RussianGrammeme.В))
                .string(),
            CoreMatchers.equalTo("Игоря")
        );
    }

    /**
     * {@link RussianPartOfSpeech#Глаг} can not do anything sensible yet.
     * @throws Exception If fails
     */
    @Test(expected = UnsupportedOperationException.class)
    public void verbUnsupported() throws Exception {
        RussianPartOfSpeech.Глаг.lexeme(
            Mockito.mock(Spelling.class),
            Mockito.mock(GrammaticalMeaning.class)
        );
    }

    /**
     * {@link RussianPartOfSpeech#Прил} can not do anything sensible yet.
     * @throws Exception If fails
     */
    @Test(expected = UnsupportedOperationException.class)
    public void adjectiveUnsupported() throws Exception {
        RussianPartOfSpeech.Прил.lexeme(
            Mockito.mock(Spelling.class),
            Mockito.mock(GrammaticalMeaning.class)
        );
    }

    /**
     * {@link RussianPartOfSpeech#Нареч} can not do anything sensible yet.
     * @throws Exception If fails
     */
    @Test(expected = UnsupportedOperationException.class)
    public void adverbUnsupported() throws Exception {
        RussianPartOfSpeech.Нареч.lexeme(
            Mockito.mock(Spelling.class),
            Mockito.mock(GrammaticalMeaning.class)
        );
    }

    /**
     * {@link RussianPartOfSpeech#Местоим} can not do anything sensible yet.
     * @throws Exception If fails
     */
    @Test(expected = UnsupportedOperationException.class)
    public void pronounUnsupported() throws Exception {
        RussianPartOfSpeech.Местоим.lexeme(
            Mockito.mock(Spelling.class),
            Mockito.mock(GrammaticalMeaning.class)
        );
    }
}
