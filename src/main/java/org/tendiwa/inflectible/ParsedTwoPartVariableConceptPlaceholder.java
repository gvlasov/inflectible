package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tendiwa.inflectible.antlr.TemplateBundleParser;
import org.tenidwa.collections.utils.Collectors;

import java.util.Optional;

/**
 * {@link Placeholder} parsed from an ANTLR parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedTwoPartVariableConceptPlaceholder
        implements Placeholder {
    /**
     * Grammar of the language of this placeholder.
     */
    private final Grammar grammar;

    /**
     * ANTLR parse tree of a placeholder.
     */
    private final TemplateBundleParser.TwoPartPlaceholderContext ctx;

    /**
     * Ctor.
     * @param rules Grammar of a language of a template this placeholder
     *  comes from.
     * @param parseTree ANTLR parse tree of a placeholder.
     */
    ParsedTwoPartVariableConceptPlaceholder(
        Grammar rules,
        TemplateBundleParser.TwoPartPlaceholderContext parseTree
    ) {
        super();
        this.grammar = rules;
        this.ctx = parseTree;
    }

    @Override
    public String fillUp(
        ImmutableMap<String, Lexeme> arguments,
        ImmutableMap<String, Lexeme> vocabulary
    ) {
        return this.delegate().fillUp(arguments, vocabulary);
    }

    /**
     * Creates a template from the
     * {@link ParsedTwoPartVariableConceptPlaceholder#ctx}.
     * @return Placeholder
     */
    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.lexemeSource(),
            this.capitalization(),
            this.grammemes(),
            this.agreement()
        );
    }

    /**
     * Finds out the kind of agreement used in this placeholder.
     * @return {@link Agreement#NONE} if there is no agreement, or
     * {@link ArgumentAgreement} if there is agreement.
     */
    private Agreement agreement() {
        final Optional<String> agreementName = this.agreementArgumentName();
        if (agreementName.isPresent()) {
            return new ArgumentAgreement(agreementName.get());
        } else {
            return Agreement.NONE;
        }
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
     * Obtains grammemes of this placeholder from an ANTLR parse tree.
     * @return Grammemes of this placeholder.
     */
    private ImmutableSet<Grammeme> grammemes() {
        return this.ctx.GRAMMEME()
            .stream()
            .map(ParseTree::getText)
            .map(this.grammar::grammemeByName)
            .collect(Collectors.toImmutableSet());
    }

    /**
     * Obtains the name of the argument this placeholder agrees with.
     * @return Name of the argument to agree with, or empty if none is
     *  specified.
     */
    private Optional<String> agreementArgumentName() {
        if (this.ctx.agreement() == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.ctx.agreement().AGREEMENT_ID().getText());
        }
    }

    /**
     * Obtains the name of the argument that holds a lexeme to fill out this
     * placeholder.
     * @return Name of an argument.
     */
    private String argumentName() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
