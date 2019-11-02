package jumpin.test.unit;

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
 public class MoveSetTest extends TestCase{
		private GameModel game;
		private Board board;
		private Fox fox;
		private Fox fox2;
		private Fox fox3;
		private Fox fox4;
		private List<MoveSet> moves;
		private GameView view;
		/**System.out.println(moves.size());
		for(MoveSet ms:moves) {
			for(Move m:ms) {
				System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
			}
		}
		DEBUGGING LOGIC
		**/
	@Override
	protected void setUp() {	
			game = new GameModel();
			board = game.getBoard();
			view = new GameView(game);
			fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
			fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
			fox3=new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
			fox4=new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
			
		}
		public void testFindFoxMoves() {
		board.assignPiece(new Position(1, 1), fox2);
		board.assignPiece(new Position(1, 2), fox);
		board.selectPiece(new Position(1,1));
		moves=FoxLogic.findFoxMoves(board);
		assertTrue(moves.size()==3);
		//assertTrue(moves[0].getNewPos())
		for(MoveSet ms:moves) {
			for(Move m:ms) {
				
			}
			}
		}
	
}
