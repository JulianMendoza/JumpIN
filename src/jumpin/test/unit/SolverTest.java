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
	private GameState gameState;
	private LevelGenerator generator;

	@Override
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
		board = game.getBoard();
		gameState = new GameState();
		fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox3 =new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		fox4 =new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
	}
	
	public void testSolverOnly() throws InterruptedException, CloneNotSupportedException, IllegalMoveException{

		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(3,2),rabbit);
		board.assignPiece(new Position(3,3),fox3);
		board.assignPiece(new Position(4,3),fox4);
		board.assignPiece(new Position(2,4), mushroom);
		board.assignPiece(new Position(1,4), mushroom2);
		board.computeSolution(3);
		board.solve();
		board.selectPiece(new Position (3,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		board.solve();
		board.selectPiece(new Position (0,4));
		assertTrue(board.getSelectedPiece() instanceof Rabbit);
		assertTrue(game.getGameState().equals(StateOfGame.WON));
	}
	
	public void testSolverWithUserInput() throws InterruptedException, CloneNotSupportedException, IllegalMoveException{
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(3,2),rabbit);
		board.assignPiece(new Position(3,3),fox3);
		board.assignPiece(new Position(4,3),fox4);
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
		//assertTrue(game.getGameState().equals(StateOfGame.IN_PROGRESS));
	}
}

	
