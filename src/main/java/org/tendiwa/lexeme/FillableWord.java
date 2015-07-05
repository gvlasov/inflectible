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
public class FillableWord implements Word {
    private List<Grammeme> permanentGrammemes = new LinkedList<>();
    private List<Lexeme> localizationToModifiers = new LinkedList<>();
    private String baseForm;

    public void addPermanentModifier(Grammeme grammeme) {
        this.permanentGrammemes.add(grammeme);
    }

    @Override
    public String toString() {
        return baseForm.toString();
    }

    public void addWordForm(String wordFormString, List<Grammeme> grammemes) {
        Lexeme lexeme = new BasicLexeme(wordFormString, grammemes);
        if (this.baseForm == null) {
            this.baseForm = lexeme.toString();
        }
        localizationToModifiers.add(lexeme);
    }

    @Override
    public String getBaseForm() {
        return baseForm;
    }

    @Override
    public String findWordForm(GrammaticalMeaning meaning) {
        int maxMatchingModifiers = 0;
        Lexeme bestSuitedLexeme = null;
        for (Lexeme form : localizationToModifiers) {
            int matchingModifiers = 0;
            for (Grammeme grammeme : meaning.grammemes()) {
                if (form.getGrammemes().contains(grammeme)) {
                    matchingModifiers++;
                    if (matchingModifiers > maxMatchingModifiers) {
                        maxMatchingModifiers = matchingModifiers;
                        bestSuitedLexeme = form;
                    }
                }
            }
        }
        if (maxMatchingModifiers == 0) {
            return baseForm;
        }
//		if (bestSuitedLexeme == null) {
//			throw new RuntimeException("A lexeme of word "+baseForm+" for modifiers "+Arrays.asList(modifiers)+" could not be found");
//		}
        return bestSuitedLexeme.toString();
    }

    @Override
    public GrammaticalMeaning permanentModifiers() {
        return new GrammaticalMeaning() {
            @Override
            public ImmutableSet<Grammeme> grammemes() {
                return ImmutableSet.copyOf(
                    FillableWord.this.permanentGrammemes
                );
            }
        };
    }

}
