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

/**
 * {@link TemplateName} defined by its string representation.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class TnBasic implements TemplateName {
    /**
     * String value of the template name.
     */
    private final transient String value;

    /**
     * Ctor.
     * @param name String value of the template name
     */
    public TnBasic(final String name) {
        this.value = name;
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
     */
    private void validate() {
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
