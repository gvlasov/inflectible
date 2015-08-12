package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.lexeme.antlr.TextBundleLexer;

/**
 * @since 0.1
 */
public final class ParsedSinglePartPlaceholderTest {
    @Test
    public void capitalizesWordWhenNecessary() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormInsertionResult("[Dude]", "programmer"),
            CoreMatchers.equalTo("Programmer")
        );
    }

    @Test
    public void doesntCapitalizeWhenUnnecessary() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormInsertionResult("[dude]", "director"),
            CoreMatchers.equalTo("director")
        );
    }

    private String wordFormInsertionResult(
        String placeholderMarkup,
        String wordForm
    ) {

        Lexeme lexeme = Mockito.mock(Lexeme.class);
        Mockito.when(lexeme.baseForm()).thenReturn(wordForm);
        DeclaredArguments declared = Mockito.mock(DeclaredArguments.class);
        Mockito.when(declared.index(Mockito.anyString())).thenReturn(0);
        return new ParsedSinglePartPlaceholder(
            new TextBundleParserFactory().createInMode(
                TextBundleLexer.LINE_CONTENT,
                placeholderMarkup
            )
                .base_form_placeholder()
        ).fillUp(
            new ActualArguments(
                declared,
                ImmutableList.of(lexeme)
            )
        );
    }
}
