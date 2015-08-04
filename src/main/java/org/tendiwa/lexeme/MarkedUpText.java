package org.tendiwa.lexeme;

/**
 * Text with unfilled placeholders.
 * A single marked up text. It consists of three parts:
 * <ol>
 *     <li>Localization id;</li>
 *     <li>List of argument names;</li>
 *     <li>Template body.</li>
 * </ol>
 * <pre>
 * localization_id (param1, param2, param3) {
 *     Text about [param1] and [param2][Plur] mentioning [param3][Gerund].
 * }
 * </pre>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public interface MarkedUpText {

    /**
     * Id of this text. Corresponds to entry_id rule in TextBundleParser.g4.
     * @return Id of this text.
     */
    String id();

    /**
     * @return Names of arguments from the header of this text.
     */
    DeclaredArguments declaredArguments();

    /**
     * @return Body of this text.
     */
    MarkedUpTextBody body();
}
