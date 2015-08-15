package org.tendiwa.inflectible;

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
    public String defaultSpelling() {
        return this.baseForm().spelling();
    }

    @Override
    public String wordForm(ImmutableSet<Grammeme> grammaticalMeaning) {
        int bestScore = 0;
        WordForm bestMatch = this.baseForm();
        for (WordForm form : this.wordForms) {
            int score = form.similarity(grammaticalMeaning);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = form;
            }
        }
        return bestMatch.spelling();
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.persistentGrammemes;
    }

    private WordForm baseForm() {
        return this.wordForms.get(0);
    }
}
