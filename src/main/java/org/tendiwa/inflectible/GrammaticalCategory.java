/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Georgy Vlasov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tendiwa.inflectible;

/**
 * A set of mutually exclusive grammemes.
 * <p>For example, in English, the grammatical category of Number consists of
 * Singular and Plural grammemes.
 * A word form can't be both singular and plural at the same time.
 * @see <a href="https://en.wikipedia.org/wiki/Grammatical_category>Grammatical
 *  category article on Wikipedia</a>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public interface GrammaticalCategory {
    /**
     * Default grammeme to be used when it is not specified which grammeme to
     * use for this grammatical category. Default grammemes are the ones that
     * are used in headwords in dictionaries. For example, the default
     * grammeme for the English grammatical category of number is Singular.
     * @return Default grammeme
     */
    Grammeme defaultGrammeme();

    /**
     * Checks if this grammatical category contains a particular grammeme.
     * @param grammeme Grammeme
     * @return True iff this grammatical category contains a particular grammeme
     */
    boolean containsGrammeme(Grammeme grammeme);

    /**
     * Finds the grammeme of this grammatical category from meaning.
     * @param meaning GrammaticalMeaning to search in
     * @return Grammeme in this grammatical category
     * @throws Exception If could not find the grammeme
     */
    Grammeme getGrammeme(GrammaticalMeaning meaning) throws Exception;
}
