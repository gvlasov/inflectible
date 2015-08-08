package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableSet;
import org.tendiwa.rocollections.WrappingReadOnlySet;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicGrammaticalMeaning
    extends WrappingReadOnlySet<Grammeme>
    implements GrammaticalMeaning {

    public BasicGrammaticalMeaning(ImmutableSet<Grammeme> collect) {
        super(collect);
    }

    public BasicGrammaticalMeaning(Grammeme... grammemes) {
        this(ImmutableSet.copyOf(grammemes));
    }
}
