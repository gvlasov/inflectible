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

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link MissingLexeme}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.
 */
public final class MissingLexemeTest {
    /**
     * {@link MissingLexeme} can have spelling that consists of an error
     * message and the identifier of a concept.
     * @throws Exception If fails
     */
    @Test
    public void createsSpellingOfErrorMessageAndConceptId() throws Exception {
        final Language language = Mockito.mock(Language.class);
        Mockito.when(language.missingLexemeFormat())
            .thenReturn("[Missing lexeme %s]");
        MatcherAssert.assertThat(
            new MissingLexeme(
                () -> "DUDE",
                language
            )
                .defaultSpelling()
                .string(),
            CoreMatchers.equalTo("[Missing lexeme DUDE]")
        );
    }

    /**
     * {@link MissingLexeme} can always be with no grammemes.
     * @throws Exception If fails
     */
    @Test
    public void hasNoPersistentGrammemes() throws Exception {
        final Language language = Mockito.mock(Language.class);
        Mockito.when(language.missingLexemeFormat())
            .thenReturn("[No such lexeme %s]");
        MatcherAssert.assertThat(
            new MissingLexeme(
                () -> "CAT",
                language
            )
                .persistentGrammemes()
                .grammemes()
                .isEmpty(),
            CoreMatchers.is(true)
        );
    }
}
