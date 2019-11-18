package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.move.Move;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.Position;
import junit.framework.TestCase;

/**
 * Tests for undo and redo methods
 * 
 * @author John
 * @documentation Cameron
 */

public class redoUndoTest extends TestCase {
	private GameModel game;
	private Board board;
	private Rabbit rabbit;
	private Fox fox, fox2;
	private Mushroom mushroom, mushroom2;

	@Override
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
		board = game.getBoard();
		fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		mushroom = new Mushroom();
		mushroom2 = new Mushroom();
	}
	
	public void testIsEmpty() {
		assertFalse(board.getHistory().hasUndo());
		assertFalse(board.getHistory().hasRedo());
	}
	
	/**
	 * Test for correct position of rabbits after undo
	 * @throws IllegalMoveException
	 */
	public void testRabbitUndo() throws IllegalMoveException {
		board.assignPiece(new Position (1,0), rabbit);
		board.assignPiece(new Position(1,1), mushroom);
		board.selectPiece(new Position(1,0));
		board.movePiece(new Move(new Position(1,0), new Position(1,2)));
		board.undoMove();
		board.selectPiece(new Position(1,0));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.assignPiece(new Position(1,3), mushroom2);
		board.selectPiece(new Position(1,0));
		board.movePiece(new Move(new Position(1,0), new Position(1,2)));
		board.selectPiece(new Position(1,2));
		board.movePiece(new Move(new Position(1,2), new Position(1,4)));
		board.undoMove();
		board.selectPiece(new Position(1,2));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
	}
	
	/**
	 * Test correct position of rabbits after redo
	 * @throws IllegalMoveException
	 */
	public void testRabbitRedo() throws IllegalMoveException {
		board.assignPiece(new Position (1,0), rabbit);
		board.assignPiece(new Position(1,1), mushroom);
		board.selectPiece(new Position(1,0));
		board.movePiece(new Move(new Position(1,0), new Position(1,2)));
		board.undoMove();
		board.selectPiece(new Position(1,0));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.redoMove();
		board.selectPiece(new Position(1,2));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.undoMove();
		board.assignPiece(new Position (1,3), mushroom2);
		board.selectPiece(new Position(1,0));
		board.movePiece(new Move(new Position(1,0), new Position(1,2)));
		board.selectPiece(new Position(1,2));
		board.movePiece(new Move(new Position(1,2), new Position(1,4)));
		board.undoMove();
		board.undoMove();
		board.redoMove();
		board.redoMove();
		board.selectPiece(new Position(1,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
	}
	
	/**
	 * Test correct fox position after undo
	 * @throws IllegalMoveException
	 */
	public void testFoxUndo() throws IllegalMoveException {
		board.assignPiece(new Position(1,0), fox2);
		board.assignPiece(new Position(1,1), fox);
		board.selectPiece(new Position(1,1));
		board.movePiece(new Move(new Position(1,1), new Position(1,4)));
		board.selectPiece(new Position (1,4));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.undoMove();
		board.selectPiece(new Position (1,1));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.movePiece(new Move(new Position(1,1), new Position(1,4)));
		board.selectPiece(new Position (1,4));
		board.movePiece(new Move(new Position(1,4), new Position(1,2)));
		board.selectPiece(new Position (1,2));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.undoMove();
		board.undoMove();
		board.selectPiece(new Position (1,1));
		assertTrue(board.getSelectedPiece() instanceof Fox);
	}
	
	/**
	 * Test correct fox position after redo
	 * @throws IllegalMoveException
	 */
	public void testFoxRedo() throws IllegalMoveException {
		board.assignPiece(new Position(1,0), fox2);
		board.assignPiece(new Position(1,1), fox);
		board.selectPiece(new Position(1,1));
		board.movePiece(new Move(new Position(1,1), new Position(1,4)));
		board.selectPiece(new Position (1,4));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.undoMove();
		board.redoMove();
		board.selectPiece(new Position (1,4));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.movePiece(new Move(new Position(1,4), new Position(1,3)));
		board.selectPiece(new Position (1,3));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.undoMove();
		board.undoMove();
		board.redoMove();
		board.redoMove();
		board.selectPiece(new Position (1,3));
		assertTrue(board.getSelectedPiece() instanceof Fox);
	}
}
