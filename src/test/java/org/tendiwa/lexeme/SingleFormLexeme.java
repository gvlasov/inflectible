package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class SingleFormLexeme implements Lexeme {
    private String spelling;

    SingleFormLexeme(String spelling) {
        this.spelling = spelling;
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

    private Lexeme delegate() {
        return new BasicLexeme(
            ImmutableSet.of(),
            ImmutableList.<WordForm>of(
                new BasicWordForm(this.spelling, ImmutableSet.of())
            )
        );
    }
}
