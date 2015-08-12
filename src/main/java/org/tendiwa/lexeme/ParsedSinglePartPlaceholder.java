package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.tendiwa.lexeme.antlr.TextBundleParser;

import java.util.Optional;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class ParsedSinglePartPlaceholder implements Placeholder {
    private final TextBundleParser.Base_form_placeholderContext ctx;

    ParsedSinglePartPlaceholder(
        TextBundleParser.Base_form_placeholderContext ctx
    ) {
        this.ctx = ctx;
    }

    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.identifier(),
            ImmutableSet.of(),
            Optional.empty()
        );
    }


    private String identifier() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }

    @Override
    public String fillUp(ActualArguments arguments) {
        return this.delegate().fillUp(arguments);
    }
}
