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
 * Template body piece that holds place for a string to be inserted in it
 * during filling out a template. This action is called "resolution of a
 * placeholder". To resolve a placeholder, we provide it with
 * {@link ActualArguments} passed to its template and a {@link Vocabulary}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class Placeholder implements TemplateBodyPiece {
    /**
     * How to choose a lexeme.
     */
    private final transient LexicalRule semantic;

    /**
     * How to choose a word form.
     */
    private final transient GrammarRule grammar;

    /**
     * How to choose spelling features.
     */
    private final transient SpellingRule graphic;

    /**
     * Ctor.
     * @param sem Semantic rule for resolution of this placeholder
     * @param gram Grammar rule for resolution of this placeholder
     * @param graph Graphic rule for resolution of this placeholder
     */
    public Placeholder(
        final LexicalRule sem,
        final GrammarRule gram,
        final SpellingRule graph
    ) {
        this.semantic = sem;
        this.grammar = gram;
        this.graphic = graph;
    }

    @Override
    public String fillUp(
        final ActualArguments arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return
            this.graphic
                .adjustSpelling(
                    this.semantic
                        .pickLexeme(arguments, vocabulary)
                        .wordForm(this.grammar.grammaticalMeaning(arguments))
                )
                .string();
    }
}
