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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Text with unfilled placeholders.
 * A single marked up text. It consists of three parts:
 * <ol>
 *     <li>Localization argumentName;</li>
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
public interface TextTemplate {

    /**
     * Fills up the template with arguments.
     * @param lexemes Lexemes passed as arguments to the template
     * @param vocabulary Vocabulary of the language of the text in this template
     * @return Text with placeholders filled up
     */
    String fillUp(
        ImmutableList<Lexeme> lexemes,
        ImmutableMap<String, Lexeme> vocabulary
    );
}
