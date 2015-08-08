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
final class ParsedGrammaticalMeaning
    extends WrappingReadOnlySet<Grammeme>
    implements GrammaticalMeaning {

    /**
     * @param grammar Grammar of the target language
     * @param grammemesCtx Parse tree
     */
    public ParsedGrammaticalMeaning(
        Grammar grammar,
        WordBundleParser.GrammemesContext grammemesCtx
    ) {
        super(ParsedGrammaticalMeaning.grammemes(grammar, grammemesCtx));
    }

    private static ImmutableSet<Grammeme> grammemes(
        Grammar grammar,
        WordBundleParser.GrammemesContext grammemesCtx
    ) {
        final List<TerminalNode> grammemeNodes = grammemesCtx.GRAMMEME();
        final ImmutableSet.Builder<Grammeme> builder = ImmutableSet.builder();
        for (TerminalNode node : grammemeNodes) {
            builder.add(grammar.grammemeByName(node.getText()));
        }
        return builder.build();
    }
}
