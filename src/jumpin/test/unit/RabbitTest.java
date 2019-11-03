package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.exception.NoTileException;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.Position;
import junit.framework.TestCase;

/**
 * Test for rabbit moves and placements
 * 
 * @author Julian
 */
public class RabbitTest extends TestCase {
	private GameModel game;
	private Board board;
	private Rabbit rabbit;

	@Override
	protected void setUp() {
		game = new GameModel();
		board = game.getBoard();
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
	}

	/**
	 * Test to see if a rabbit exists
	 * 
	 * @throws NoTileException  if no tile exists at the specific position
	 * @throws NoPieceException if there is no piece at the position
	 */
	public void testRabbitExists() throws NoTileException, NoPieceException {
		board.assignPiece(new Position(1, 0), rabbit);
		assertTrue(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
	}

	/**
	 * Test to see if a rabbit can move with no pieces next to it
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Rabbit at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitMoveAlone() throws NoPieceException, NoTileException, IllegalMoveException {
		board.assignPiece(new Position(1, 1), rabbit);
		try {
			game.movePiece(new Position(1, 1), Direction.WEST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Illegal move for Rabbit", e.getMessage());
		}
		try {
			game.movePiece(new Position(1, 1), Direction.EAST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Illegal move for Rabbit", e.getMessage());
		}
		try {
			game.movePiece(new Position(1, 1), Direction.NORTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Illegal move for Rabbit", e.getMessage());
		}
		try {
			game.movePiece(new Position(1, 1), Direction.SOUTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Illegal move for Rabbit", e.getMessage());
		}
	}

	/**
	 * Test to determine the correct error message when a rabbit attempts to jump
	 * off the board
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Rabbit at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitMoveEdge() throws NoPieceException, NoTileException, IllegalMoveException {
		board.assignPiece(new Position(0, 0), rabbit);
		try {
			game.movePiece(new Position(0, 0), Direction.NORTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Rabbit cannot move North off the board", e.getMessage());
		}
		board.assignPiece(new Position(0, 1), rabbit);
		try {
			game.movePiece(new Position(0, 1), Direction.WEST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Rabbit cannot move West off the board", e.getMessage());
		}
		board.assignPiece(new Position(4, 1), rabbit);
		try {
			game.movePiece(new Position(4, 1), Direction.EAST, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Rabbit cannot move East off the board", e.getMessage());
		}
		board.assignPiece(new Position(1, 4), rabbit);
		try {
			game.movePiece(new Position(1, 4), Direction.SOUTH, -1);
		} catch (IllegalMoveException e) {
			assertEquals("Rabbit cannot move South off the board", e.getMessage());
		}
	}

	/**
	 * Test to determine if a rabbit behaves as intended with mushrooms
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Rabbit at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitMushroom() throws NoPieceException, NoTileException, IllegalMoveException {
		board.assignPiece(new Position(1, 0), rabbit);
		Mushroom mushroom = new Mushroom();
		board.assignPiece(new Position(1, 1), mushroom);
		game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		assertTrue(board.selectPiece(new Position(1, 2)) instanceof Rabbit);
		assertFalse(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
		game.movePiece(new Position(1, 2), Direction.NORTH, -1);
		board.assignPiece(new Position(1, 2), mushroom);
		game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		assertTrue(board.selectPiece(new Position(1, 3)) instanceof Rabbit);
		assertFalse(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
		game.movePiece(new Position(1, 3), Direction.NORTH, -1);
		board.assignPiece(new Position(1, 3), mushroom);
		game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		assertTrue(board.selectPiece(new Position(1, 4)) instanceof Rabbit);
		assertFalse(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
		game.movePiece(new Position(1, 4), Direction.NORTH, -1);
		board.assignPiece(new Position(1, 4), mushroom);
		try {
			game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		} catch (IllegalMoveException e) {
			// assertEquals("Rabbit cannot move South off the board",e.getMessage());
			assertEquals("Illegal move for Rabbit", e.getMessage());
		}
	}

	/**
	 * Test to determine if a rabbit behaves as intended with foxes
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Rabbit at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitFoxes() throws NoPieceException, NoTileException, IllegalMoveException {
		board.assignPiece(new Position(1, 0), rabbit);
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 1), fox);
		board.assignPiece(new Position(1, 2), fox2);
		game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		assertTrue(board.selectPiece(new Position(1, 3)) instanceof Rabbit);
		assertFalse(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
		game.movePiece(new Position(1, 3), Direction.NORTH, -1);
		board.assignPiece(new Position(1, 3), fox);
		board.assignPiece(new Position(1, 4), fox2);
		try {
			game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		} catch (IllegalMoveException e) {
			// assertEquals("Rabbit cannot move South off the board",e.getMessage());
			assertEquals("Illegal move for Rabbit", e.getMessage());
		}
	}

	/**
	 * Test to determine if a rabbit behaves as intended with other rabbits
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Rabbit at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitRabbit() throws NoPieceException, NoTileException, IllegalMoveException {
		board.assignPiece(new Position(1, 0), rabbit);
		board.assignPiece(new Position(1, 1), rabbit);
		game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		assertTrue(board.selectPiece(new Position(1, 2)) instanceof Rabbit);
		assertFalse(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
		game.movePiece(new Position(1, 2), Direction.NORTH, -1);
		board.assignPiece(new Position(1, 2), rabbit);
		game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		assertTrue(board.selectPiece(new Position(1, 3)) instanceof Rabbit);
		assertFalse(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
		game.movePiece(new Position(1, 3), Direction.NORTH, -1);
		board.assignPiece(new Position(1, 3), rabbit);
		game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		assertTrue(board.selectPiece(new Position(1, 4)) instanceof Rabbit);
		assertFalse(board.selectPiece(new Position(1, 0)) instanceof Rabbit);
		game.movePiece(new Position(1, 4), Direction.NORTH, -1);
		board.assignPiece(new Position(1, 4), rabbit);
		try {
			game.movePiece(new Position(1, 0), Direction.SOUTH, -1);
		} catch (IllegalMoveException e) {
			// assertEquals("Rabbit cannot move South off the board",e.getMessage());
			assertEquals("Illegal move for Rabbit", e.getMessage());
		}
	}
}
