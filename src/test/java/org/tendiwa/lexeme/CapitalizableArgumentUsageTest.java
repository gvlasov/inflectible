package org.tendiwa.lexeme;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 * @since 0.1
 */
public class CapitalizableArgumentUsageTest {
    @Test
    public void decapitalizesArgumentName() throws Exception {
        Assert.assertEquals(
            "dudeman",
            new CapitalizableArgumentUsage("Dudeman").declaredName()
        );
    }
}
