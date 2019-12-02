package jumpin.test.unit;

import jumpin.model.board.Board;
import jumpin.model.board.generator.GenerateLevel;
import junit.framework.TestCase;

/**
 * Test class for random level generator
 * 
 * @author Cameron
 */

public class RandomLevelTest extends TestCase {
	
	private Board randomBoard;

	/**
	 * Test for solvable random levels after six moves
	 * 
	 * @throws CloneNotSupportedException
	 */
	public void testSolvableRandomLevel() throws CloneNotSupportedException {
		randomBoard = GenerateLevel.createLevel().getBoard();

		while (true) {
			randomBoard.computeSolution(6);
			if (randomBoard.getSolution() != null)
				break;
			randomBoard = GenerateLevel.createLevel().getBoard();
		}
	}

}
