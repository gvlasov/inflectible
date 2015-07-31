package org.tendiwa.lexeme;

import java.util.Collections;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;
import org.tendiwa.rocollections.WrappingReadOnlyList;

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
                new WrappingReadOnlyList<>(
                    Collections.singletonList(guest)
                )
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
            new WrappingReadOnlyList<>(
                Collections.singletonList(guest)
            )
        ).argumentValue("host");
    }
}
