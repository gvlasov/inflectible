package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tendiwa.inflectible.antlr.TemplateBundleParserBaseListener;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link TextTemplate} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
class ParsedTextTemplate implements TextTemplate {
     /**
     * Grammar of the language of this template.
     */
    private final Grammar grammar;

    /**
     * ANTLR parse tree of a template.
     */
    private final TemplateBundleParser.TemplateContext ctx;

    /**
     * Ctor.
     * @param grammar Grammar of the language of this text
     * @param ctx ANTLR parse tree of a text template
     */
    ParsedTextTemplate(Grammar grammar, TemplateBundleParser.TemplateContext ctx) {
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String fillUp(
        ImmutableList<Lexeme> lexemes,
        ImmutableMap<String, Lexeme> vocabulary
    ) {
        return this.delegate().fillUp(lexemes, vocabulary);
    }

    /**
     * Obtains the names of the arguments of this text template.
     * @return Names of arguments.
     */
    private ImmutableList<String> argumentNames() {
        return this.ctx.declaredArguments().ID().stream()
            .map(TerminalNode::getText)
            .collect(Collectors.toImmutableList());
    }


    /**
     * Creates a template from the {@link ParsedLexeme#ctx}
     * @return Template from markup
     */
    private TextTemplate delegate() {
        // TODO: To be refactored in #47
        return new ParseTreeListener(this.argumentNames()).filledUpText();
    }

    /**
     * Walks an ANTLR parse tree and constructs a template.
     */
    private final class ParseTreeListener
        extends TemplateBundleParserBaseListener {

        /**
         * Template builder.
         */
        private TextTemplateBuilder builder;

        /**
         * Argument names in the order as they appear in markup.
         */
        private ImmutableList<String> arguments;

        /**
         * Ctor.
         * @param names Argument names in the order as they appear in markup
         */
        ParseTreeListener(ImmutableList<String> names) {
            this.arguments = names;
        }

        @Override
        public final void enterTwoPartPlaceholder(
            TemplateBundleParser.TwoPartPlaceholderContext ctx
        ) {
            this.builder.addPlaceholder(
                new ParsedTwoPartVariableConceptPlaceholder(
                    ParsedTextTemplate.this.grammar,
                    ctx
                )
            );
        }

        @Override
        public final void enterRawText(TemplateBundleParser.RawTextContext ctx) {
            this.builder.addText(ctx.getText());
        }

        @Override
        public final void enterSinglePartPlaceholder(
            TemplateBundleParser.SinglePartPlaceholderContext ctx
        ) {
            this.builder.addPlaceholder(new ParsedSinglePartPlaceholder(ctx));
        }

        /**
         * Walk the ANTLR parse tree and construct a {@link TextTemplate} for
         * it.
         * @return Template.
         */
        private TextTemplate filledUpText() {
            this.builder = new TextTemplateBuilder(this.arguments);
            ParseTreeWalker.DEFAULT.walk(this, ParsedTextTemplate.this.ctx);
            return this.builder.build();
        }
    }
}
