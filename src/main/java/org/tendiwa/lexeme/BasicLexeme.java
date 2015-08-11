package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicLexeme implements Lexeme {
    private final ImmutableSet<Grammeme> persistentGrammemes;
    private final ImmutableList<WordForm> wordForms;

    public BasicLexeme(
        ImmutableSet<Grammeme> persistentGrammemes,
        ImmutableList<WordForm> wordForms
    ) {
        this.persistentGrammemes = persistentGrammemes;
        this.wordForms = wordForms;
    }

    @Override
    public String baseForm() {
        return this.wordForms.get(0).spelling();
    }

    @Override
    public String formForPlaceholder(Placeholder placeholder) {
        int bestScore = 0;
        WordForm bestMatch = null;
        for (WordForm form : this.wordForms) {
            int score = form.similarity(placeholder.grammaticalMeaning());
            if (score > bestScore) {
                bestScore = score;
                bestMatch = form;
            }
        }
        if (bestMatch == null) {
            throw new IllegalArgumentException(
                String.format(
                    "Word form for grammatical meaning %s not found",
                    placeholder.grammaticalMeaning()
                )
            );
        }
        return bestMatch.spelling();
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.persistentGrammemes;
    }
}
