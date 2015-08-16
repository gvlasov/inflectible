package org.tendiwa.inflectible;

import com.google.common.collect.ImmutableSet;

import java.util.Map;

/**
 * Agreement is a relation between two members of a sentence. One member is
 * called <i>dominant</i>, and the other is called <i>dependent</i>. In
 * agreement relation, the grammatical meaning of the resulting {@link WordForm}
 * of the dependent word is modified with the
 * {@link Lexeme#persistentGrammemes()} of the dominant word form.
 * <p/>
 * In markup, agreement is expressed with:
 * <pre>[dependent][Explicit Grammemes;dominant]</pre> syntax. For example:
 * <p/>
 * <pre>
 *     interesting.fact(subject, predicate) {
 *       [Subject] [predicate][III;subject] well.
 *     }
 * </pre>
 * If you pass lexemes SHOVEL and DIG, it will be "Shovel digs well". SHOVEL
 * doesn't have any persistent grammemes During word form resolution,
 * predicate DIG will only have one grammeme III (third person). DIG in third
 * person is (he...) <i>digs</i>
 * <p/>
 * However, if you pass lexemes SCISSORS and CUT, it will be "Scissors cut
 * well". Lexeme SCISSORS has a persistent grammeme Plur (its only word form
 * is plural), so agreement relation adds grammeme Plur to word form
 * resolution for CUT, so a word form with grammemes III and Plur is
 * returned, which is (they...) <i>cut</i>.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
@FunctionalInterface
public interface Agreement {
    /**
     * No agreement. No grammemes are added to the dependent word as a result
     * of applying this type of agreement.
     */
    Agreement NONE = arguments -> ImmutableSet.of();

    /**
     * Finds out additional for resolution of a form of the dependent
     * word.
     * @param arguments Actual values of arguments passed to a
     *  {@link TextTemplate}.
     * @return Persistent grammemes of the dominant word.
     */
    ImmutableSet<Grammeme> grammemes(Map<String, Lexeme> arguments);
}
