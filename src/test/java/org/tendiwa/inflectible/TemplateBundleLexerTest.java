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

import com.google.common.base.Joiner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.antlr.TemplateBundleLexer;

/**
 * Unit tests for {@link TemplateBundleLexer}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class TemplateBundleLexerTest {
    /**
     * TemplateBundleLexer can split a correct piece of markup into tokens.
     * @throws Exception If fails
     */
    @Test
    public void splitsIntoTokens() throws Exception {
        final String text = Joiner.on('\n').join(
            "log.get_damage(attacker, action, aim) {",
            "    // Медведь кусает человека, человек получает пизды",
            "    [Attacker] [action][III] [aim][Р], [aim] получает пизды",
            "}"
        );
        MatcherAssert.assertThat(
            new TemplateBundleLexer(
                new ANTLRInputStream(
                    IOUtils.toInputStream(text)
                )
            )
                .nextToken()
                .getText(),
            CoreMatchers.equalTo("log")
        );
    }
}
