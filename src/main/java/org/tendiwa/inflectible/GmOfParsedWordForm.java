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

import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tendiwa.inflectible.antlr.LexemeBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link GrammaticalMeaning} from an ANTLR parse tree of a {@link WordForm}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
final class GmOfParsedWordForm implements GrammaticalMeaning {
    /**
     * Grammar of a language.
     */
    private final Grammar grammar;

    /**
     * ANTLR parse tree of grammemes in a word form.
     */
    private final transient LexemeBundleParser.GrammemesContext ctx;

    /**
     * Ctor.
     * @param gram Grammar of a language
     * @param context ANTLR parse tree of grammemes in a word form.
     */
    GmOfParsedWordForm(
        final Grammar gram,
        final LexemeBundleParser.GrammemesContext context
    ) {
        this.grammar = gram;
        this.ctx = context;
    }

    @Override
    public ImmutableSet<Grammeme> grammemes() {
        return this.ctx
            .GRAMMEME()
            .stream()
            .map(ParseTree::getText)
            .map(this.grammar::grammemeByName)
            .collect(Collectors.toImmutableSet());
    }
}
