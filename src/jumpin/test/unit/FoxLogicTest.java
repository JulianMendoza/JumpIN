package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.logic.FoxLogic;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.structures.Position;
import jumpin.model.structures.move.Move;
import jumpin.model.structures.move.MoveSet;
import junit.framework.TestCase;
/**
 * 
 * @author Julian
 *	TestCase for The FoxLogic Class
 */
public class FoxLogicTest extends TestCase {
	private GameModel game;
	private Board board;
	private Fox fox, fox2;


	@Override
	protected void setUp() throws CloneNotSupportedException {
		game = new GameModel();
		board = game.getBoard();
		fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		board.assignPiece(new Position(1, 1), fox2);
		board.assignPiece(new Position(1, 0), fox);
		board.selectPiece(new Position(1, 1));
	}
	
	/**
	 * Test if fox piece exists
	 * 
	 */
	public void testFoxExists() {
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		board.selectPiece(new Position(1, 1));
		assertTrue(board.getSelectedPiece() instanceof Fox);
		board.selectPiece(new Position(1, 0));
		assertTrue(board.getSelectedPiece()  instanceof Fox);
	}
	
	/**
	 * Test if Fox's move set contains the correct moves
	 * 
	 */
	public void testCreateMoveset() {
		MoveSet test = FoxLogic.createMoveSet(new Move(board.getSelectedPosition(), new Position(1, 4)), board);
		assertTrue(test.size() == 2);
		assertTrue(test.contains(new Move(new Position(1, 1), new Position(1, 4))));
		assertTrue(test.contains(new Move(new Position(1, 0), new Position(1, 3))));

	}
	
	/**
	 * Test if other fox position on board is correcet
	 * 
	 */
	public void testGetOtherFoxPosition() {
		Position fox2pos = FoxLogic.getOtherFoxPosition(board, fox);
		assertEquals(fox2pos, new Position(1, 0));
	}
}
