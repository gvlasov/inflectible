package org.tendiwa.lexeme;

import org.tendiwa.lexeme.antlr.TextBundleParser;

/**
 * MarkedUpText parsed from a parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
class BasicTextTemplate implements TextTemplate {
    private final TextBundleParser.TextContext ctx;

    /**
     * @param ctx Parse subtree containing markup.
     */
    BasicTextTemplate(
        TextBundleParser.TextContext ctx
    ) {
        this.ctx = ctx;
    }

    @Override
    public final String id() {
        return this.ctx.text_id().getText();
    }

    @Override
    public DeclaredArguments declaredArguments() {
        return new ParsedDeclaredArguments(this.ctx);
    }

    @Override
    public final MarkedUpTextBody body() {
        return new ParsedMarkedUpTextBody(this.ctx.template());
    }
}
