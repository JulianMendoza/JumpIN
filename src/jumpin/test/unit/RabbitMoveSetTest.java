package jumpin.test.unit;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.logic.FoxLogic;
import jumpin.model.logic.RabbitLogic;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.Position;
import jumpin.view.GameView;
import junit.framework.TestCase;

public class RabbitMoveSetTest extends TestCase{
	private GameModel game;
	private Board board;
	private Fox fox;
	private Fox fox2;
	private Fox fox3;
	private Fox fox4;
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
		moves=new ArrayList<MoveSet>();
		moveset=new MoveSet();
	}
	public void testRabbitMovesNone() {
		moves=RabbitLogic.findRabbitMoves(board);
		assertTrue(moves.isEmpty());
		
	}
}
