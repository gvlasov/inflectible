package org.tendiwa.lexeme;


import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.tenidwa.collections.utils.Collectors;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class WordFormFromMarkup implements WordForm {
    private final WordFormMarkup markup;
    private final Grammar grammar;

    WordFormFromMarkup(WordFormMarkup markup, Grammar grammar) {
        this.markup = markup;
        this.grammar = grammar;
    }

    @Override
    public String spelling() {
        return this.markup.spelling();
    }

    @Override
    public int similarity(ImmutableSet<Grammeme> grammemes) {
        return Sets.intersection(
            this.grammaticalMeaning(),
            grammemes
        ).size();
    }

    private ImmutableSet<Grammeme> grammaticalMeaning() {
        return this.markup.grammemes()
            .stream()
            .map(this.grammar::grammemeByName)
            .collect(Collectors.toImmutableSet());
    }
}
