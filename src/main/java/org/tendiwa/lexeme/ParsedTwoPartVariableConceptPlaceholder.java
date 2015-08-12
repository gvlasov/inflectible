package org.tendiwa.lexeme;

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

    private final TextBundleParser.PlaceholderContext ctx;

    ParsedTwoPartVariableConceptPlaceholder(
        Grammar grammar,
        TextBundleParser.PlaceholderContext ctx
    ) {
        super();
        this.grammar = grammar;
        this.ctx = ctx;
    }

    @Override
    public String fillUp(ActualArguments arguments) {
        return this.delegate().fillUp(arguments);
    }

    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.name(),
            this.grammemes(),
            this.agreementArgumentName()
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
