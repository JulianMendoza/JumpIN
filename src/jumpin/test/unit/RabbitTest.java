package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;
import jumpin.model.structures.move.Move;
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
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
		board = game.getBoard();
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
	}

	/**
	 * Test to see if a rabbit exists
	 * 
	 */
	public void testRabbitExists() {
		board.assignPiece(new Position(1, 0), rabbit);
		board.selectPiece(new Position(1, 0));
		assertTrue( board.getSelectedPiece() instanceof Rabbit);
	}

	/**
	 * Test to see if a rabbit can move with no pieces next to it
	 * 
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitMoveAlone() throws IllegalMoveException {
		board.assignPiece(new Position(1, 1), rabbit);
		board.selectPiece(new Position (1,1));
		try {
			board.movePiece(new Move(new Position(1, 1), new Position(1,0)));
		} catch (IllegalMoveException e) {
			assertEquals("Cannot perform illegal move!", e.getMessage());
		}
		try {
			board.movePiece(new Move(new Position(1, 1), new Position(1,2)));
		} catch (IllegalMoveException e) {
			assertEquals("Cannot perform illegal move!", e.getMessage());
		}
		try {
			board.movePiece(new Move(new Position(1, 1), new Position(2,1)));
		} catch (IllegalMoveException e) {
			assertEquals("Cannot perform illegal move!", e.getMessage());
		}
		try {
			board.movePiece(new Move(new Position(1, 1), new Position(0,1)));
		} catch (IllegalMoveException e) {
			assertEquals("Cannot perform illegal move!", e.getMessage());
		}
	}

	/**
	 * Test to determine if a rabbit behaves as intended with mushrooms
	 * 
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitMushroom() throws IllegalMoveException {
		board.assignPiece(new Position(1, 0), rabbit);
		Mushroom mushroom = new Mushroom();
		board.assignPiece(new Position(1, 1), mushroom);
		board.selectPiece(new Position (1,0));
		board.movePiece(new Move(new Position(1, 0), new Position(1,2)));
		board.deselectPiece();
		board.selectPiece(new Position(1,2));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1,0));
		assertFalse(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1,2));
		board.movePiece(new Move(new Position(1, 2), new Position(1,0)));
		board.assignPiece(new Position(1, 2), mushroom);
		board.deselectPiece();
		board.selectPiece(new Position(1,0));
		board.movePiece(new Move(new Position(1, 0), new Position(1,3)));
		board.selectPiece(new Position(1, 3));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1, 0));
		assertFalse(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1, 3));
		board.movePiece(new Move(new Position(1, 3), new Position(1,0)));
		board.assignPiece(new Position(1, 3), mushroom);
		board.deselectPiece();
		board.selectPiece(new Position(1, 0));
		board.movePiece(new Move(new Position(1, 0), new Position(1,4)));
		board.deselectPiece();
		board.selectPiece(new Position(1, 4));
		assertTrue( board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1, 0));
		assertFalse(board.getSelectedPiece() instanceof Rabbit);
	}

	/**
	 * Test to determine if a rabbit behaves as intended with foxes
	 * 
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitFoxes() throws IllegalMoveException {
		board.assignPiece(new Position(1, 0), rabbit);
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 1), fox);
		board.assignPiece(new Position(1, 2), fox2);
		board.selectPiece(new Position (1,0));
		board.movePiece(new Move(new Position(1, 0), new Position(1,3)));
		board.selectPiece(new Position(1, 3));
		assertTrue( board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1, 0));
		assertFalse(board.getSelectedPiece() instanceof Rabbit);
	}

	/**
	 * Test to determine if a rabbit behaves as intended with other rabbits
	 * 
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitRabbit() throws IllegalMoveException {
		board.assignPiece(new Position(1, 0), rabbit);
		board.assignPiece(new Position(1, 1), rabbit);
		board.selectPiece(new Position (1,0));
		board.movePiece(new Move(new Position(1, 0), new Position(1,2)));
		board.deselectPiece();
		board.selectPiece(new Position(1, 2));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1, 0));
		assertFalse(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position (1,2));
		board.movePiece(new Move(new Position(1, 2), new Position(1,0)));
		board.assignPiece(new Position(1, 2), rabbit);
		board.deselectPiece();
		board.selectPiece(new Position (1,0));
		board.movePiece(new Move(new Position(1, 0),new Position(1,3)));
	    board.deselectPiece();
		board.selectPiece(new Position(1, 3));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1, 0));
		assertFalse(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position (1,3));
		board.movePiece(new Move(new Position(1, 3), new Position(1,0)));
		board.assignPiece(new Position(1, 3), rabbit);
		board.deselectPiece();
		board.selectPiece(new Position (1,0));
		board.movePiece(new Move(new Position(1, 0),new Position(1,4)));
		board.deselectPiece();
		board.selectPiece(new Position(1, 4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.deselectPiece();
		board.selectPiece(new Position(1, 0));
		assertFalse(board.getSelectedPiece() instanceof Rabbit);

	}
}
