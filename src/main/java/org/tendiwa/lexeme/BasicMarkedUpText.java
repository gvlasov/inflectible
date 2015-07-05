package org.tendiwa.lexeme;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.tendiwa.lexeme.antlr.TextBundleParser;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
class BasicMarkedUpText implements MarkedUpText {
    private final Language language;
    private final Vocabulary vocabulary;
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
        Vocabulary vocabulary,
        TextBundleParser.TextContext ctx
    ) {
        this.language = language;
        this.vocabulary = vocabulary;
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
            new MarkupArguments(this.ctx, this.words(denotations))
        );
        ParseTreeWalker.DEFAULT.walk(
            text,
            this.ctx
        );
        return text.toString();
    }

    private List<Word> words(Localizable[] denotations) {
        List<Word> words = new ArrayList<>(denotations.length);
        for (Localizable denotation : denotations) {
            words.add(
                this.vocabulary.getWord(denotation.getLocalizationId())
            );
        }
        return words;
    }

}
