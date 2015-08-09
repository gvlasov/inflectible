package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tenidwa.collections.utils.Collectors;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedTwoPartVariableConceptPlaceholder
    extends AbstractVariableConceptPlaceholder {
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
    public ImmutableSet<Grammeme> grammaticalMeaning() {
        return this.ctx.GRAMMEME()
            .stream()
            .map(ParseTree::getText)
            .map(this.grammar::grammemeByName)
            .collect(Collectors.toImmutableSet());
    }

    @Override
    public String fillUp(ActualArguments arguments) {
        return arguments.argumentValue(this.argumentName())
            .formForPlaceholder(
                new AgreeingPlaceholder(
                    arguments.argumentValue(this.agreementArgumentName())
                )
            );
    }

    private String agreementArgumentName() {
        return this.ctx.agreement().AGREEMENT_ID().getText();
    }

    @Override
    protected String identifier() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }

    /**
     * @author Georgy Vlasov (suseika@tendiwa.org)
     * @version $Id$
     * @since 0.1
     */
    private final class AgreeingPlaceholder implements Placeholder {
        private final Lexeme lexeme;

        public AgreeingPlaceholder(Lexeme lexeme) {
            this.lexeme = lexeme;
        }

        @Override
        public ImmutableSet<Grammeme> grammaticalMeaning() {
            return ImmutableSet.<Grammeme>builder()
                .addAll(
                    ParsedTwoPartVariableConceptPlaceholder.this
                        .grammaticalMeaning()
                )
                .addAll(this.lexeme.persistentGrammemes())
                .build();
        }

        @Override
        public String fillUp(ActualArguments arguments) {
            return arguments
                .argumentValue(
                    ParsedTwoPartVariableConceptPlaceholder.this.argumentName()
                )
                .formForPlaceholder(this);
        }

        @Override
        public boolean capitalizes() {
            return ParsedTwoPartVariableConceptPlaceholder.this.capitalizes();
        }
    }
}
