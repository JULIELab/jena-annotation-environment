package de.julielab.annoenv.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test");
		//$JUnit-BEGIN$
		suite.addTestSuite(MmaxDataConverterTest.class);
		//$JUnit-END$
		return suite;
	}

}
