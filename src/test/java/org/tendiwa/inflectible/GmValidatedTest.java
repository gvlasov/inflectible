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

import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.inflectible.implementations.RussianGrammeme;

/**
 * Unit tests for {@link GmValidated}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class GmValidatedTest {
    /**
     * {@link GmValidated} can throw an exception if it contains multiple
     * grammemes from the same {@link GrammaticalCategory}.
     * @throws Exception If fails
     */
    @Test(expected = IllegalStateException.class)
    public void failsIfHasGrammemesFromSameGrammaticalCategory()
        throws Exception {
        new GmValidated(
            new AnyPartOfSpeech(),
            ImmutableSet.of(RussianGrammeme.I, RussianGrammeme.II)
        )
            .grammemes();
    }

    /**
     * {@link GmValidated} can throw an exception if it has a grammeme from a
     * grammatical category that is not used in its part of speech.
     * @throws Exception If fails
     */
    @Test(expected = IllegalStateException.class)
    public void failsIfHasGrammemeFromWrongGrammaticalCategory()
        throws Exception {
        final PartOfSpeech part = Mockito.mock(PartOfSpeech.class);
        final Grammeme one = Mockito.mock(Grammeme.class);
        final GrammaticalCategory category = Mockito.mock(
            GrammaticalCategory.class
        );
        Mockito.when(part.usesCategory(category)).thenReturn(false);
        Mockito.when(one.category()).thenReturn(category);
        Mockito.when(category.containsGrammeme(one)).thenReturn(true);
        new GmValidated(part, ImmutableSet.of(one)).grammemes();
    }
}
