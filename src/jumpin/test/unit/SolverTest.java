package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.constants.StateOfGame;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.move.Move;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.LevelGenerator;
import jumpin.model.util.Position;
import junit.framework.TestCase;

public class SolverTest extends TestCase {
	private GameModel game;
	private Board board;
	private Rabbit rabbit;
	private Fox fox;
	private Fox fox2;
	private Fox fox3;
	private Fox fox4;


	@Override
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
		board = game.getBoard();
		fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		fox3 = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox4 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
	}
	
	/**
	 * Test for solver alone
	 * 
	 * @throws InterruptedException
	 * @throws CloneNotSupportedException
	 * @throws IllegalMoveException
	 */
	public void testSolverOnly() throws InterruptedException, CloneNotSupportedException, IllegalMoveException{
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
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
	 * Test for solver adapting to user input
	 * 
	 * @throws InterruptedException
	 * @throws CloneNotSupportedException
	 * @throws IllegalMoveException
	 */
	public void testSolverWithUserInput() throws InterruptedException, CloneNotSupportedException, IllegalMoveException{
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Mushroom mushroom3 = new Mushroom();
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		Rabbit rabbit2 = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(3,0),rabbit);
		board.assignPiece(new Position(0,2),rabbit2);
		board.assignPiece(new Position(1,3),fox3);
		board.assignPiece(new Position(1,2),fox4);
		board.assignPiece(new Position(2,1),fox);
		board.assignPiece(new Position(3,1),fox2);
		board.assignPiece(new Position(3,2), mushroom);
		board.assignPiece(new Position(3,3), mushroom2);
		board.assignPiece(new Position(2,4), mushroom3);
		board.computeSolution(4);
		board.solve();
		board.selectPiece(new Position (2,2));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.selectPiece(new Position (2,1));
		board.movePiece(new Move(new Position(2,1), new Position(0,1)));
		board.selectPiece(new Position (0,1));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.computeSolution(4);
		board.solve();
		board.selectPiece(new Position (1,4));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.solve();
		board.selectPiece(new Position (3,1));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.selectPiece(new Position (3,0));
		board.movePiece(new Move(new Position(3,0), new Position(3,4)));
		board.selectPiece(new Position (3,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.computeSolution(4);
		board.solve();
		board.selectPiece(new Position (0,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
	}
}

	
