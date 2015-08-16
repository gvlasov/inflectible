package org.tendiwa.inflectible;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * Unit tests for {@link BasicLexemeBundleParser}.
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public final class BasicLexemeBundleParserTest {
    /**
     * {@link BasicLexemeBundleParser} can fail if an input stream passed to
     * it throws {@link IOException}.
     * @throws Exception If fails
     */
    @Test(expected = UncheckedIOException.class)
    public void failsWithBadInputStream() throws Exception {
        InputStream failing = Mockito.mock(InputStream.class);
        Mockito.when(failing.read()).thenThrow(new IOException());
        new BasicLexemeBundleParser(failing);
    }
}
