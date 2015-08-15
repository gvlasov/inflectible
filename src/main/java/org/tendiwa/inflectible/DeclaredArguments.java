package org.tendiwa.inflectible;

/**
 * List of argument names from the header line of each marked up text.
 * @see {@link ActualArguments} For the list of values passed in place of
 *  declared arguments.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public interface DeclaredArguments {
    int index(String argumentName);
}
