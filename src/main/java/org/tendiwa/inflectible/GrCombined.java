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

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.tenidwa.collections.utils.Collectors;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * Grammar rule that combines multiple other rules.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class GrCombined implements GrammarRule {
    /**
     * Grammar rules to combine.
     */
    private final transient List<GrammarRule> rules;

    /**
     * Ctor.
     * @param combined Grammar rules to combine.
     */
    public GrCombined(
        final ImmutableList<GrammarRule> combined
    ) {
        this.rules = combined;
    }

    @Override
    public GrammaticalMeaning grammaticalMeaning(
        final ActualArguments arguments
    ) throws Exception {
        return new GmCombined(
            this.rules.stream()
                .map(
                    Rethrowing.rethrowFunction(
                        rule -> rule.grammaticalMeaning(arguments)
                    )
                )
                .collect(Collectors.toImmutableList())
        );
    }
}
