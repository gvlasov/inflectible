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

import lombok.EqualsAndHashCode;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * Name for a {@link Template}. May consist of only dots or lowrcase letters,
 * e.g. "actions.attack".
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode(of = { "value" })
public final class TemplateName implements ValidatedIdentifier {
    /**
     * String value of the template name.
     */
    private final transient String value;

    /**
     * Ctor.
     * @param name String value of the template name
     */
    public TemplateName(final String name) {
        this.value = name;
    }

    /**
     * Ctor.
     * @param ctx ANTLR parse tree of a template
     */
    public TemplateName(final TemplateBundleParser.TemplateContext ctx) {
        this(ctx.id().getText());
    }

    @Override
    public String string() throws Exception {
        this.validate();
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Validates the template name.
     * @throws Exception If template name is not valid
     */
    private void validate() throws Exception {
        for (final char character : this.value.toCharArray()) {
            if (!this.isValidCharacter(character)) {
                throw new IllegalArgumentException(
                    String.format(
                        "%s is not a valid template name",
                        this.value
                    )
                );
            }
        }
    }

    /**
     * Checks if a character is a valid one for a template's name.
     * @param character Character
     * @return True iff {@code character} can be in a template's name.
     */
    private boolean isValidCharacter(final char character) {
        return character == '.' || Character.isLowerCase(character);
    }
}
