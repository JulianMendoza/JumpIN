package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.util.Position;
import junit.framework.*;

/**
 * Test case for mushroom placement and movement
 * 
 * @author Julian
 *
 */
public class MushroomTest extends TestCase {
	private GameModel game;
	private Board board;
	private Mushroom mushroom;

	@Override
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
		board = game.getBoard();
		mushroom = new Mushroom();
	}

	/**
	 * Test if a mushroom exists
	 * 
	 * @throws NoTileException if no mushroom exists on the tile
	 */
	public void testMushroomExists() {
		board.assignPiece(new Position(0, 1), mushroom);
		board.selectPiece(new Position(0, 1));
		assertTrue(board.getSelectedPiece() instanceof Mushroom);
	}

}