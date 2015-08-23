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
package org.tendiwa.inflectible.antlr.parsed;

import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.tendiwa.inflectible.implementations.English;

/**
 * Unit tests for {@link ParsedLexeme}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
public final class ParsedLexemeTest {
    /**
     * Name of a resource with lexemes.
     */
    public static final String LEXEMES_RESOURCE = "characters.en_US.words";

    /**
     * ParsedLexeme can return its base form.
     * @throws Exception If fails
     */
    @Test
    public void hasBaseForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle(ParsedLexemeTest.LEXEMES_RESOURCE, 0)
                .defaultSpelling()
                .string(),
            CoreMatchers.equalTo("bear")
        );
    }

    /**
     * ParsedLexeme can be created from an ANTLR parse tree with 0 persistent
     * grammemes in it.
     * @throws Exception If fails
     */
    @Test
    public void worksWithZeroPersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle(ParsedLexemeTest.LEXEMES_RESOURCE, 0)
                .persistentGrammemes()
                .grammemes(),
            CoreMatchers.equalTo(ImmutableSet.of())
        );
    }

    /**
     * ParsedLexeme can return the right word form based on grammemes
     * provided to it.
     * @throws Exception If fails
     */
    @Test
    public void canAssumeCorrectForm() throws Exception {
        MatcherAssert.assertThat(
            this
                .wordOfBundle(ParsedLexemeTest.LEXEMES_RESOURCE, 0)
                .wordForm(() -> ImmutableSet.of(English.Grammemes.Plur))
                .string(),
            CoreMatchers.equalTo("bears")
        );
    }

    /**
     * ParsedLexeme can be used multiple times to return the same thing.
     * @throws Exception If fails
     */
    @Test
    public void canBeUsedMultipleTimes() throws Exception {
        final ParsedLexeme lexeme = this.wordOfBundle(
            ParsedLexemeTest.LEXEMES_RESOURCE,
            1
        );
        IntStream.range(0, 2).forEach(
            i -> MatcherAssert.assertThat(
                lexeme.defaultSpelling().string(),
                CoreMatchers.equalTo("human")
            )
        );
    }

    /**
     * ParsedLexeme can obtain persistent grammemes from an ANTLR parse tree.
     * @throws Exception If fails
     */
    @Test
    public void canHavePersistentGrammemes() throws Exception {
        MatcherAssert.assertThat(
            this.wordOfBundle(ParsedLexemeTest.LEXEMES_RESOURCE, 2)
                .persistentGrammemes()
                .grammemes()
                .size(),
            CoreMatchers.equalTo(1)
        );
    }

    /**
     * Picks {@code index}'th form of a lexeme from a resource with lexemes'
     * markup.
     * @param resource Name of a resource with lexemes' markup
     * @param index Index of a lexeme in the markup
     * @return Lexeme on {@code index}'th place in the markup
     * @throws IOException If can't read the resource
     */
    private ParsedLexeme wordOfBundle(
        final String resource,
        final int index
    ) throws IOException {
        return new ParsedLexeme(
            new English().grammar(),
            new BasicLexemeBundleParser(
                ParsedLexemeTest.class.getResourceAsStream(resource)
            )
                .lexemes()
                .lexeme(index)
        );
    }
}
