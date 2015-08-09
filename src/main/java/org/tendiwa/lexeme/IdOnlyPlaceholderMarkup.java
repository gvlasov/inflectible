package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import java.util.Optional;
import org.tendiwa.lexeme.antlr.TextBundleParser;

/**
 * Placeholder that has only its identifier and doesn't have any additional
 * grammatical meaning specified.
 * <p/>
 * Example: {@code [Attacker]} or {@code [defender]}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class IdOnlyPlaceholderMarkup implements PlaceholderMarkup {

    private final TextBundleParser.Base_form_placeholderContext ctx;

    public IdOnlyPlaceholderMarkup(
        TextBundleParser.Base_form_placeholderContext noCategoryPlaceholderCtx
    ) {
        this.ctx = noCategoryPlaceholderCtx;
    }

    @Override
    public String argumentName() {
        return
            new BasicCapitalization(
                this.ctx.CAPITALIZABLE_ID().getText()
            ).uncapitalized();
    }

    @Override
    public Optional<String> agreementId() {
        return Optional.empty();
    }

    @Override
    public ImmutableList<String> explicitGrammemes() {
        return ImmutableList.of();
    }

}
