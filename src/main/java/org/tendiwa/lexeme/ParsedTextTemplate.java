package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;

/**
 * {@link TextTemplate} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
class ParsedTextTemplate implements TextTemplate {
    private final Grammar grammar;
    private final TextBundleParser.TextContext ctx;

    /**
     * @param grammar Grammar of the language of this text
     * @param ctx ANTLR parse tree of a text template
     */
    ParsedTextTemplate(Grammar grammar, TextBundleParser.TextContext ctx) {
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String fillUp(ImmutableList<Lexeme> lexemes) {
        return new ParseTreeListener(
            new ActualArguments(
                new ParsedDeclaredArguments(this.ctx.arguments()),
                lexemes
            )
        ).filledUpText();
    }

    /**
     * Walks over ANTLR parse tree, creates proper objects from its plaintext
     * findings and passes what it created to {@link BodyWalker}
     */
    final class ParseTreeListener extends TextBundleParserBaseListener {

        private StringBuilder builder;
        private final ActualArguments arguments;

        ParseTreeListener(ActualArguments arguments) {
            this.arguments = arguments;
        }

        @Override
        public final void enterPlaceholder(
            TextBundleParser.PlaceholderContext ctx
        ) {
            this.builder.append(
                new ParsedTwoPartVariableConceptPlaceholder(
                    ParsedTextTemplate.this.grammar,
                    ctx
                ).fillUp(this.arguments)
            );
        }

        @Override
        public final void enterRaw_text(TextBundleParser.Raw_textContext ctx) {
            this.builder.append(ctx.getText());
        }

        @Override
        public final void enterBase_form_placeholder(
            TextBundleParser.Base_form_placeholderContext ctx
        ) {
            this.builder.append(
                new ParsedSinglePartPlaceholder(ctx).fillUp(this.arguments)
            );
        }

        /**
         * @return Text collected by this listener after walking the parse tree.
         */
        private String filledUpText() {
            this.builder = new StringBuilder();
            ParseTreeWalker.DEFAULT.walk(
                this,
                ParsedTextTemplate.this.ctx
            );
            return this.builder.toString();
        }
    }
}
