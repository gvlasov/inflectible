package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.TextBundleParser;

import java.util.List;
import java.util.Optional;

/**
 * Placeholder with two parts in brackets: first one with argument name,
 *  second one with possible grammemes and agreement argument.
 *  <p/>
 *  Example of a two-part placeholder:
 *  <pre>[attacker][Plur]</pre>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class TwoPartPlaceholderMarkup implements PlaceholderMarkup {
    private final TextBundleParser.TwoPartPlaceholderContext placeholderCtx;

    /**
     * Creates a two part placeholder from a parse tree.
     * @param placeholderCtx A piece of parse tree containing the
     * two-part-placeholder's tokens.
     */
    public TwoPartPlaceholderMarkup(
        TextBundleParser.TwoPartPlaceholderContext placeholderCtx
    ) {
        this.placeholderCtx = placeholderCtx;
    }


    @Override
    public String argumentName() {
        return new BasicCapitalization(
            this.placeholderCtx.CAPITALIZABLE_ID().getText()
        ).uncapitalized();
    }

    @Override
    public final Optional<String> agreementId() {
        final TextBundleParser.AgreementContext agreement =
            this.placeholderCtx.agreement();
        if (agreement != null) {
            return Optional.of(
                agreement.AGREEMENT_ID().getText()
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ImmutableList<String> explicitGrammemes() {
        final List<TerminalNode> categoryNodes = this.placeholderCtx.GRAMMEME();
        final ImmutableList.Builder<String> categories =
            ImmutableList.builder();
        for (TerminalNode node : categoryNodes) {
            categories.add(node.getText());
        }
        return categories.build();
    }
}
