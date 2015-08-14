package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tenidwa.collections.utils.Collectors;

import java.util.Optional;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedTwoPartVariableConceptPlaceholder
        implements Placeholder {
    private final Grammar grammar;

    private final TextBundleParser.TwoPartPlaceholderContext ctx;

    ParsedTwoPartVariableConceptPlaceholder(
        Grammar grammar,
        TextBundleParser.TwoPartPlaceholderContext ctx
    ) {
        super();
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String fillUp(
        ImmutableMap<String, Lexeme> arguments,
        ImmutableMap<String, Lexeme> vocabulary
    ) {
        return this.delegate().fillUp(arguments, vocabulary);
    }

    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.lexemeSource(),
            this.capitalization(),
            this.grammemes(),
            this.agreement()
        );
    }

    private Agreement agreement() {
        final Optional<String> agreementName = this.agreementArgumentName();
        if (agreementName.isPresent()) {
            return new ArgumentAgreement(agreementName.get());
        } else {
            return Agreement.NONE;
        }
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

    private ImmutableSet<Grammeme> grammemes() {
        return this.ctx.GRAMMEME()
            .stream()
            .map(ParseTree::getText)
            .map(this.grammar::grammemeByName)
            .collect(Collectors.toImmutableSet());
    }

    private Optional<String> agreementArgumentName() {
        if (this.ctx.agreement() == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.ctx.agreement().AGREEMENT_ID().getText());
        }
    }

    private String name() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
