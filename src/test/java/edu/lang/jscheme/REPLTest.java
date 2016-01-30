package edu.lang.jscheme;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class REPLTest extends TestCase {
    public REPLTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(REPLTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }
}
