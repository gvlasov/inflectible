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

import org.tendiwa.inflectible.ActualArguments;
import org.tendiwa.inflectible.PiPlainText;
import org.tendiwa.inflectible.TemplateBodyPiece;
import org.tendiwa.inflectible.Vocabulary;
import org.tendiwa.inflectible.antlr.TemplateParser;

/**
 * Plain text, probably with escaped characters, parsed from an ANTLR parse
 * tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class PiParsedPlainText implements TemplateBodyPiece {
    /**
     * ANTLR parse tree of some plain text.
     */
    private final transient TemplateParser.RawTextContext ctx;

    /**
     * Ctor.
     * @param context ANTLR parse tree of some plain text.
     */
    public PiParsedPlainText(
        final TemplateParser.RawTextContext context
    ) {
        this.ctx = context;
    }

    @Override
    public String fillUp(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return new PiPlainText(this.unescapedPlainText())
            .fillUp(arguments, vocabulary);
    }

    /**
     * Transforms text with escaped characters to the actual text that is
     * implied by escaping.
     * @return Plain text from the ANTLR parse tree, but without the backslash
     *  escaping.
     */
    private String unescapedPlainText() {
        return this.ctx.getText()
            .replace("\\[", "[")
            .replace("\\\\", "\\");
    }
}
