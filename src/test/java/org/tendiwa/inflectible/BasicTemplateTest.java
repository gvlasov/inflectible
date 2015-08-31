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
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link BasicTemplate}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.3.0
 */
public final class BasicTemplateTest {
    /**
     * {@link BasicTemplate} can fill itself up.
     * @throws Exception If fails
     */
    @Test
    public void fillsUp() throws Exception {
        final TemplateBodyPiece one = Mockito.mock(TemplateBodyPiece.class);
        Mockito.when(one.fillUp(Mockito.any(), Mockito.any()))
            .thenReturn("Hey");
        final TemplateBodyPiece two = Mockito.mock(TemplateBodyPiece.class);
        Mockito.when(two.fillUp(Mockito.any(), Mockito.any()))
            .thenReturn(" dude");
        MatcherAssert.assertThat(
            new BasicTemplate(
                ImmutableList.of(),
                ImmutableList.of(one, two)
            )
                .fillUp(
                    ImmutableList.of(
                        Mockito.mock(Lexeme.class)
                    ),
                    new BasicVocabulary(ImmutableMap.of())
                ),
            CoreMatchers.equalTo("Hey dude")
        );
    }
}
