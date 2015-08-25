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

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.inflectible.Grammar;
import org.tendiwa.inflectible.TemplateName;

/**
 * Unit tests for {@link ParsedTemplatuary}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class ParsedTemplatuaryTest {
    /**
     * ParsedTextuary can load templates from an input stream.
     * @throws Exception If fails
     */
    @Test
    public void loadsTexts() throws Exception {
        MatcherAssert.assertThat(
            new ParsedTemplatuary(
                Mockito.mock(Grammar.class),
                ImmutableList.of(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            "id(args, more) {",
                            "  things [more] [args]<;more>",
                            "} ",
                            "id.compound(hey, you) {",
                            " [hey] [you]",
                            "}  "
                        )
                    )
                )
            ).hasTemplate(new TemplateName("id.compound")),
            CoreMatchers.is(true)
        );
    }

    /**
     * ParsedTextuary can fail if it can't read from its input stream.
     * @throws Exception If fails
     */
    @Test(expected = IOException.class)
    public void failsWithBadInput() throws Exception {
        final InputStream badInput = Mockito.mock(InputStream.class);
        Mockito.when(badInput.read()).thenThrow(new IOException());
        new ParsedTemplatuary(
            Mockito.mock(Grammar.class),
            ImmutableList.of(badInput)
        );
    }
}
