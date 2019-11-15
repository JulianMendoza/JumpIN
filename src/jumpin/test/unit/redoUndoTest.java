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

public class redoUndoTest extends TestCase {
	private GameModel game;
	private Board board;
	private Rabbit rabbit;
	private Fox fox;
	private Fox fox2;
	private Fox fox3;
	private Fox fox4;

	@Override
	protected void setUp() {
		game = new GameModel();
		board = game.getBoard();
		fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox3 =new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		fox4 =new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
	}
	
	public void testIsEmpty() {
		assertFalse(board.getHistory().hasUndo());
		assertFalse(board.getHistory().hasRedo());
	}
	
	public void testRabbitUndo() throws IllegalMoveException {
		board.assignPiece(new Position (1,0), rabbit);
		Mushroom mushroom = new Mushroom();
		board.assignPiece(new Position(1,1), mushroom);
		board.selectPiece(new Position(1,0));
		board.movePiece(new Move(new Position(1,0), new Position(1,2)));
		board.undoMove();
		board.selectPiece(new Position(1,0));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		Mushroom mushroom2 = new Mushroom();
		board.assignPiece(new Position(1,3), mushroom2);
		board.selectPiece(new Position(1,0));
		board.movePiece(new Move(new Position(1,0), new Position(1,2)));
		board.selectPiece(new Position(1,2));
		board.movePiece(new Move(new Position(1,2), new Position(1,4)));
		board.undoMove();
		board.selectPiece(new Position(1,2));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
	}
	
	public void testRabbitRedo() throws IllegalMoveException {
		board.assignPiece(new Position (1,0), rabbit);
		Mushroom mushroom = new Mushroom();
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
		Mushroom mushroom2 = new Mushroom();
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
