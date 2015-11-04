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
package org.tendiwa.inflectible.inflection;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.EnglishGrammeme;

/**
 * Unit tests for {@link GmClosest}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class GmClosestTest {
    /**
     * {@link GmClosest} can become the closest grammatical meaning from the
     * source set.
     * @throws Exception If fails
     */
    @Test
    public void becomesClosestMeaning() throws Exception {
        MatcherAssert.assertThat(
            new GmClosest(
                ImmutableSet.of(
                    () -> ImmutableSet.of(
                        EnglishGrammeme.Plur,
                        EnglishGrammeme.Ger
                    ),
                    () -> ImmutableSet.of(
                        EnglishGrammeme.Sing,
                        EnglishGrammeme.I
                    )
                ),
                () -> ImmutableSet.of(
                    EnglishGrammeme.Plur,
                    EnglishGrammeme.Ger,
                    EnglishGrammeme.I
                )
            ).grammemes(),
            Matchers.contains(EnglishGrammeme.Plur, EnglishGrammeme.Ger)
        );
    }

    /**
     * {@link GmClosest} can consider the meaning that has more common
     * grammemes with the target meaning to be more similar to the target
     * meaning.
     * @throws Exception If fails
     */
    @Test
    public void considersMeaningWithMoreCommonGrammemesMoreSimilar() throws
        Exception {
        MatcherAssert.assertThat(
            new GmClosest(
                ImmutableSet.of(
                    ImmutableSet::of,
                    () -> ImmutableSet.of(
                        EnglishGrammeme.Plur,
                        EnglishGrammeme.Ger
                    )
                ),
                () -> ImmutableSet.of(
                    EnglishGrammeme.Plur,
                    EnglishGrammeme.Ger,
                    EnglishGrammeme.I
                )
            ).grammemes(),
            Matchers.contains(EnglishGrammeme.Plur, EnglishGrammeme.Ger)
        );
    }

    /**
     * {@link GmClosest} can not work with an empty source set.
     * @throws Exception If fails
     */
    @Test(expected = IllegalStateException.class)
    public void doesntWorkWithEmptySourceSet() throws Exception {
        new GmClosest(
            ImmutableSet.of(),
            () -> ImmutableSet.of(EnglishGrammeme.Plur)
        ).grammemes();
    }
}
