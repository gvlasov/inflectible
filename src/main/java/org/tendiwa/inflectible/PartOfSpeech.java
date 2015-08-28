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
 * A part of speech is a category of words which have similar grammatical
 * properties. Words that are assigned to the same part of speech generally
 * display similar behavior in terms of syntax—they play similar roles within
 * the grammatical structure of sentences—and sometimes in terms of morphology,
 * in that they undergo inflection for similar properties.
 * <p/>
 * For example, in English, noun, verb and adjective are some of the parts of
 * speech.
 * @see <a href="https://en.wikipedia.org/wiki/Part_of_speech">Part of speech
 *  article on Wikipedia</a>
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.2.0
 */
public interface PartOfSpeech {
    /**
     * Checks if this part of speech uses a particular grammatical category.
     * <p>For example, in English language, both nouns and verbs use the
     * grammatical number, but only verbs use grammatical tense.
     * @param category Grammatical category
     * @return True iff this part of speech uses a particular grammatical
     *  category.
     * @throws Exception If could not determine if this part of speech uses a
     *  category or not
     */
    boolean usesCategory(GrammaticalCategory category) throws Exception;
}
