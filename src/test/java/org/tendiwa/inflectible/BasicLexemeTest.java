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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link BasicLexeme}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class BasicLexemeTest {
    /**
     * {@link BasicLexeme} can fail if it contains two spellings with
     * equivalent grammatical meanings.
     * @throws Exception If fails
     */
    @Test(expected = IllegalStateException.class)
    public void disallowsSameGrammaticalMeaning() throws Exception {
        final Grammeme one = Mockito.mock(Grammeme.class);
        final Grammeme another = Mockito.mock(Grammeme.class);
        new BasicLexeme(
            new GmEmpty(),
            ImmutableMap.of(
                ()-> ImmutableSet.of(one, another),
                new SpBasic("dude"),
                ()-> ImmutableSet.of(one, another),
                new SpBasic("undude")
            )
        )
            .defaultSpelling();
    }

    /**
     * {@link BasicLexeme} can return the first of the provided spellings as
     * its default spelling.
     * @throws Exception If fails
     */
    @Test
    public void returnsTheFirstSpellingAsDefault() throws Exception {
        final String chair = "chair";
        MatcherAssert.assertThat(
            new BasicLexeme(
                new GmEmpty(),
                ImmutableMap.of(
                    new GmEmpty(),
                    new SpBasic(chair),
                    () -> ImmutableSet.of(Mockito.mock(Grammeme.class)),
                    new SpBasic("table")
                )
            )
                .defaultSpelling()
                .string(),
            CoreMatchers.equalTo(chair)
        );
    }

    /**
     * {@link BasicLexeme} can have persistent grammemes.
     * @throws Exception If fails
     */
    @Test
    public void hasPersistentGrammemes() throws Exception {
        final GrammaticalMeaning persistent = Mockito.mock(
            GrammaticalMeaning.class
        );
        MatcherAssert.assertThat(
            new BasicLexeme(
                persistent,
                ImmutableMap.of(
                    new GmEmpty(),
                    () -> "hey"
                )
            )
                .persistentGrammemes(),
            CoreMatchers.is(persistent)
        );
    }
}
