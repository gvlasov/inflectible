package org.tendiwa.lexeme;

import com.google.common.collect.ImmutableList;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @since 0.1
 */
public final class ActualArgumentsTest {
    @Test
    public void findsArgumentByName() {
        Lexeme guest = Mockito.mock(Lexeme.class);
        DeclaredArguments declared = Mockito.mock(DeclaredArguments.class);
        Mockito.when(declared.index("guest")).thenReturn(0);
        MatcherAssert.assertThat(
            new ActualArguments(
                declared,
                ImmutableList.of(guest)
            ).argumentValue("guest"),
            CoreMatchers.equalTo(guest)
        );
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failsWhenNotEnoughValues() {
        Lexeme guest = Mockito.mock(Lexeme.class);
        DeclaredArguments declared = Mockito.mock(DeclaredArguments.class);
        Mockito.when(declared.index("guest")).thenReturn(0);
        Mockito.when(declared.index("host")).thenReturn(1);
        new ActualArguments(
            declared,
            ImmutableList.of(guest)
        ).argumentValue("host");
    }
}
