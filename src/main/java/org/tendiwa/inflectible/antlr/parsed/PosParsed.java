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

import java.util.Set;
import org.tendiwa.inflectible.Grammar;
import org.tendiwa.inflectible.GrammaticalCategory;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.PartOfSpeech;
import org.tendiwa.inflectible.Spelling;
import org.tendiwa.inflectible.antlr.LexemeParser;

/**
 * Part of speech parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
final class PosParsed implements PartOfSpeech {
    /**
     * Grammar of a natural language.
     */
    private final transient Grammar grammar;

    /**
     * ANTLR parse tree of a part of speech.
     */
    private final transient LexemeParser.PartOfSpeechContext ctx;

    /**
     * Ctor.
     * @param gram Grammar of a natural language
     * @param context ANTLR parse tree of a part of speech
     */
    PosParsed(
        final Grammar gram,
        final LexemeParser.PartOfSpeechContext context
    ) {
        this.grammar = gram;
        this.ctx = context;
    }

    @Override
    public boolean usesCategory(
        final GrammaticalCategory category
    ) throws Exception {
        return this.delegate().usesCategory(category);
    }

    @Override
    public Lexeme lexeme(
        final Spelling headword, final GrammaticalMeaning persistent
    ) throws Exception {
        return this.delegate().lexeme(headword, persistent);
    }

    @Override
    public Set<GrammaticalMeaning> meaningVariations() throws Exception {
        return this.delegate().meaningVariations();
    }

    @Override
    public String toString() {
        return this.ctx.PART_OF_SPEECH().getText();
    }

    // To be refactored in #47
    /**
     * Creates a part of speech.
     * @return Part of speech
     * @throws Exception If could not crete a part of speech
     */
    private PartOfSpeech delegate() throws Exception {
        return this.grammar.partOfSpeechByName(
            this.ctx.PART_OF_SPEECH().getText()
        );
    }
}
