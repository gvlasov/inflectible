package org.tendiwa.lexeme;

import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class ParsedDeclaredArguments
    extends TextBundleParserBaseListener implements DeclaredArguments {
    /**
     * Maps declared argument name to index in the argument list.
     */
    private final TextBundleParser.ArgumentsContext ctx;

    /**
     * @param ctx A single parsed marked up text.
     */
    public ParsedDeclaredArguments(TextBundleParser.ArgumentsContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public final int index(String argumentName) {
        if (!this.nameToIndex().containsKey(argumentName)) {
            throw new IllegalArgumentException(
                "Argument with name " + argumentName +
                    " is not present in declared arguments"
            );
        }
        return this.nameToIndex().get(argumentName);
    }

    // TODO: Will be made lazy in #47
    private TObjectIntMap<String> nameToIndex() {
        TObjectIntMap<String> map = new TObjectIntHashMap<>(
            ParsedDeclaredArguments.this.ctx.ID().size()
        );
        int index = 0;
        for (TerminalNode argument : this.ctx.ID()) {
            map.put(argument.getText(), index);
            index++;
        }
        return map;
    }
}
