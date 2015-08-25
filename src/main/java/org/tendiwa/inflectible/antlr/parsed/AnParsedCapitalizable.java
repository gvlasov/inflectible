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

import java.util.Locale;
import org.tendiwa.inflectible.ArgumentName;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * Argument name from ANTLR parse tree about which it is not known until
 * runtime if its content is capitalized or not.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class AnParsedCapitalizable implements ArgumentName {
    /**
     * ANTLR parse tree with a probably capitalized argument name.
     */
    private final transient
        TemplateBundleParser.CapitalizableArgumentNameContext ctx;

    /**
     * Ctor.
     * @param context ANLTR parse tree with a probably capitalized argument name
     */
    AnParsedCapitalizable(
        final TemplateBundleParser.CapitalizableArgumentNameContext context
    ) {
        this.ctx = context;
    }
    @Override
    public String string() throws Exception {
        final String answer;
        if (this.ctx.argumentName() == null) {
            answer = this.ctx
                .capitalizedArgumentName()
                .CAPITALIZED_ARGUMENT_NAME()
                .getText()
                .toLowerCase(Locale.getDefault());
        } else {
            answer = this.ctx.argumentName().ARGUMENT_NAME().getText();
        }
        return answer;
    }
}
