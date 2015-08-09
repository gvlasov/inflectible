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
public final class ParsedTwoPartVariableConceptPlaceholder
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
    protected String identifier() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
