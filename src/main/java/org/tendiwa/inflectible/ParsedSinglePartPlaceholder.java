package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedSinglePartPlaceholder implements Placeholder {
    private final TemplateBundleParser.SinglePartPlaceholderContext ctx;

    ParsedSinglePartPlaceholder(
        TemplateBundleParser.SinglePartPlaceholderContext ctx
    ) {
        this.ctx = ctx;
    }

    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.lexemeSource(),
            this.capitalization(),
            ImmutableSet.of(),
            Agreement.NONE
        );
    }

    private Capitalization capitalization() {
        return Character.isUpperCase(this.name().charAt(0)) ?
            Capitalization.CAPITALZES : Capitalization.IDENTITY;
    }

    private LexemeSource lexemeSource() {
        return new ArgumentsLexemeSource(
            this.name().toLowerCase()
        );
    }

    private String name() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }

    @Override
    public String fillUp(
        ImmutableMap<String, Lexeme> arguments,
        ImmutableMap<String, Lexeme> vocabulary
    ) {
        return this.delegate().fillUp(arguments, vocabulary);
    }
}
