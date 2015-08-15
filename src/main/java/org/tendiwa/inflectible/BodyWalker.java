package org.tendiwa.inflectible;

/**
 * Walks over a template, encountering plaintext parts and placeholder parts.
 * <p/>
 * For example, this marked up text has two plaintext parts and one
 * placeholder part:
 * <pre>
 * story (dude) {
 *     Once upon a time, some [dude][Plur] were born.
 * }
 * </pre>
 * Those parts are:
 * <ol>
 *     <li><pre>Once upon a time, some </pre> — plain text;</li>
 *     <li><pre>[dude][Plur]</pre> — placeholder;</li>
 *     <li><pre> were born.</pre> — plain text;</li>
 * </ol>
 * <p/>
 * A BodyWalker walking over the body of such {@link TextTemplate} would
 * encounter these parts in this very order, and call corresponding methods
 * for each encounter.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
@FunctionalInterface
public interface BodyWalker {
    /**
     * Called when a placeholder is encountered.
     * @param placeholder Encountered placeholder.
     * @return Text that {@code placehodler} is turned into.
     */
    String enterPlaceholder(Placeholder placeholder);
}
