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

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link ParsedTextuary}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedTextuaryTest {
    /**
     * ParsedTextuary can load templates from an input stream.
     * @throws Exception If fails
     */
    @Test
    public void loadsTexts() throws Exception {
        Assert.assertEquals(
            2,
            new ParsedTextuary(
                Mockito.mock(Grammar.class),
                ImmutableList.of(
                    IOUtils.toInputStream(
                        Joiner.on('\n').join(
                            "id2(args2, more) {",
                            "  things [more] [args2][;more]",
                            "} ",
                            "id3.compund(hey, you) {",
                            " [hey] [you]",
                            "}  "
                        )
                    )
                )
            )
                .size()
        );
    }

    /**
     * ParsedTextuary can fail if it can't read from its input stream.
     * @throws Exception If fails
     */
    @Test(expected = UncheckedIOException.class)
    public void failsWithBadInput() throws Exception {
        final InputStream badInput = Mockito.mock(InputStream.class);
        Mockito.when(badInput.read()).thenThrow(new IOException());
        new ParsedTextuary(
            Mockito.mock(Grammar.class),
            ImmutableList.of(badInput)
        );
    }
}
