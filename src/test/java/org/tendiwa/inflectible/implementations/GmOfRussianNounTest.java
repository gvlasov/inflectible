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
package org.tendiwa.inflectible.implementations;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link GmOfRussianNoun}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class GmOfRussianNounTest {
    /**
     * {@link GmOfRussianNoun} can be checked for equality against instances
     * of its class.
     * @throws Exception If fails
     */
    @Test
    public void obeysEqualsContract() throws Exception {
        EqualsVerifier.forClass(GmOfRussianNoun.class)
            .suppress(Warning.TRANSIENT_FIELDS)
            .verify();
    }

    /**
     * {@link GmOfRussianNoun} can returns the two grammemes it was
     * initialized with.
     * @throws Exception If fails
     */
    @Test
    public void containsGrammemes() throws Exception {
        MatcherAssert.assertThat(
            new GmOfRussianNoun(
                RussianGrammeme.Ед,
                RussianGrammeme.И
            )
                .grammemes(),
            Matchers.contains(RussianGrammeme.Ед, RussianGrammeme.И)
        );
    }
}
