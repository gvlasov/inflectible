package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection of lexemes belonging to the same denotation.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class FillableLexeme implements Lexeme {
    private List<Grammeme> permanentGrammemes = new LinkedList<>();
    private List<WordForm> localizationToModifiers = new LinkedList<>();
    private String baseForm;

    public void addPermanentModifier(Grammeme grammeme) {
        this.permanentGrammemes.add(grammeme);
    }

    @Override
    public String toString() {
        return baseForm.toString();
    }

    public void addWordForm(String wordFormString, List<Grammeme> grammemes) {
        WordForm wordForm = new BasicWordForm(wordFormString, grammemes);
        if (this.baseForm == null) {
            this.baseForm = wordForm.toString();
        }
        localizationToModifiers.add(wordForm);
    }

    @Override
    public String baseForm() {
        return baseForm;
    }

    @Override
    public String form(GrammaticalMeaning meaning) {
        int maxMatchingModifiers = 0;
        WordForm bestSuitedWordForm = null;
        for (WordForm form : localizationToModifiers) {
            int matchingModifiers = 0;
            for (Grammeme grammeme : meaning.grammemes()) {
                if (form.getGrammemes().contains(grammeme)) {
                    matchingModifiers++;
                    if (matchingModifiers > maxMatchingModifiers) {
                        maxMatchingModifiers = matchingModifiers;
                        bestSuitedWordForm = form;
                    }
                }
            }
        }
        if (maxMatchingModifiers == 0) {
            return baseForm;
        }
//		if (bestSuitedLexeme == null) {
//			throw new RuntimeException("A lexeme of lexeme "+baseForm+" for modifiers "+Arrays.asList(modifiers)+" could not be found");
//		}
        return bestSuitedWordForm.toString();
    }

    @Override
    public GrammaticalMeaning permanentModifiers() {
        return new GrammaticalMeaning() {
            @Override
            public ImmutableSet<Grammeme> grammemes() {
                return ImmutableSet.copyOf(
                    FillableLexeme.this.permanentGrammemes
                );
            }
        };
    }

}
