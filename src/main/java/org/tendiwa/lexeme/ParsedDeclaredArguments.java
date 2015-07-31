package org.tendiwa.lexeme;

import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedDeclaredArguments
    extends TextBundleParserBaseListener
    implements DeclaredArguments {
    /**
     * Maps declared argument name to index in the argument list.
     */
    private final TObjectIntMap<String> nameToIndex;

    /**
     * @param ctx A single parsed marked up text.
     */
    public ParsedDeclaredArguments(
        TextBundleParser.TextContext ctx
    ) {
        this.nameToIndex = new TObjectIntHashMap<>(
            ctx.arguments().ID().size()
        );
        ParseTreeWalker.DEFAULT.walk(this, ctx);
    }

    @Override
    public final void enterArguments(TextBundleParser.ArgumentsContext ctx) {
        int index = 0;
        for (TerminalNode argument : ctx.ID()) {
            this.nameToIndex.put(
                argument.getText(),
                index
            );
            index++;
        }
    }

    @Override
    public final int index(String argumentName) {
        if (!this.nameToIndex.containsKey(argumentName)) {
            throw new IllegalArgumentException(
                "Argument with name " + argumentName +
                    " is not present in declared arguments"
            );
        }
        return this.nameToIndex.get(argumentName);
    }
}
