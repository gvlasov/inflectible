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
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Spelling;

/**
 * Fake inflection rules for those parts of speech that are not implemented yet.
 * <p>This class will disappear before 1.0.0, that's why I didn't bother with
 * proper checked exceptions.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class NotImplementedInflection implements PartOfSpeechInflection {
    /**
     * Error message.
     */
    private static final String ERROR =
        "Inflection not implemented for this part of speech";

    @Override
    public Stem createStem(
        final GrammaticalMeaning persistent,
        final Spelling headword
    ) {
        throw new UnsupportedOperationException(NotImplementedInflection.ERROR);
    }

    @Override
    public ImmutableSet<GrammaticalMeaning> allPossibleInflectedMeanings() {
        throw new UnsupportedOperationException(NotImplementedInflection.ERROR);
    }
}
