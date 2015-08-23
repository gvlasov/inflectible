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

import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tendiwa.inflectible.antlr.TemplateBundleParserBaseListener;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link Template} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1.0
 */
final class ParsedTemplate implements Template {
     /**
     * Grammar of the language of this template.
     */
    private final transient Grammar grammar;

    /**
     * ANTLR parse tree of a template.
     */
    private final transient TemplateBundleParser.TemplateContext ctx;

    /**
     * Ctor.
     * @param grammemes Grammar of the language of this text
     * @param context ANTLR parse tree of a text template
     */
    ParsedTemplate(
        final Grammar grammemes,
        final TemplateBundleParser.TemplateContext context
    ) {
        this.grammar = grammemes;
        this.ctx = context;
    }

    @Override
    public String fillUp(
        final ImmutableList<Lexeme> arguments,
        final Vocabulary vocabulary
    ) throws Exception {
        return this.delegate().fillUp(arguments, vocabulary);
    }

    // To be refactored in #47
    /**
     * Creates a template from the {@link ParsedLexeme#ctx}.
     * @return Template from markup
     */
    private Template delegate() {
        return new TemplateBuilder(this.argumentNames()).template();
    }

    /**
     * Obtains the names of the arguments of this text template.
     * @return Names of arguments.
     */
    private ImmutableList<ArgumentName> argumentNames() {
        return this.ctx
            .declaredArguments()
            .ID()
            .stream()
            .map(TerminalNode::getText)
            .map(AnBasic::new)
            .collect(Collectors.toImmutableList());
    }

    /**
     * Walks an ANTLR parse tree and constructs a template.
     */
    private final class TemplateBuilder
        extends TemplateBundleParserBaseListener {

        /**
         * Template builder.
         */
        private transient ImmutableList.Builder<TemplateBodyPiece> pieces;

        /**
         * Argument names in the order as they appear in markup.
         */
        private final transient ImmutableList<ArgumentName> arguments;

        /**
         * Ctor.
         * @param names Argument names in the order as they appear in markup
         */
        TemplateBuilder(final ImmutableList<ArgumentName> names) {
            super();
            this.arguments = names;
        }

        @Override
        public void enterTwoPartPlaceholder(
            final TemplateBundleParser.TwoPartPlaceholderContext context
        ) {
            this.pieces.add(
                new ParsedTwoPartVariableConceptPlaceholder(
                    ParsedTemplate.this.grammar,
                    context
                )
            );
        }

        @Override
        public void enterRawText(
            final TemplateBundleParser.RawTextContext context
        ) {
            this.pieces.add(new PiPlainText(context.getText()));
        }

        @Override
        public void enterSinglePartPlaceholder(
            final TemplateBundleParser.SinglePartPlaceholderContext context
        ) {
            this.pieces.add(
                new ParsedSinglePartPlaceholder(context)
            );
        }

        /**
         * Walk the ANTLR parse tree and construct a {@link Template} for
         * it.
         * @return Template.
         */
        private Template template() {
            this.pieces = ImmutableList.builder();
            ParseTreeWalker.DEFAULT.walk(this, ParsedTemplate.this.ctx);
            return new BasicTemplate(
                this.arguments,
                this.pieces.build()
            );
        }
    }
}
