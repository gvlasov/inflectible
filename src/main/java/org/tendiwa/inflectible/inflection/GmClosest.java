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
import java.util.Set;
import org.tendiwa.inflectible.GmWithSimilarity;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Grammeme;
import org.tenidwa.collections.utils.Measurement;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * Grammatical meaning A from a set of grammatical meanings {M1, M2 ... Mn},
 * and A is the closest to some grammatical meaning B from a set of grammatical
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class GmClosest implements GrammaticalMeaning {
    /**
     * Possible grammatical meanings.
     */
    private final Set<? extends GrammaticalMeaning> possible;

    /**
     * Target grammatical meaning.
     */
    private final GrammaticalMeaning target;

    /**
     * Ctor.
     * @param meanings Possible grammatical meanings
     * @param target Target grammatical meanings
     */
    GmClosest(
        final Set<? extends GrammaticalMeaning> meanings,
        final GrammaticalMeaning target
    ) {
        this.possible = meanings;
        this.target = target;
    }

    /**
     * Finds the grammatical meaning that is the closest to the target
     * grammatical meaning.
     * @return Grammatical meaning that is the closest to the target grammatical
     *  meaning.
     */
    private GrammaticalMeaning closest() {
        if (this.possible.isEmpty()) {
            throw new IllegalStateException(
                "The set of possible grammatical meanings is empty"
            );
        }
        return this.possible
            .stream()
            .map(GmWithSimilarity::new)
            .max(
                new Measurement<>(
                    Rethrowing.rethrowFunction(
                        (suppletive) -> suppletive.similarity(this.target)
                    )
                )
            )
            .get();
    }

    @Override
    public ImmutableSet<Grammeme> grammemes() throws Exception {
        return this.closest().grammemes();
    }
}
