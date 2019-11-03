package jumpin.test.unit;

import java.util.Arrays;
import java.util.List;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.logic.FoxLogic;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.util.Position;
import jumpin.view.GameView;
import junit.framework.TestCase;
/**
 * 
 * @author Julian
 *	TestCase for The FoxLogic Class
 */
public class FoxLogicTest extends TestCase {
	private GameModel game;
	private Board board;
	private Fox fox;
	private Fox fox2;
	private List<MoveSet> moves;
	private GameView view;

	@Override
	protected void setUp() {
		game = new GameModel();
		board = game.getBoard();
		view = new GameView(game);
		fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 1), fox2);
		board.assignPiece(new Position(1, 0), fox);
		board.selectPiece(new Position(1, 1));
		moves = FoxLogic.findFoxMoves(board);
	}
	public void testCreateMoveset() {
		MoveSet test = FoxLogic.createMoveSet(new Move(board.getSelectedPosition(), new Position(1, 4)), board);
		assertTrue(test.size() == 2);
		assertTrue(test.contains(new Move(new Position(1, 1), new Position(1, 4))));
		assertTrue(test.contains(new Move(new Position(1, 0), new Position(1, 3))));

	}
	public void testGetOtherFoxPosition() {
		Position fox2pos = FoxLogic.getOtherFoxPosition(board, fox);
		assertEquals(fox2pos, new Position(1, 0));
	}
}
