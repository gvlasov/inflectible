package org.tendiwa.lexeme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;

/**
 * List of arguments from the header line of each marked up text.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class MarkupArguments extends TextBundleParserBaseListener {
    private final Map<String, Lexeme> declarationsToValues = new HashMap<>();
    private final List<Lexeme> argumentValues;

    /**
     * @param ctx A single parsed marked up text.
     * @param argumentValues Values of arguments.
     */
    public MarkupArguments(
        org.tendiwa.lexeme.antlr.TextBundleParser.TextContext ctx,
        List<Lexeme> argumentValues
    ) {
        this.argumentValues = argumentValues;
        ParseTreeWalker.DEFAULT.walk(this, ctx);
    }

    @Override
    public final void enterArguments(org.tendiwa.lexeme.antlr.TextBundleParser.ArgumentsContext ctx) {
        for (TerminalNode argument : ctx.ID()) {
            this.declarationsToValues.put(
                argument.getText(),
                this.argumentValues.get(
                    this.declarationsToValues.size()
                )
            );
        }
    }

    /**
     * @param declaredName Name of argument.
     * @return Value of the argument with particular name.
     */
    public final Lexeme argumentValue(String declaredName) {
        final Lexeme lexeme = this.declarationsToValues.get(declaredName);
        if (lexeme == null) {
            throw new IllegalArgumentException(
                "No argument with name \"" + declaredName + "\" "
            );
        }
        return lexeme;
    }
}
