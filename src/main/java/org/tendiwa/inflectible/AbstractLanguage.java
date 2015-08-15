package org.tendiwa.inflectible;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractLanguage implements Language {


    private final Class<? extends Grammeme> grammemesClass;

    protected AbstractLanguage(Class<? extends Grammeme> grammemesClass) {
        this.grammemesClass = grammemesClass;
    }

    @Override
    public Grammar grammar() {
        return new EnumBasedGrammar(this.grammemesClass);
    }
}
