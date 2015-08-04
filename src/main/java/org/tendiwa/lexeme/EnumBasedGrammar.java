package org.tendiwa.lexeme;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class EnumBasedGrammar implements Grammar {

    private final Class<? extends Grammeme> grammemes;

    public EnumBasedGrammar(Class<? extends Grammeme> grammemes) {
        if (!grammemes.isEnum()) {
            throw new IllegalArgumentException(
                String.format(
                    "%s: Grammemes class must be an enum",
                    grammemes.getCanonicalName()
                )
            );
        }
        this.grammemes = grammemes;
    }

    @Override
    public Grammeme grammemeByName(String name) {
        try {
            return (Grammeme) this.grammemes
                .getMethod("valueOf", String.class)
                .invoke(null, name);
        } catch (
            NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException e
            ) {
            throw new AssertionError("Reflective static method call failed");
        }
    }
}
