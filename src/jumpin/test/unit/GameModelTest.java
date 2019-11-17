package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.constants.StateOfGame;
import jumpin.model.util.Position;
import junit.framework.*;

/**
 * Test for the GameModel and level generator
 * 
 * @author Julian
 * @documentation Cameron Davis
 */
public class GameModelTest extends TestCase {
	private GameModel game;

	@Override
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
	}

	/**
	 * Test for correct rabbits to win and game state before creating level
	 */
	public void testGameState() {
		assertEquals(-1, game.getGameState().getNumToWin());
		assertEquals(null, game.getGameState().getState());
	}

	/**
	 * 
	 * Test for correct rabbits to win and game state after generating Level 1
	 */
	public void testLevel1() {
		game.getGenerator().createLevel1();
		assertEquals(3, game.getGameState().getNumToWin());
		assertEquals(StateOfGame.IN_PROGRESS, game.getGameState().getState());
	}


	/**
	 * Test for correct position of rabbit holes
	 */
	public void testRabbitHoles() {
		assertTrue(game.getBoard().getTile(new Position(0, 0)) instanceof RabbitHole);
		assertTrue(game.getBoard().getTile(new Position(4, 0)) instanceof RabbitHole);
		assertTrue(game.getBoard().getTile(new Position(2, 2)) instanceof RabbitHole);
		assertTrue(game.getBoard().getTile(new Position(0, 4)) instanceof RabbitHole);
		assertTrue(game.getBoard().getTile(new Position(4, 4)) instanceof RabbitHole);
	}

}
