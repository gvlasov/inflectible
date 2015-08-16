package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Lexeme with only its dictionary form (with default grammatical meaning)
 * and no persistent grammemes.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class SingleFormLexeme implements Lexeme {
    /**
     * Spelling of the dictionary word form.
     */
    private String spelling;

    /**
     * Ctor.
     * @param wordForm Spelling of the dictionary word form.
     */
    SingleFormLexeme(String wordForm) {
        this.spelling = wordForm;
    }

    @Override
    public String defaultSpelling() {
        return this.delegate().defaultSpelling();
    }

    @Override
    public String wordForm(ImmutableSet<Grammeme> grammaticalMeaning) {
        return this.delegate().wordForm(grammaticalMeaning);
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.delegate().persistentGrammemes();
    }

    /**
     * Creates a lexeme to delegate all the interface methods of
     * {@link SingleFormLexeme}.
     * @return Lexeme with a single word form (with default grammatical
     *  meaning) and no persistent grammemes.
     */
    private Lexeme delegate() {
        return new BasicLexeme(
            ImmutableSet.of(),
            ImmutableList.<WordForm>of(
                new BasicWordForm(this.spelling, ImmutableSet.of())
            )
        );
    }
}
