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
import java.util.Collections;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

/**
 * Unit tests for {@link ParsedVocabulary}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedVocabularyTest {
    /**
     * ParsedVocabulary can parse multiple input streams to a
     * Map<String, Lexeme>.
     * @throws Exception If fails
     */
    @Test
    public void findsWords() throws Exception {
        final ParsedVocabulary bundle = this.englishVocabulary();
        MatcherAssert.assertThat(
            bundle.size(),
            CoreMatchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            bundle.containsKey("DRAGON"),
            CoreMatchers.equalTo(true)
        );
    }

    /**
     * Creates a small vocabulary for {@link English} language.
     * @return Vocabulary for {@link English}
     */
    private ParsedVocabulary englishVocabulary() {
        return new ParsedVocabulary(
            new English().grammar(),
            Collections.singletonList(
                IOUtils.toInputStream(
                    Joiner.on('\n').join(
                        "DRAGON {",
                        "   dragon  [Sing]",
                        "   dragons [Plur]",
                        "}",
                        "BEE {",
                        "   bee  [Sing]",
                        "   bees [Plur]",
                        "} "
                    )
                )
            )
        );
    }
}
