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

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * {@link Vocabulary} defined by a map from lexeme identifiers to lexemes.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class BasicVocabulary implements Vocabulary {
    /**
     * Lexemes in this vocabulary.
     */
    private final transient ImmutableMap<Conception, Lexeme> lexemes;

    /**
     * Ctor.
     * @param map Map from lexeme identifiers to lexemes
     */
    BasicVocabulary(final ImmutableMap<Conception, Lexeme> map) {
        this.lexemes = map;
    }

    @Override
    public Lexeme lexeme(final Conception name) throws Exception {
        final Lexeme lexeme = this.stringMap().get(name.identifier());
        if (lexeme == null) {
            throw new MissingLexemeException(name.identifier());
        }
        return lexeme;
    }

    @Override
    public boolean hasLexeme(final Conception name) throws Exception {
        return this.stringMap().containsKey(name.identifier());
    }

    // To be refactored in #47
    /**
     * Cretes map from string conception names to their lexemes.
     * @return Map from string conception names to their lexemes
     * @throws Exception If could not create the map
     */
    private Map<String, Lexeme> stringMap() throws Exception {
        return this.lexemes
            .keySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Rethrowing.rethrowFunction(Conception::identifier),
                    this.lexemes::get
                )
            );
    }
}
