package org.tendiwa.lexeme;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;

/**
 * MarkedUpTextBody coming from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedMarkedUpTextBody implements MarkedUpTextBody {
    private final TextBundleParser.TemplateContext ctx;

    /**
     * ANTLR parse tree for a body of a marked up text.
     * @param ctx Body context.
     */
    ParsedMarkedUpTextBody(TextBundleParser.TemplateContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public String walk(BodyWalker walker) {
        final ParseTreeListener listener = new ParseTreeListener(walker);
        ParseTreeWalker.DEFAULT.walk(listener, this.ctx);
        return listener.filledUpText();
    }

    /**
     * Walks over ANTLR parse tree, creates proper objects from its plaintext
     * findings and passes what it created to {@link BodyWalker}
     */
    private static final class ParseTreeListener
        extends TextBundleParserBaseListener  {

        private final StringBuilder builder;

        private final BodyWalker walker;

        ParseTreeListener(BodyWalker walker) {
            this.walker = walker;
            this.builder = new StringBuilder();
        }

        @Override
        public final void enterPlaceholder(
            TextBundleParser.PlaceholderContext ctx
        ) {
            this.builder.append(
                this.walker.enterPlaceholder(
                    new TwoPartPlaceholderMarkup(ctx)
                )
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
                this.walker.enterPlaceholder(
                    new IdOnlyPlaceholderMarkup(ctx)
                )
            );
        }

        /**
         * @return Text collected by this listener after walking the parse tree.
         */
        private String filledUpText() {
            return this.builder.toString();
        }
    }
}
