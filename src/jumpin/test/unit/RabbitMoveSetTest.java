package jumpin.test.unit;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.logic.RabbitLogic;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.Position;
import jumpin.view.GameView;
import junit.framework.TestCase;
/**
 * 
 * @author JOHN
 *
 */
public class RabbitMoveSetTest extends TestCase{
	private GameModel game;
	private Board board;
	private Fox fox;
	private Fox fox2;
	private Fox fox3;
	private Fox fox4;
	private Mushroom mushroom;
	private List<MoveSet> moves;
	private GameView view;
	private Rabbit rabbit;
	private MoveSet moveset;

	@Override
	protected void setUp() {	
		game = new GameModel();
		board = game.getBoard();
		view = new GameView(game);
		fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox3=new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		fox4=new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		mushroom = new Mushroom();
		moves=new ArrayList<MoveSet>();
		moveset=new MoveSet();
	}
	/**
	 * Test if rabbit can move with no pieces near it
	 */
	public void testRabbitMovesNone() {
		board.assignPiece(new Position(1,2), fox3);
		board.assignPiece(new Position(1,1), fox4);
		board.assignPiece(new Position(1,4), rabbit);
		board.selectPiece(new Position(1,4));
		moves = RabbitLogic.findRabbitMoves(board);
		assertTrue(moves.isEmpty());
		
	}
	/**
	 * Test rabbit movement over mushroom
	 * 
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitOverMushroom() throws IllegalMoveException{
		board.assignPiece(new Position(1,2), mushroom);
		board.assignPiece(new Position(2,1), mushroom);
		board.assignPiece(new Position(1,1), rabbit);
		board.selectPiece(new Position(1,1));
		moves = RabbitLogic.findRabbitMoves(board);
		assertTrue(moves.size() == 2);
		moveset.add(new Move(new Position(1,1),new Position(1,3)));
		assertTrue(moves.contains(moveset));
		moveset=new MoveSet();
		moveset.add(new Move(new Position(1,1),new Position(3,1)));
		assertTrue(moves.contains(moveset));
	}
	
	/**
	 * Test rabbit movement over different objects
	 * 
	 * @throws IllegalMoveException if the piece cannot move in a specific behavior
	 */
	public void testRabbitOverObj() throws IllegalMoveException{
		board.assignPiece(new Position(3,2), fox);
		board.assignPiece(new Position(4,2), fox2);
		board.assignPiece(new Position(4,3), mushroom);
		board.assignPiece(new Position(3,1), rabbit);
		board.selectPiece(new Position(3,1));
		moves = RabbitLogic.findRabbitMoves(board);
		assertTrue(moves.size() == 1);
		moveset.add(new Move(new Position(3,1),new Position(3,3)));
		assertTrue(moves.contains(moveset));
		moveset=new MoveSet();
		moveset.add(new Move(new Position(3,1),new Position(3,3)));
		moveset.add(new Move(new Position(3,3),new Position(5,3)));
		assertTrue(moves.contains(moveset));
	}
	
}