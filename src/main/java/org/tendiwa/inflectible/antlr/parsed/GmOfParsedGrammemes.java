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
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.inflectible.GmValidated;
import org.tendiwa.inflectible.Grammar;
import org.tendiwa.inflectible.GrammaticalMeaning;
import org.tendiwa.inflectible.Grammeme;
import org.tendiwa.inflectible.PartOfSpeech;
import org.tendiwa.inflectible.antlr.LexemeParser;
import org.tendiwa.inflectible.antlr.TemplateParser;
import org.tenidwa.collections.utils.Collectors;
import org.tenidwa.collections.utils.Rethrowing;

/**
 * {@link GrammaticalMeaning} from an ANTLR parse tree of grammemes.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public final class GmOfParsedGrammemes implements GrammaticalMeaning {
    /**
     * Grammar of a natural language.
     */
    private final transient Grammar grammar;

    /**
     * Part of speech.
     */
    private final transient PartOfSpeech part;

    /**
     * Parsed grammeme names.
     */
    private final transient List<TerminalNode> nodes;

    /**
     * Ctor.
     * @param gram Grammar of a natural language
     * @param prt Part of speech
     * @param grammemes ANTLR terminal nodes with grammeme names
     */
    private GmOfParsedGrammemes(
        final Grammar gram,
        final PartOfSpeech prt,
        final List<TerminalNode> grammemes
    ) {
        this.grammar = gram;
        this.part = prt;
        this.nodes = grammemes;
    }

    /**
     * Ctor.
     * @param gram Grammar of a natural language
     * @param prt Part of speech
     * @param context ANTLR parse tree of grammemes
     */
    GmOfParsedGrammemes(
        final Grammar gram,
        final PartOfSpeech prt,
        final TemplateParser.GrammemesContext context
    ) {
        this(gram, prt, context.GRAMMEME());
    }

    /**
     * Ctor.
     * @param gram Grammar of a natural language
     * @param prt Part of speech
     * @param context ANTLR parse tree of grammemes
     */
    GmOfParsedGrammemes(
        final Grammar gram,
        final PartOfSpeech prt,
        final LexemeParser.GrammemesContext context
    ) {
        this(gram, prt, context.GRAMMEME());
    }

    @Override
    public ImmutableSet<Grammeme> grammemes() throws Exception {
        return new GmValidated(
            this.part,
            this.nodes
                .stream()
                .map(TerminalNode::getText)
                .map(
                    Rethrowing.rethrowFunction(
                        this.grammar::grammemeByName
                    )
                )
                .collect(Collectors.toImmutableSet())
        )
            .grammemes();
    }
}
