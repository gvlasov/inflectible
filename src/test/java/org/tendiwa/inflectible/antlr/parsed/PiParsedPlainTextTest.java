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
package org.tendiwa.inflectible.antlr.parsed;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.inflectible.ActualArguments;
import org.tendiwa.inflectible.Vocabulary;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;

/**
 * Unit tests for {@link PiParsedPlainText}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class PiParsedPlainTextTest {
    /**
     * {@link PiParsedPlainText} can obtain text from an ANTLR parse tree with
     * plain text in it.
     * @throws Exception If fails
     */
    @Test
    public void parsesText() throws Exception {
        final String markup = "Hello world";
        MatcherAssert.assertThat(
            new PiParsedPlainText(
                new BasicTemplateBundleParser(
                    TemplateBundleLexer.LINE_CONTENT,
                    markup
                )
                    .rawText()
            )
                .fillUp(
                    Mockito.mock(ActualArguments.class),
                    Mockito.mock(Vocabulary.class)
                ),
            CoreMatchers.equalTo(markup)
        );
    }

    /**
     * {@link PiParsedPlainText} can obtain text from an ANTLR parse tree
     * that contains escaped characters.
     * @throws Exception If fails
     */
    @Test
    public void unescapesEscapeSequences() throws Exception {
        MatcherAssert.assertThat(
            new PiParsedPlainText(
                new BasicTemplateBundleParser(
                    TemplateBundleLexer.LINE_CONTENT,
                    "Hello \\[mom], backslashes: \\\\\\\\"
                )
                    .rawText()
            )
                .fillUp(
                    Mockito.mock(ActualArguments.class),
                    Mockito.mock(Vocabulary.class)
                ),
            CoreMatchers.equalTo("Hello [mom], backslashes: \\\\")
        );
    }
}
