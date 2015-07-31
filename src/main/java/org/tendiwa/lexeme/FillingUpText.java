package org.tendiwa.lexeme;

import org.tendiwa.lexeme.antlr.TextBundleParser;
import org.tendiwa.lexeme.antlr.TextBundleParserBaseListener;

/**
 * Complete plain text with filled up placeholders that constructs itself by
 * walking a parse tree.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
class FillingUpText extends TextBundleParserBaseListener {
    private final Language language;
    private final ActualArguments arguments;
    private final StringBuilder builder;

    FillingUpText(
        Language language,
        ActualArguments arguments
    ) {
        this.language = language;
        this.arguments = arguments;
        this.builder = new StringBuilder();
    }

    @Override
    public final void enterPlaceholder(
        TextBundleParser.PlaceholderContext placeholderCtx
    ) {
        final TwoPartPlaceholder placeholder =
            new TwoPartPlaceholder(placeholderCtx);
        this.builder.append(
            this.arguments
                .argumentValue(
                    placeholder.id()
                )
                .form(
                    new PlaceholderGrammaticalMeaning(
                        placeholder,
                        this.language,
                        this.arguments
                    )
                )
        );
    }

    @Override
    public final void enterRaw_text(
        TextBundleParser.Raw_textContext rawTextCtx
    ) {
        this.builder.append(
            rawTextCtx.getText()
        );
    }

    @Override
    public final void enterNo_category_placeholder(
        TextBundleParser.No_category_placeholderContext noCategoryPlaceholderCtx
    ) {
        this.builder.append(
            this.arguments
                .argumentValue(
                    noCategoryPlaceholderCtx
                        .CAPITALIZABLE_ID()
                        .getText()
                )
                .baseForm()
        );
    }

    /**
     * @return Filled up plain text.
     */
    @Override
    public final String toString() {
        return this.builder.toString();
    }
}
