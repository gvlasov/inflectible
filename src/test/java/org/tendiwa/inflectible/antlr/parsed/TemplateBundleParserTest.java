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
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * Unit tests for {@link TemplateBundleParser}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class TemplateBundleParserTest {
    /**
     * TemplateBundleParser can locate entry arguments in the markup.
     * @throws Exception If fails
     */
    @Test
    public void findsArguments() throws Exception {
        MatcherAssert.assertThat(
            new TemplateBundleParser(
                new CommonTokenStream(
                    new TemplateBundleLexer(
                        new ANTLRInputStream(
                            TemplateBundleLexerTest.class.getResourceAsStream(
                                "messages.ru_RU.texts"
                            )
                        )
                    )
                )
            )
                .templates()
                .template(0)
                .declaredArguments()
                .argumentName(2)
                .getText(),
            CoreMatchers.equalTo("aim")
        );
    }

    /**
     * TemplateBundleParser can handle more than one template per bundle.
     * @throws Exception If fails
     */
    @Test
    public void parsesMultipleEntriesInOneTemplateBundle() throws Exception {
        MatcherAssert.assertThat(
            new TemplateBundleParser(
                new CommonTokenStream(
                    new TemplateBundleLexer(
                        new ANTLRInputStream(
                            IOUtils.toInputStream(
                                Joiner.on('\n').join(
                                    "hey.man(a) {",
                                    "  // Man",
                                    "  Hey, man [a]!",
                                    "}",
                                    "",
                                    "yo.dude(b) {",
                                    "  Yo, dude [b]!",
                                    "} "
                                )
                            )
                        )
                    )
                )
            )
                .templates()
                .template()
                .size(),
            CoreMatchers.equalTo(2)
        );
    }
}
