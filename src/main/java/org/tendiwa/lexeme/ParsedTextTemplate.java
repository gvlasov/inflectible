package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link TextTemplate} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
class ParsedTextTemplate implements TextTemplate {
    private final Grammar grammar;
    private final TextBundleParser.TextTemplateContext ctx;

    /**
     * @param grammar Grammar of the language of this text
     * @param ctx ANTLR parse tree of a text template
     */
    ParsedTextTemplate(Grammar grammar, TextBundleParser.TextTemplateContext ctx) {
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

    private ImmutableList<String> argumentNames() {
        return this.ctx.declaredArguments().ID().stream()
            .map(TerminalNode::getText)
            .collect(Collectors.toImmutableList());
    }

    // TODO: To be refactored in #47
    private TextTemplate delegate() {
        return new ParseTreeListener(this.argumentNames()).filledUpText();
    }

    /**
     * Walks over an ANTLR parse tree, creates proper objects from its plaintext
     * findings and passes what it created to {@link BodyWalker}
     */
    private final class ParseTreeListener
        extends TextBundleParserBaseListener {

        private TextTemplateBuilder builder;
        private ImmutableList<String> argumentNames;

        ParseTreeListener(ImmutableList<String> argumentNames) {
            this.argumentNames = argumentNames;
        }

        @Override
        public final void enterTwoPartPlaceholder(
            TextBundleParser.TwoPartPlaceholderContext ctx
        ) {
            this.builder.addPlaceholder(
                new ParsedTwoPartVariableConceptPlaceholder(
                    ParsedTextTemplate.this.grammar,
                    ctx
                )
            );
        }

        @Override
        public final void enterRawText(TextBundleParser.RawTextContext ctx) {
            this.builder.addText(ctx.getText());
        }

        @Override
        public final void enterSinglePartPlaceholder(
            TextBundleParser.SinglePartPlaceholderContext ctx
        ) {
            this.builder.addPlaceholder(new ParsedSinglePartPlaceholder(ctx));
        }

        /**
         * @return Walk the ANTLR parse tree and construct a
         * {@link TextTemplate} for it.
         */
        private TextTemplate filledUpText() {
            this.builder = new TextTemplateBuilder(this.argumentNames);
            ParseTreeWalker.DEFAULT.walk(this, ParsedTextTemplate.this.ctx);
            return this.builder.build();
        }
    }
}
