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
final class IdOnlyPlaceholder implements Placeholder {

    private final TextBundleParser.Base_form_placeholderContext ctx;

    public IdOnlyPlaceholder(
        TextBundleParser.Base_form_placeholderContext noCategoryPlaceholderCtx
    ) {
        this.ctx = noCategoryPlaceholderCtx;
    }

    @Override
    public String id() {
        return this.ctx.CAPITALIZABLE_ID().getText();
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
