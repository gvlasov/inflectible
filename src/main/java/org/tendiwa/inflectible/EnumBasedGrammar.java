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

import java.lang.reflect.InvocationTargetException;

/**
 * {@link Grammar} that has its grammemes stored in an enum. Storing
 * grammemes in an enum, as opposed to storing them in a Map<String, Grammeme>,
 * is important because that way IDE will be able to use completion and
 * compile-time checking for grammemes.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class EnumBasedGrammar implements Grammar {

    /**
     * Enum with grammemes.
     */
    private final transient Class<? extends Grammeme> cls;

    /**
     * Ctor.
     * @param grammemes Enum with grammemes.
     */
    public EnumBasedGrammar(final Class<? extends Grammeme> grammemes) {
        if (!grammemes.isEnum()) {
            throw new IllegalArgumentException(
                String.format(
                    "%s: Grammemes class must be an enum",
                    grammemes.getCanonicalName()
                )
            );
        }
        this.cls = grammemes;
    }

    @Override
    public Grammeme grammemeByName(final String name) {
        try {
            return (Grammeme) this.cls
                .getMethod("valueOf", String.class)
                .invoke(null, name);
        } catch (
            final NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException ex
        ) {
            throw new AssertionError(
                "Reflective static method call failed. Report this bug please",
                ex
            );
        }
    }
}
