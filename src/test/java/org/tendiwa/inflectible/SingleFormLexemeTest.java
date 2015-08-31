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

import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link SingleFormLexeme}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class SingleFormLexemeTest {
    /**
     * {@link SingleFormLexeme} can have default spelling that returns
     * lexeme's argument.
     * @throws Exception If fails
     */
    @Test
    public void hasDefaultSpelling() throws Exception {
        final String dude = "dude";
        MatcherAssert.assertThat(
            new SingleFormLexeme(dude).defaultSpelling().string(),
            CoreMatchers.equalTo(dude)
        );
    }

    /**
     * {@link SingleFormLexeme} can always return empty persistent
     * grammatical meaning.
     * @throws Exception If fails
     */
    @Test
    public void returnsEmptyPersistentGrammaticalMeaning() throws Exception {
        MatcherAssert.assertThat(
            new SingleFormLexeme("man").persistentGrammemes()
                .grammemes().isEmpty(),
            CoreMatchers.is(true)
        );
    }

    /**
     * {@link SingleFormLexeme} can return its headword against non-default
     * grammatical meanings.
     * @throws Exception If fails
     */
    @Test
    public void returnsSameWordForm() throws Exception {
        final String woman = "woman";
        MatcherAssert.assertThat(
            new SingleFormLexeme(woman).wordForm(
                ()-> ImmutableSet.of(
                    Mockito.mock(Grammeme.class),
                    Mockito.mock(Grammeme.class),
                    Mockito.mock(Grammeme.class)
                )
            )
                .string(),
            CoreMatchers.equalTo(woman)
        );
    }
}
