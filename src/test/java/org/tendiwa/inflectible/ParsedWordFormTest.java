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
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

/**
 * Unit tests for {@link ParsedWordForm}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedWordFormTest {
    /**
     * ParsedWordForm can obtain its spelling from an ANTLR parse tree.
     * @throws Exception If fails
     */
    @Test
    public void hasSpelling() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormBears().spelling(),
            CoreMatchers.equalTo("bears")
        );
    }

    /**
     * ParsedWordForm can tell how similar its grammatical meaning is to some
     * other grammatical meaning.
     * @throws Exception If fails
     */
    @Test
    public void computesSimilarity() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormBears()
                .similarity(ImmutableSet.of(English.Grammemes.Plur)),
            CoreMatchers.equalTo(1)
        );
    }

    /**
     * Creates a plural word form <i>bears</i>.
     * @return Plural word form <i>bears</i>
     */
    private ParsedWordForm wordFormBears() {
        return new ParsedWordForm(
            new English().grammar(),
            new BasicLexemeBundleParser(
                "BEAR {",
                "  bear",
                "  bears [Plur]",
                "}"
            )
                .lexemes()
                .lexeme(0)
                .wordForms()
                .wordForm(1)
        );
    }
}
