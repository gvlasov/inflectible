package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicWordForm implements WordForm {
    private final String spelling;
    private final ImmutableSet<Grammeme> grammaticalMeaning;

    public BasicWordForm(
        String spelling,
        ImmutableSet<Grammeme> grammaticalMeaning
    ) {
        this.spelling = spelling;
        this.grammaticalMeaning = grammaticalMeaning;
    }

    @Override
    public String spelling() {
        return this.spelling;
    }

    @Override
    public int similarity(ImmutableSet<Grammeme> grammemes) {
        return Sets.intersection(
            grammemes,
            this.grammaticalMeaning
        ).size();
    }
}
