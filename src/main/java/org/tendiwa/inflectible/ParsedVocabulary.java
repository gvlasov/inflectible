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

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * A vocabulary of lexemes that identifies each lexeme by a unique string.
 * Lexeme identifiers are uppercase words, e.g. RUN or TREE.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedVocabulary extends ForwardingMap<String, Lexeme> {
    /**
     * Input streams with lexemes' markup.
     */
    private final transient List<InputStream> input;

    /**
     * Grammar of the language of the lexemes.
     */
    private final transient Grammar grammar;

    // To be refactored in #47
    /**
     * Found lexemes.
     */
    private final transient ImmutableMap<String, Lexeme> lexemes;

    /**
     * Ctor.
     * @param grammemes Grammar of the language of the lexemes
     * @param sources Input streams with lexemes' markup
     */
    public ParsedVocabulary(
        final Grammar grammemes,
        final List<InputStream> sources
    ) {
        super();
        this.input = sources;
        this.grammar = grammemes;
        this.lexemes = this.constructLexemes();
    }

    // @checkstyle ProtectedMethodInFinalClassCheck (3 lines)
    @Override
    protected Map<String, Lexeme> delegate() {
        return this.lexemes;
    }

    /**
     * Constructs lexemes from markup.
     * @return Lexemes constructed from the markup in the input stream
     */
    private ImmutableMap<String, Lexeme> constructLexemes() {
        return ImmutableMap.copyOf(
            this.input
                .stream()
                .map(BasicLexemeBundleParser::new)
                .flatMap(parser -> parser.lexemes().lexeme().stream())
                .collect(
                    java.util.stream.Collectors.toMap(
                        ctx -> ctx.LEXEME_ID().getText(),
                        ctx -> new ParsedLexeme(this.grammar, ctx)
                    )
                )
        );
    }

}
