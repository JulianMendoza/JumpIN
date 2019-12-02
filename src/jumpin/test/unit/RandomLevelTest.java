package jumpin.test.unit;

import John.GenerateLevel;
import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.file.LevelGenerator;
import junit.framework.TestCase;

/**
 * Test class for random level generator
 * 
 * @author Cameron
 */

public class RandomLevelTest extends TestCase {

	private GenerateLevel levelGenerator;
	private GameModel theModel;
	private Board theBoard;

	@Override
	protected void setUp() throws CloneNotSupportedException {
		theModel = new GameModel();
		theBoard = theModel.getBoard();
		levelGenerator = new GenerateLevel(theBoard, theModel.getGameState());
	}


	/**
	 * Test if randomly generated levels are solvable
	 * 
	 * @throws CloneNotSupportedException
	 */
	public void testSolvableRandomLevel() throws CloneNotSupportedException {
		Board randomizedBoard = levelGenerator.createLevel();

		while (true) {
			randomizedBoard.computeSolution(6);
			if (randomizedBoard.getSolution() != null)
				break;
			randomizedBoard = levelGenerator.createLevel();
		}
	}

}
