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
import org.mockito.Mockito;
import org.tendiwa.inflectible.implementations.English;

/**
 * Unit tests for {@link ParsedVocabularyPlaceholder}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class ParsedVocabularyPlaceholderTest {
    /**
     * {@link ParsedVocabularyPlaceholder} can get its lexeme from vocabulary.
     * @throws Exception If fails
     */
    @Test
    public void getsLexemeFromVocabulary() throws Exception {
        final String dude = "dude";
        final Vocabulary vocabulary = Mockito.mock(Vocabulary.class);
        Mockito.when(vocabulary.lexeme(Mockito.anyObject()))
            .thenReturn(new SingleFormLexeme(dude));
        final ActualArguments arguments = Mockito.mock(ActualArguments.class);
        Mockito.when(arguments.byName(Mockito.anyObject()))
            .thenReturn(new SingleFormLexeme("man"));
        MatcherAssert.assertThat(
            new ParsedVocabularyPlaceholder(
                new English().grammar(),
                new BasicTemplateBundleParser(
                    "text.id(object) {",
                    "  This [lexeme DUDE]<;object> greets you.",
                    "}"
                )
                    .templates()
                    .template(0)
                    .templateBody()
                    .line(0)
                    .piece(1)
                    .vocabularyPlaceholder()
            )
                .pickLexeme(arguments, vocabulary)
                .defaultSpelling()
                .string(),
            CoreMatchers.equalTo(dude)
        );
    }

    /**
     * {@link ParsedVocabularyPlaceholder} can capitalize its content.
     * @throws Exception If fails
     */
    @Test
    public void capitalizes() throws Exception {
        final String cat = "cat";
        final Vocabulary vocabulary = Mockito.mock(Vocabulary.class);
        Mockito.when(vocabulary.lexeme(Mockito.anyObject()))
            .thenReturn(new SingleFormLexeme(cat));
        final ActualArguments arguments = Mockito.mock(ActualArguments.class);
        Mockito.when(arguments.byName(Mockito.anyObject()))
            .thenReturn(new SingleFormLexeme("woman"));
        MatcherAssert.assertThat(
            new ParsedVocabularyPlaceholder(
                new English().grammar(),
                new BasicTemplateBundleParser(
                    "text.id(object) { ",
                    "  [Lexeme DUDE]<;object> greets you.",
                    "} "
                )
                    .templates()
                    .template(0)
                    .templateBody()
                    .line(0)
                    .piece(0)
                    .vocabularyPlaceholder()
            )
                .capitalize(new BasicSpelling(cat))
                .string(),
            CoreMatchers.equalTo("Cat")
        );
    }
}
