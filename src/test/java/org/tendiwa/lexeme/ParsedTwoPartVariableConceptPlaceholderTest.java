package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.lexeme.antlr.TextBundleLexer;
import org.tendiwa.lexeme.implementations.English;

public final class ParsedTwoPartVariableConceptPlaceholderTest {
    @Test
    @Ignore
    public void capitalizesWordWhenNecessary() throws Exception {
        MatcherAssert.assertThat(
            this.wordFormInsertionResult("[dude][Plur]", "director"),
            CoreMatchers.equalTo("director")
        );
    }

    @Test
    public void doesntCapitalizeWordWhenUnnecessary() throws Exception {

    }

    private String wordFormInsertionResult(
        String placeholderMarkup,
        String wordForm
    ) {
        DeclaredArguments declared = Mockito.mock(DeclaredArguments.class);
        Mockito.when(declared.index(Mockito.anyString())).thenReturn(0);
        return new ParsedTwoPartVariableConceptPlaceholder(
            new English().grammar(),
            new TextBundleParserFactory().createInMode(
                TextBundleLexer.LINE_CONTENT,
                placeholderMarkup
            )
                .placeholder()
        ).fillUp(
            new ActualArguments(
                declared,
                ImmutableList.of(new SingleFormLexeme(wordForm))
            )
        );
    }
}
