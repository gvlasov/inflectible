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

import java.io.IOException;
import java.io.InputStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.inflectible.antlr.TemplateLexer;

/**
 * Unit tests for {@link BasicTemplateParser}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class BasicTemplateParserTest {
    /**
     * {@link BasicTemplateParser} can fail if an input stream passed to
     * it throws {@link IOException}.
     * @throws Exception If fails
     */
    @Test(expected = IOException.class)
    public void failsWithBadInputStream() throws Exception {
        final InputStream failing = Mockito.mock(InputStream.class);
        Mockito.when(failing.read()).thenThrow(new IOException());
        new BasicTemplateParser(failing);
    }

    /**
     * {@link BasicTemplateParser} can parse escaped characters from a
     * stream.
     * @throws Exception If fails
     */
    @Test
    public void readsEscapedCharacters() throws Exception {
        MatcherAssert.assertThat(
            new BasicTemplateParser(
                TemplateLexer.LINE_CONTENT,
                "Hello, \\[, this is \\\\"
            )
                .rawText()
                .getTokens(TemplateLexer.ESC)
                .size(),
            CoreMatchers.equalTo(2)
        );
    }

    /**
     * {@link BasicTemplateParser} can start parsing in arbitrary mode,
     * not only in the default mode.
     * @throws Exception If fails
     */
    @Test
    public void canStartParsingInArbitraryMode() throws Exception {
        MatcherAssert.assertThat(
            new BasicTemplateParser(
                TemplateLexer.LINE_CONTENT,
                "[man]"
            )
                .singlePartPlaceholder(),
            CoreMatchers.notNullValue()
        );
    }
}
