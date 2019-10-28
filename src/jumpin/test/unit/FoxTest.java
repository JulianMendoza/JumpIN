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
 * Test for fox moves and placements
 * 
 * @author Julian
 *
 */
public class FoxTest extends TestCase {
	private GameModel game;
	private Board board;

	@Override
	protected void setUp() {
		game = new GameModel();
		board = game.getBoard();
	}

	/**
	 * Tests the behavior of a fox placed in a vertical orientation
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Fox at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testVerticalFox() throws NoTileException, NoPieceException, IllegalMoveException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 0)) instanceof Fox);
		try {
			game.movePiece(new Position(1, 1), Direction.WEST, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox is not allowed to move West", e.getMessage());
		}
		try {
			game.movePiece(new Position(1, 1), Direction.EAST, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox is not allowed to move East", e.getMessage());
		}
		game.movePiece(new Position(1, 1), Direction.SOUTH, 1);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 2)) instanceof Fox);
		game.movePiece(new Position(1, 1), Direction.SOUTH, 2);
		assertTrue(board.selectPiece(new Position(1, 3)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 4)) instanceof Fox);
		game.movePiece(new Position(1, 4), Direction.NORTH, 3);
		assertTrue(board.selectPiece(new Position(1, 0)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
	}

	/**
	 * Tests the behavior of a fox placed in a horizontal orientation
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Fox at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testHorizontalFox() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_1);
		board.assignPiece(new Position(0, 1), fox2);
		board.assignPiece(new Position(1, 1), fox);
		assertTrue(board.selectPiece(new Position(0, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
		try {
			game.movePiece(new Position(1, 1), Direction.NORTH, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox is not allowed to move North", e.getMessage());
		}
		try {
			game.movePiece(new Position(1, 1), Direction.SOUTH, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox is not allowed to move South", e.getMessage());
		}
		game.movePiece(new Position(1, 1), Direction.EAST, 1);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(2, 1)) instanceof Fox);
		game.movePiece(new Position(1, 1), Direction.EAST, 2);
		assertTrue(board.selectPiece(new Position(3, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(4, 1)) instanceof Fox);
		game.movePiece(new Position(4, 1), Direction.WEST, 3);
		assertTrue(board.selectPiece(new Position(0, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
	}

	/**
	 * Tests the edge exceptions for the foxes Assures that "Illegal move for Fox"
	 * is not the error message
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Fox at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testFoxEdgeMoves() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		try {
			game.movePiece(new Position(1, 0), Direction.NORTH, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox cannot move North off the board", e.getMessage());
		}
		/*
		 * try { game.movePiece(new Position(1, 1), Direction.NORTH, 1);
		 * }catch(IllegalMoveException e) {
		 * assertEquals("Fox cannot move North off the board" ,e.getMessage()); }
		 */
		game.movePiece(new Position(1, 0), Direction.SOUTH, 3);
		try {
			game.movePiece(new Position(1, 4), Direction.SOUTH, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox cannot move South off the board", e.getMessage());
		}
		/*
		 * try { game.movePiece(new Position(1, 3), Direction.SOUTH, 1);
		 * }catch(IllegalMoveException e) {
		 * assertEquals("Fox cannot move South off the board" ,e.getMessage()); }
		 */
		Fox fox3 = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox fox4 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		board.assignPiece(new Position(0, 1), fox3);
		board.assignPiece(new Position(1, 1), fox4);
		try {
			game.movePiece(new Position(0, 1), Direction.WEST, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox cannot move West off the board", e.getMessage());
		}
		/*
		 * try { game.movePiece(new Position(1, 1), Direction.WEST, 1);
		 * }catch(IllegalMoveException e) {
		 * assertEquals("Fox cannot move West off the board" ,e.getMessage()); }
		 */
		game.movePiece(new Position(0, 1), Direction.EAST, 3);
		try {
			game.movePiece(new Position(1, 4), Direction.EAST, 1);
		} catch (IllegalMoveException e) {
			assertEquals("Fox is not allowed to move East", e.getMessage());
		}
		/*
		 * try { game.movePiece(new Position(1, 3), Direction.EAST, 1);
		 * }catch(IllegalMoveException e) {
		 * assertEquals("Fox cannot move East off the board" ,e.getMessage()); }
		 */
	}

	/**
	 * Test for the Fox interaction with a Mushroom
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Fox at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testFoxCollisonMushroom() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		Mushroom mushroom = new Mushroom();
		board.assignPiece(new Position(1, 2), mushroom);
		for (int i = 1; i <= 3; i++) {
			try {
				game.movePiece(new Position(1, 1), Direction.SOUTH, i);
			} catch (IllegalMoveException e) {
				assertEquals("Illegal move for Fox", e.getMessage());
			}
		}
	}

	/**
	 * Test for the Fox interaction with a Rabbit
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Fox at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testFoxCollisonRabbit() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(1, 2), rabbit);
		for (int i = 1; i <= 3; i++) {
			try {
				game.movePiece(new Position(1, 1), Direction.SOUTH, i);
			} catch (IllegalMoveException e) {
				assertEquals("Illegal move for Fox", e.getMessage());
			}
		}
	}

	/**
	 * Test for the Fox interaction with another Fox
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Fox at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testFoxCollisonFox() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		Fox fox3 = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_2);
		Fox fox4 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_2);
		board.assignPiece(new Position(1, 2), fox3);
		board.assignPiece(new Position(1, 3), fox4);
		for (int i = 1; i <= 3; i++) {
			try {
				game.movePiece(new Position(1, 1), Direction.SOUTH, i);
			} catch (IllegalMoveException e) {
				assertEquals("Illegal move for Fox", e.getMessage());
			}
		}
	}

	/**
	 * Test for the Fox interaction with a RabbitHole
	 * 
	 * @throws NoTileException      if no tile exists at the specific position
	 * @throws NoPieceException     if there is no Fox at the position
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testFoxCollisonRabbitHole() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(2, 0), fox2);
		board.assignPiece(new Position(2, 1), fox);
		for (int i = 1; i <= 3; i++) {
			try {
				game.movePiece(new Position(2, 1), Direction.SOUTH, i);
			} catch (IllegalMoveException e) {
				assertEquals("Illegal move for Fox", e.getMessage());
			}
		}
	}
}