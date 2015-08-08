package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * Lexeme defined by its persistent grammemes and a list of its forms.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
final class BasicLexeme implements Lexeme {

    private final GrammaticalMeaning persistentGrammemes;
    private final List<WordForm> forms;

    /**
     * @param forms Word forms
     */
    public BasicLexeme(
        GrammaticalMeaning persistentGrammemes,
        ImmutableList<WordForm> forms
    ) {
        this.persistentGrammemes = persistentGrammemes;
        this.forms = forms;
    }

    @Override
    public String form(final GrammaticalMeaning meaning) {
        int bestScore = 0;
        WordForm bestMatch = null;
        for (WordForm form : this.forms) {
            int score = form.grammaticalMeaning().similarity(meaning);
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
    public GrammaticalMeaning persistentGrammemes() {
        return this.persistentGrammemes;
    }

    @Override
    public String baseForm() {
        return this.forms.get(0).spelling();
    }

}
