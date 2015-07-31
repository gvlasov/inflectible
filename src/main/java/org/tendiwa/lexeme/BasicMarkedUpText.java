package org.tendiwa.lexeme;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.rocollections.ReadOnlyList;
import org.tendiwa.rocollections.WrappingReadOnlyList;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
class BasicMarkedUpText implements MarkedUpText {
    private final Language language;
    private final NativeSpeaker nativeSpeaker;
    private final TextBundleParser.TextContext ctx;

    /**
     * Text from file containing a header and body in the following form:
     * <pre>
     * text_localization_id (param1, param2, param3) {
     *     Text about [param1] and [param2][Plur] mentioning [param3][Gerund].
     * }
     * </pre>
     * @param language Language of the text.
     * @param ctx Parse subtree containing markup.
     */
    BasicMarkedUpText(
        Language language,
        NativeSpeaker nativeSpeaker,
        TextBundleParser.TextContext ctx
    ) {
        this.language = language;
        this.nativeSpeaker = nativeSpeaker;
        this.ctx = ctx;
    }

    @Override
    public final String id() {
        return this.ctx.text_id().getText();
    }

    @Override
    public final String fillUp(Localizable... denotations) {
        final FillingUpText text = new FillingUpText(
            this.language,
            new ActualArguments(
                new ParsedDeclaredArguments(this.ctx),
                this.lexemes(denotations)
            )
        );
        ParseTreeWalker.DEFAULT.walk(
            text,
            this.ctx
        );
        return text.toString();
    }

    private ReadOnlyList<Lexeme> lexemes(Localizable[] conceptions) {
        List<Lexeme> lexemes = new ArrayList<>(conceptions.length);
        for (Localizable denotation : conceptions) {
            lexemes.add(
                this.nativeSpeaker.wordFor(denotation)
            );
        }
        return new WrappingReadOnlyList<>(lexemes);
    }

}
