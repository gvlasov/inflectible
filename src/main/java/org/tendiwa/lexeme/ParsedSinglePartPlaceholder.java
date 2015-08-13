package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.tendiwa.lexeme.antlr.TextBundleParser;

import java.util.Map;
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

    @Override
    public String fillUp(Map<String, Lexeme> arguments) {
        return this.delegate().fillUp(arguments);
    }

    private Placeholder delegate() {
        return new BasicPlaceholder(
            this.name().toLowerCase(),
            Character.isUpperCase(this.name().charAt(0)),
            ImmutableSet.of(),
            Optional.empty()
        );
    }

    private String name() {
        return this.ctx.CAPITALIZABLE_ID().getText();
    }
}
