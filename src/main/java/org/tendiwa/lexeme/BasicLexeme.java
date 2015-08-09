package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
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
    private final List<WordForm> forms;

    /**
     * @param forms Word forms
     */
    public BasicLexeme(
        ImmutableSet<Grammeme> persistentGrammemes,
        ImmutableList<WordForm> forms
    ) {
        this.persistentGrammemes = persistentGrammemes;
        this.forms = forms;
    }

    @Override
    public String form(final ImmutableSet<Grammeme> meaning) {
        int bestScore = 0;
        WordForm bestMatch = null;
        for (WordForm form : this.forms) {
            int score = form.similarity(meaning);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = form;
            }
        }
        if (bestMatch == null) {
            throw new IllegalArgumentException(
                "Word form for grammatical meaning "+meaning+" not found"
            );
        }
        return bestMatch.spelling();
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.persistentGrammemes;
    }

    @Override
    public String baseForm() {
        return this.forms.get(0).spelling();
    }

}
