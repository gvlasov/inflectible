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

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Unit tests for {@link LexemeName}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class LexemeNameTest {
    /**
     * {@link LexemeName} can be created from an uppercase string.
     * @throws Exception If fails
     */
    @Test
    public void allowsUppercase() throws Exception {
        final String name = "DUDE";
        MatcherAssert.assertThat(
            new LexemeName(name).string(),
            CoreMatchers.is(name)
        );
    }

    /**
     * {@link LexemeName} can not be created from a string with any
     * non-uppercase letters.
     * @throws Exception If argument name didn't pass validation
     */
    @Test(expected = Exception.class)
    public void disallowsNonUppercase() throws Exception {
        new LexemeName("dude").string();
    }

    /**
     * {@link LexemeName} can be tested for equality against instances of its
     * class.
     * @throws Exception If fails
     */
    @Test
    public void obeysEqualsContract() throws Exception {
        EqualsVerifier.forClass(LexemeName.class)
            .suppress(Warning.TRANSIENT_FIELDS)
            .verify();
    }
}
