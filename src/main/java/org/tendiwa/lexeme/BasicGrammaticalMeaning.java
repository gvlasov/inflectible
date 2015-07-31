package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.WordBundleParser;
import org.tendiwa.rocollections.WrappingReadOnlySet;

/**
 * Grammatical meaning coming from a parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class BasicGrammaticalMeaning
    extends WrappingReadOnlySet<Grammeme>
    implements GrammaticalMeaning {

    /**
     * @param language Target language
     * @param grammemesCtx Parse tree
     */
    public BasicGrammaticalMeaning(
        Language language,
        WordBundleParser.GrammemesContext grammemesCtx
    ) {
        super(
            BasicGrammaticalMeaning.grammemes(language, grammemesCtx)
        );
    }

    private static ImmutableSet<Grammeme> grammemes(
        Language language,
        WordBundleParser.GrammemesContext grammemesCtx
    ) {
        final List<TerminalNode> grammemeNodes = grammemesCtx.GRAMMEME();
        final ImmutableSet.Builder<Grammeme> builder = ImmutableSet.builder();
        for (TerminalNode node : grammemeNodes) {
            builder.add(language.stringToModifier(node.getText()));
        }
        return builder.build();
    }
}
