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

import org.tendiwa.inflectible.inflection.NotImplementedInflection;
import org.tendiwa.inflectible.inflection.PartOfSpeechInflection;

/**
 * Part of speech that doesn't have any restrictions on what grammatical
 * categories it may use.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class AnyPartOfSpeech implements PartOfSpeech {
    @Override
    public boolean usesCategory(
        final GrammaticalCategory category
    ) throws Exception {
        return true;
    }

    @Override
    public PartOfSpeechInflection inflection() {
        return new NotImplementedInflection();
    }
}
