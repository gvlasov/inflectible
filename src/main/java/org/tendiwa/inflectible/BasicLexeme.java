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
import com.google.common.collect.ImmutableSet;

/**
 * {@link Lexeme} defined by its set of persistent grammemes and list of
 * possible word forms.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicLexeme implements Lexeme {
    /**
     * Persistent grammemes.
     */
    private final transient ImmutableSet<Grammeme> persistent;

    /**
     * Word forms.
     */
    private final transient ImmutableList<WordForm> forms;

    /**
     * Ctor.
     * @param grammemes Persistent grammemes
     * @param spellings Word forms
     */
    public BasicLexeme(
        final ImmutableSet<Grammeme> grammemes,
        final ImmutableList<WordForm> spellings
    ) {
        this.persistent = grammemes;
        this.forms = spellings;
    }

    @Override
    public Spelling defaultSpelling() {
        return this.baseForm().spelling();
    }

    @Override
    public Spelling wordForm(final ImmutableSet<Grammeme> grammemes) {
        int bestScore = 0;
        WordForm bestMatch = this.baseForm();
        for (final WordForm form : this.forms) {
            final int score = form.similarity(grammemes);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = form;
            }
        }
        return bestMatch.spelling();
    }

    @Override
    public ImmutableSet<Grammeme> persistentGrammemes() {
        return this.persistent;
    }

    /**
     * The dictionary form of this lexeme.
     * @see <a href="https://en.wikipedia.org/wiki/Dictionary_form">
     *  Dictionary form</a>
     * @return The dictionary form of this lexeme.
     */
    private WordForm baseForm() {
        return this.forms.get(0);
    }
}
