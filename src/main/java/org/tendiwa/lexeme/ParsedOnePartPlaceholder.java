package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.tendiwa.lexeme.antlr.TextBundleParser;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedOnePartPlaceholder
    extends AbstractVariableConceptPlaceholder {
    private final TextBundleParser.Base_form_placeholderContext ctx;

    ParsedOnePartPlaceholder(
        TextBundleParser.Base_form_placeholderContext ctx
    ) {
        this.ctx = ctx;
    }

    @Override
    public ImmutableSet<Grammeme> grammaticalMeaning() {
        return ImmutableSet.of();
    }
    @Override
    public final String fillUp(ActualArguments arguments) {
        return arguments.argumentValue(this.argumentName()).baseForm();
    }

    @Override
    protected String identifier() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
