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
    public void walk(BodyWalker walker) {
        ParseTreeWalker.DEFAULT.walk(
            new ParseTreeListener(walker),
            this.ctx
        );
    }

    /**
     * Walks over ANTLR parse tree, creates proper objects from its plaintext
     * findings and passes what it created to {@link BodyWalker}
     */
    private static final class ParseTreeListener
        extends TextBundleParserBaseListener  {

        private final BodyWalker walker;

        ParseTreeListener(BodyWalker walker) {
            this.walker = walker;
        }

        @Override
        public final void enterPlaceholder(
            TextBundleParser.PlaceholderContext ctx
        ) {
            this.walker.enterPlaceholder(
                new TwoPartPlaceholder(ctx)
            );
        }

        @Override
        public final void enterRaw_text(TextBundleParser.Raw_textContext ctx) {
            this.walker.enterPlaintext(ctx.getText());
        }

        @Override
        public final void enterBase_form_placeholder(
            TextBundleParser.Base_form_placeholderContext ctx
        ) {
            this.walker.enterPlaceholder(
                new IdOnlyPlaceholder(ctx)
            );
        }
    }
}
