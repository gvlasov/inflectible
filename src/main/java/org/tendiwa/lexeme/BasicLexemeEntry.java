package org.tendiwa.lexeme;

import org.tendiwa.lexeme.antlr.WordBundleParser;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class BasicLexemeEntry implements LexemeEntry {

    private final GrammaticalMeaning grammemes;
    private final String wordForm;

    BasicLexemeEntry(
        final Language language,
        final WordBundleParser.EntryContext entryCtx
    ) {
        this.grammemes = new BasicGrammaticalMeaning(
            language,
            entryCtx.grammemes()
        );
        this.wordForm = this.extractWordForm(entryCtx);
    }

    /**
     * @param entryCtx Parse tree
     * @return A word form as a plaintext string.
     */
    private String extractWordForm(
        final WordBundleParser.EntryContext entryCtx
    ) {
        return entryCtx.WORD_FORM().getText();
    }

    @Override
    public String wordForm() {
        return this.wordForm;
    }

    @Override
    public GrammaticalMeaning grammaticalMeaning() {
        return this.grammemes;
    }
}
