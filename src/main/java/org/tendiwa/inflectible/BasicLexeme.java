package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * {@link Lexeme} defined by its set of persistent grammemes and list of
 * possible word forms.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicLexeme implements Lexeme {
    /**
     * Persistent grammemes.
     */
    private final ImmutableSet<Grammeme> persistent;

    /**
     * Word forms.
     */
    private final ImmutableList<WordForm> forms;

    /**
     * Ctor.
     * @param grammemes Persistent grammemes
     * @param spellings Word forms
     */
    public BasicLexeme(
        ImmutableSet<Grammeme> grammemes,
        ImmutableList<WordForm> spellings
    ) {
        this.persistent = grammemes;
        this.forms = spellings;
    }

    @Override
    public String defaultSpelling() {
        return this.baseForm().spelling();
    }

    @Override
    public String wordForm(ImmutableSet<Grammeme> grammaticalMeaning) {
        int bestScore = 0;
        WordForm bestMatch = this.baseForm();
        for (WordForm form : this.forms) {
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
        return this.persistent;
    }

    /**
     * The dictionary form of this lexeme.
     * @see <a href="https://en.wikipedia.org/wiki/Dictionary_form">
     *  Dictionary form</a>
     * @return The dictionary form of this lexeme.
     */
    private WordForm baseForm() {
        return this.forms.get(0);
    }
}
