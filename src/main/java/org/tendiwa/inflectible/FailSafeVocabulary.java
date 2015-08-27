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

/**
 * {@link Vocabulary} that will not throw an exception if it doesn't have a
 * particular lexeme. Instead, it will return a lexeme whose spelling is a
 * localized "Word missing" message.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class FailSafeVocabulary implements Vocabulary {
    /**
     * Decorated vocabulary.
     */
    private final transient Vocabulary wrapped;

    /**
     * A natural language.
     */
    private final transient Language language;

    /**
     * Ctor.
     * @param vocabulary Vocabulary to decorate
     */
    FailSafeVocabulary(final Language lang, final Vocabulary vocabulary) {
        this.wrapped = vocabulary;
        this.language = lang;
    }

    @Override
    public Lexeme lexeme(final Concept concept) throws Exception {
        final Lexeme answer;
        if (this.wrapped.hasLexeme(concept)) {
            answer = this.wrapped.lexeme(concept);
        } else {
            answer = new MissingLexeme(concept, this.language);
        }
        return answer;
    }

    @Override
    public boolean hasLexeme(final Concept concept) throws Exception {
        return this.wrapped.hasLexeme(concept);
    }
}
