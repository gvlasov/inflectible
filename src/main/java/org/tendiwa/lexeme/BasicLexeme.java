package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import java.util.List;

/**
 * Lexeme defined by its persistent grammemes and a list of its forms.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class BasicLexeme implements Lexeme {

    private final ImmutableSet<Grammeme> persistentGrammemes;
    private final List<LexemeEntry> formsWithGrammemes;

    /**
     * @param persistentGrammemes Grammemes that belong to each word form of
     * this lexeme.
     * @param formsWithGrammemes Word forms with grammemes that belong to
     * them. Don't contain grammemes from {@code persistentGrammemes}.
     */
    public BasicLexeme(
        final ImmutableSet<Grammeme> persistentGrammemes,
        final List<LexemeEntry> formsWithGrammemes
    ) {
        this.persistentGrammemes = persistentGrammemes;
        this.formsWithGrammemes = formsWithGrammemes;
    }

    @Override
    public String form(final GrammaticalMeaning meaning) {
        int maxScore = this.persistentGrammemes.size();
        int bestScore = 0;
        LexemeEntry bestMatch = null;
        for (LexemeEntry entry : this.formsWithGrammemes) {
            int score = entry.grammaticalMeaning().similarity(meaning);
            if (score == maxScore) {
                return entry.wordForm();
            } else if (score > bestScore) {
                bestScore = score;
                bestMatch = entry;
            }
        }
        return bestMatch.wordForm();
    }

    @Override
    public String baseForm() {
        return this.formsWithGrammemes.get(0).wordForm();
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.persistentGrammemes;
    }
}
