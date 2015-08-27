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

import com.google.common.collect.ImmutableList;
import org.tendiwa.inflectible.BasicLexeme;
import org.tendiwa.inflectible.GmEmpty;
import org.tendiwa.inflectible.Grammar;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Lexeme;
import org.tendiwa.inflectible.Spelling;
import org.tendiwa.inflectible.WordForm;
import org.tendiwa.inflectible.antlr.LexemeParser;

/**
 * {@link Lexeme} constructed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
final class ParsedLexeme implements Lexeme {

    /**
     * Grammar of the language of this lexeme.
     */
    private final transient Grammar grammar;

    /**
     * ANTLR parse tree of a lexeme.
     */
    private final transient LexemeParser.LexemeContext ctx;

    /**
     * Ctor.
     * @param grammemes Grammar of the language of this lexeme
     * @param context ANTLR parse tree of a lexeme
     */
    public ParsedLexeme(
        final Grammar grammemes,
        final LexemeParser.LexemeContext context
    ) {
        this.grammar = grammemes;
        this.ctx = context;
    }

    @Override
    public Spelling wordForm(final GrammaticalMeaning grammemes) {
        return this.delegate().wordForm(grammemes);
    }

    @Override
    public GrammaticalMeaning persistentGrammemes() {
        return this.delegate().persistentGrammemes();
    }

    @Override
    public Spelling defaultSpelling() {
        return this.delegate().defaultSpelling();
    }

    /**
     * Creates a lexeme with word forms and persistent grammatical meaning
     * obtained from the {@link ParsedLexeme#ctx}.
     * @return Lexeme from markup.
     */
    private Lexeme delegate() {
        return new BasicLexeme(
            this.grammemes(),
            this.wordForms()
        );
    }

    /**
     * Obtains grammemes from markup.
     * @return Grammemes
     */
    private GrammaticalMeaning grammemes() {
        final GrammaticalMeaning grammemes;
        if (this.ctx.persistentGrammemes() == null) {
            grammemes = new GmEmpty();
        } else {
            grammemes = new GmOfParsedGrammemes(
                this.grammar,
                this.ctx
                    .persistentGrammemes()
                    .grammaticalMeaning()
                    .grammemes()
            );
        }
        return grammemes;
    }

    /**
     * Obtains word forms from markup.
     * @return Word forms.
     */
    private ImmutableList<WordForm> wordForms() {
        final ImmutableList.Builder<WordForm> builder = ImmutableList.builder();
        if (this.ctx.wordForms().dictionaryWordForm() != null) {
            builder.add(
                new WfParsedDictionary(
                    this.ctx.wordForms().dictionaryWordForm()
                )
            );
        }
        this.ctx
            .wordForms()
            .inflectedWordForm()
            .stream()
            .map(context -> new WfParsedInflected(this.grammar, context))
            .forEach(builder::add);
        return builder.build();
    }
}
