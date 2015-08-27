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
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.tendiwa.inflectible.antlr.LexemeLexer;
import org.tendiwa.inflectible.antlr.LexemeParser;

/**
 * A convenience descendant of {@link LexemeParser} created from an
 * {@link InputStream} without explicitly specifying any additional plumbing.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class BasicLexemeParser extends LexemeParser {
    /**
     * Ctor.
     * @param input Input stream with lexemes' markup
     * @throws IOException If can't read in input stream
     */
    public BasicLexemeParser(final InputStream input) throws IOException {
        super(
            new CommonTokenStream(
                new LexemeLexer(
                    new ANTLRInputStream(input)
                )
            )
        );
    }

    /**
     * Ctor.
     * @param markup Lexemes' markup
     * @throws IOException If can't read the input stream
     */
    public BasicLexemeParser(final String... markup) throws IOException {
        this(
            IOUtils.toInputStream(
                Joiner.on('\n').join(markup)
            )
        );
    }
}
