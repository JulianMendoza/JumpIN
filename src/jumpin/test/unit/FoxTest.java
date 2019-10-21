package jumpin.test.unit;

import junit.framework.*;
import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.exception.NoTileException;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.Position;

/**
 * Test for fox moves and placements
 * 
 * @author Julian
 *
 */
public class FoxTest extends TestCase {
	private GameModel game;
	private Board board;

	protected void setUp() {
		game = new GameModel();
		board = game.getBoard();
	}

	public void testVerticalFox() throws NoTileException, NoPieceException, IllegalMoveException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 0)) instanceof Fox);
		assertFalse(board.selectPiece(new Position(1, 0)).allowsDirection(Direction.EAST));
		assertFalse(board.selectPiece(new Position(1, 0)).allowsDirection(Direction.WEST));
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

	public void testHorizontalFox() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceConstants.FOX_ID_1);
		board.assignPiece(new Position(0, 1), fox2);
		board.assignPiece(new Position(1, 1), fox);
		assertTrue(board.selectPiece(new Position(0, 1)) instanceof Fox);
		assertTrue(board.selectPiece(new Position(1, 1)) instanceof Fox);
		assertFalse(board.selectPiece(new Position(1, 1)).allowsDirection(Direction.NORTH));
		assertFalse(board.selectPiece(new Position(1, 1)).allowsDirection(Direction.SOUTH));
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

	public void testFoxCollisonMushroom() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
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

	public void testFoxCollisonRabbit() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		Rabbit rabbit = new Rabbit(PieceConstants.RABBIT_ID_1);
		board.assignPiece(new Position(1, 2), rabbit);
		for (int i = 1; i <= 3; i++) {
			try {
				game.movePiece(new Position(1, 1), Direction.SOUTH, i);
			} catch (IllegalMoveException e) {
				assertEquals("Illegal move for Fox", e.getMessage());
			}
		}
	}

	public void testFoxCollisonFox() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		Fox fox3 = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox4 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
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

	public void testFoxCollisonRabbitHole() throws NoPieceException, IllegalMoveException, NoTileException {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
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
