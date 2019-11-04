package jumpin.test.unit;

import junit.framework.*;

/**
 * Test Suite for all test cases JUnit 3
 * 
 * @author Julian
 *
 */
public class AllTests extends TestSuite {
	/**
	 * The main tests for the game
	 * 
	 * @return suite
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for JumpIN application");
		suite.addTest(new TestSuite(GameModelTest.class));
		suite.addTest(new TestSuite(MushroomTest.class));
		suite.addTest(new TestSuite(RabbitTest.class));
		suite.addTest(new TestSuite(FoxLogicTest.class));
		suite.addTest(new TestSuite(FoxMoveSetTest.class));
		suite.addTest(new TestSuite(RabbitMoveSetTest.class));
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
