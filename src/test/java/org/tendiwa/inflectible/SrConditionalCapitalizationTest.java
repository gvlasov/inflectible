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

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Unit tests for {@link SrConditionalCapitalization}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class SrConditionalCapitalizationTest {
    /**
     * {@link SrConditionalCapitalization} can capitalize its input if the
     * condition in the constructor argument is met.
     * @throws Exception If fails
     */
    @Test
    public void capitalizesIfConditionIsMet() throws Exception {
        MatcherAssert.assertThat(
            new SrConditionalCapitalization(() -> true)
                .adjustSpelling(() -> "dude")
                .string(),
            CoreMatchers.equalTo("Dude")
        );
    }

    /**
     * {@link SrConditionalCapitalization} can leave its input as it is if the
     * condition in the constructor argument is not met.
     * @throws Exception If fails
     */
    @Test
    public void leavesAsIsIfConditionIsNotMet() throws Exception {
        final String rock = "rock";
        MatcherAssert.assertThat(
            new SrConditionalCapitalization(() -> false)
                .adjustSpelling(() -> rock)
                .string(),
            CoreMatchers.equalTo(rock)
        );
    }

    /**
     * {@link SrConditionalCapitalization} can leave its already
     * capitalized input as it is if the condition in the constructor argument
     * is not met.
     * @throws Exception If fails
     */
    @Test
    public void leavesCapitalizedAsIsIfConditionIsNotMet() throws Exception {
        final String bob = "Bob";
        MatcherAssert.assertThat(
            new SrConditionalCapitalization(() -> false)
                .adjustSpelling(() -> bob)
                .string(),
            CoreMatchers.equalTo(bob)
        );
    }

    /**
     * {@link SrConditionalCapitalization} can capitalize the already
     * capitalized result, which leaves it as it is.
     * @throws Exception If fails
     */
    @Test
    public void caiptalizesCaitalized() throws Exception {
        final String mary = "Mary";
        MatcherAssert.assertThat(
            new SrConditionalCapitalization(() -> true)
                .adjustSpelling(() -> mary)
                .string(),
            CoreMatchers.equalTo(mary)
        );
    }
}
