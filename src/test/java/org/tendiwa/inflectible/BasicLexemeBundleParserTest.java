package org.tendiwa.inflectible;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * @since 0.1
 */
public final class BasicLexemeBundleParserTest {
    @Test(expected = UncheckedIOException.class)
    public void failsWithBadInputStream() throws Exception {
        InputStream failing = Mockito.mock(InputStream.class);
        Mockito.when(failing.read()).thenThrow(IOException.class);
        new BasicLexemeBundleParser(failing);
    }
}
