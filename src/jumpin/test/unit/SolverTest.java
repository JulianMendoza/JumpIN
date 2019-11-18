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
 * Test for solver method
 * 
 * @author John
 * @documentation Cameron
 */
public class SolverTest extends TestCase {
	private GameModel game;
	private Board board;
	private Rabbit rabbit;
	private Fox fox, fox2;
	private Mushroom mushroom, mushroom2;

	@Override
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
		board = game.getBoard();
		fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		fox2 = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		mushroom = new Mushroom();
		mushroom2 = new Mushroom();
	}
	
	/**
	 * Test correct rabbit positioning after invoking solver
	 * 
	 * @throws InterruptedException
	 * @throws CloneNotSupportedException
	 * @throws IllegalMoveException
	 */
	public void testSolverOnly() throws InterruptedException, CloneNotSupportedException, IllegalMoveException{
		board.assignPiece(new Position(3,2),rabbit);
		board.assignPiece(new Position(3,3),fox);
		board.assignPiece(new Position(4,3),fox2);
		board.assignPiece(new Position(2,4), mushroom);
		board.assignPiece(new Position(1,4), mushroom2);
		board.computeSolution(3);
		board.solve();
		board.selectPiece(new Position (3,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.solve();
		board.selectPiece(new Position (0,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
	}
	
	/**
	 * Test correct positioning of rabbits after both using the solver and moving pieces
	 * 
	 * @throws InterruptedException
	 * @throws CloneNotSupportedException
	 * @throws IllegalMoveException
	 */
	public void testSolverWithUserInput() throws InterruptedException, CloneNotSupportedException, IllegalMoveException{
		board.assignPiece(new Position(3,2),rabbit);
		board.assignPiece(new Position(3,3),fox);
		board.assignPiece(new Position(4,3),fox2);
		board.assignPiece(new Position(2,4), mushroom);
		board.assignPiece(new Position(1,4), mushroom2);
		board.computeSolution(3);
		board.solve();
		board.selectPiece(new Position (3,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.selectPiece(new Position (3,4));
		board.movePiece(new Move(new Position(3,4), new Position(3,2)));
		board.selectPiece(new Position (3,2));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.computeSolution(3);
		board.solve();
		board.selectPiece(new Position (0,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
	}
}

	
