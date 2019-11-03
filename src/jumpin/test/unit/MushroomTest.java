package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.exception.NoTileException;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.util.Position;
import junit.framework.*;


/**
 * Test case for mushroom placement and movement
 * 
 * @author Julian
 */
public class MushroomTest extends TestCase {
	private GameModel game;
	private Board board;
	private Mushroom mushroom;

	@Override
	protected void setUp() {
		game = new GameModel();
		board = game.getBoard();
		mushroom = new Mushroom();
	}

	/**
	 * Test if a mushroom exists
	 * 
	 * @throws NoTileException if no mushroom exists on the tile
	 */
	public void testMushroomExists() throws NoTileException {
		board.assignPiece(new Position(0, 1), mushroom);
		assertTrue(board.selectPiece(new Position(0, 1)) instanceof Mushroom);
	}

	/**
	 * Test if mushroom can move in any direction
	 * 
	 * @throws NoPieceException     if there is no mushroom on the board
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testMushroomMove() throws NoPieceException, NoTileException, IllegalMoveException {
		board.assignPiece(new Position(0, 1), mushroom);
		try {
			game.movePiece(new Position(0, 1), Direction.WEST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move West", e.getMessage());
		}
		try {
			game.movePiece(new Position(0, 1), Direction.EAST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move East", e.getMessage());
		}
		try {
			game.movePiece(new Position(0, 1), Direction.NORTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move North", e.getMessage());
		}
		try {
			game.movePiece(new Position(0, 1), Direction.SOUTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move South", e.getMessage());
		}
	}

	/**
	 * Tests the edge exceptions for the mushroom Assures that "Mushroom cannot move
	 * North/South/East/West off the board" is not the error message
	 * 
	 * @throws NoPieceException     if there is no mushroom on the board
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testMushroomEdge() throws NoPieceException, NoTileException, IllegalMoveException {
		board.assignPiece(new Position(1, 0), mushroom);
		try {
			game.movePiece(new Position(1, 0), Direction.NORTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move North", e.getMessage());
		}
		board.assignPiece(new Position(0, 1), mushroom);
		try {
			game.movePiece(new Position(0, 1), Direction.WEST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move West", e.getMessage());
		}
		board.assignPiece(new Position(4, 1), mushroom);
		try {
			game.movePiece(new Position(4, 1), Direction.EAST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move East", e.getMessage());
		}
		board.assignPiece(new Position(4, 3), mushroom);
		try {
			game.movePiece(new Position(4, 3), Direction.SOUTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Mushroom is not allowed to move South", e.getMessage());
		}
	}
}