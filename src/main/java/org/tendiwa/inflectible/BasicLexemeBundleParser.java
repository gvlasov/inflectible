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
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.tendiwa.inflectible.antlr.LexemeBundleLexer;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;

/**
 * A convenience descendant of {@link LexemeBundleParser} created from an
 * {@link InputStream} without explicitly specifying any additional plumbing.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicLexemeBundleParser extends LexemeBundleParser {
    /**
     * Ctor.
     * @param input Input stream with lexemes' markup
     */
    public BasicLexemeBundleParser(final InputStream input) {
        super(
            new CommonTokenStream(
                new LexemeBundleLexer(
                    BasicLexemeBundleParser.createAntlrInputStream(input)
                )
            )
        );
    }

    /**
     * Ctor.
     * @param markup Lexemes' markup
     */
    public BasicLexemeBundleParser(final String... markup) {
        this(
            IOUtils.toInputStream(
                Joiner.on('\n').join(markup)
            )
        );
    }

    /**
     * Creates an ANTLR input stream.
     * @param input Input stream with lexemes' markup
     * @return An ANTLR input stream with lexemes' characters
     */
    private static ANTLRInputStream createAntlrInputStream(
        final InputStream input
    ) {
        try {
            return new ANTLRInputStream(input);
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
