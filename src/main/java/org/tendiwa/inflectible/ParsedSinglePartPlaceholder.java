package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;

/**
 * {@link Placeholder} constructed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedSinglePartPlaceholder implements Placeholder {
    /**
     * ANTLR parse tree of a placeholder.
     */
    private final TemplateBundleParser.SinglePartPlaceholderContext ctx;

    /**
     * Ctor.
     * @param parseTree ANTLR parse tree of a two-part placeholder.
     */
    ParsedSinglePartPlaceholder(
        TemplateBundleParser.SinglePartPlaceholderContext parseTree
    ) {
        this.ctx = parseTree;
    }

    /**
     * Creates a placeholder with aspects obtained from the
     * {@link ParsedLexeme#ctx}.
     * @return Lexeme from markup.
     */
    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.lexemeSource(),
            this.capitalization(),
            ImmutableSet.of(),
            Agreement.NONE
        );
    }

    /**
     * Determines capitalization of this placeholder.
     * @return Capitalization to be applied to this placeholder.
     */
    private Capitalization capitalization() {
        return Character.isUpperCase(this.argumentName().charAt(0)) ?
            Capitalization.CAPITALIZE : Capitalization.IDENTITY;
    }

    /**
     * Creates a {@link LexemeSource} for this placeholder.
     * @return A lexeme source.
     */
    private LexemeSource lexemeSource() {
        return new ArgumentsLexemeSource(
            this.argumentName().toLowerCase()
        );
    }

    /**
     * Obtains the argument name used to fill out this placeholder.
     * @return Argument name obtained from an ANTLR parse tree.
     */
    private String argumentName() {
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
