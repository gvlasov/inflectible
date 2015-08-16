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
import org.tenidwa.collections.utils.Collectors;

/**
 * {@link NativeSpeaker} defined by a vocabulary of {@link Lexeme}s and a
 * textuary of {@link TextTemplate}s.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicNativeSpeaker implements NativeSpeaker {
    /**
     * Vocabulary of lexemes.
     */
    private final ImmutableMap<String, Lexeme> vocabulary;

    /**
     * Textuary of templates.
     */
    private final ImmutableMap<String, TextTemplate> textuary;

    /**
     * Ctor.
     * @param lexemes Vocabulary of lexemes
     * @param templates Textuary of templates
     */
    BasicNativeSpeaker(
        ImmutableMap<String, Lexeme> lexemes,
        ImmutableMap<String, TextTemplate> templates
    ) {
        this.vocabulary = lexemes;
        this.textuary = templates;
    }

    @Override
    public String text(String textTemplateId, Localizable... arguments) {
        return this.textuary.get(textTemplateId).fillUp(
            ImmutableList.copyOf(arguments)
                .stream()
                .map(argument -> this.vocabulary.get(argument.getLocalizationId()))
                .collect(Collectors.toImmutableList()),
            this.vocabulary
        );
    }
}
