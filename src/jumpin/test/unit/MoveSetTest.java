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
import jumpin.model.piece.pieces.Rabbit;
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
		private Rabbit rabbit;
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
			rabbit = new Rabbit(PieceID.RABBIT_ID_1);
			
		}
		public void testFindFoxMoves() {
		board.assignPiece(new Position(1, 2), fox2);
		board.assignPiece(new Position(1, 1), fox);
		board.selectPiece(new Position(1,1));
		moves=FoxLogic.findFoxMoves(board);
		System.out.println("Test 1");
		for(MoveSet ms:moves) {
			for(Move m:ms) {
				System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
			}
			}
		assertTrue(moves.size()==3);
		}
		public void testFindFoxMoves2() {
			board.assignPiece(new Position(1, 2), fox2);
			board.assignPiece(new Position(1, 1), fox);
			board.selectPiece(new Position(1,2));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 2");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==3);
		}
		public void testFindFoxMoves3() {
			board.assignPiece(new Position(0,1 ), fox3);
			board.assignPiece(new Position(1,1), fox4);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 3");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==3);
		}
		public void testFindFoxMoves4() {
			board.assignPiece(new Position(0,1 ), fox3);
			board.assignPiece(new Position(1,1), fox4);
			board.selectPiece(new Position(0,1));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 4");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==3);
		}
		public void testFindFoxMoves5() {
			board.assignPiece(new Position(1, 0), fox);
			board.assignPiece(new Position(1, 1), fox2);
			board.assignPiece(new Position(1,4),rabbit);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 5");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==2);
		}
		public void testFindFoxMoves6() {
			board.assignPiece(new Position(1, 0), fox);
			board.assignPiece(new Position(1, 1), fox2);
			board.assignPiece(new Position(1,4),rabbit);
			board.selectPiece(new Position(1,0));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 6");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==2);
		}
		public void testFindFoxMoves7() {
			board.assignPiece(new Position(1, 3), fox);
			board.assignPiece(new Position(1, 4), fox2);
			board.assignPiece(new Position(1,0),rabbit);
			board.selectPiece(new Position(1,4));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 7");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==2);
		}
		public void testFindFoxMoves8() {
			board.assignPiece(new Position(0, 1), fox3);
			board.assignPiece(new Position(1, 1), fox4);
			board.assignPiece(new Position(4,1),rabbit);
			board.selectPiece(new Position(0,1));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 8");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==2);
		}
		public void testFindFoxMoves9() {
			board.assignPiece(new Position(0, 1), fox3);
			board.assignPiece(new Position(1, 1), fox4);
			board.assignPiece(new Position(4,1),rabbit);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 9");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==2);
		}
		public void testFindFoxMoves10() {
			board.assignPiece(new Position(0, 1), fox3);
			board.assignPiece(new Position(1, 1), fox4);
			board.assignPiece(new Position(3,1),rabbit);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			System.out.println("Test 10");
			for(MoveSet ms:moves) {
				for(Move m:ms) {
					System.out.println("Old Pos:"+m.getOldPos().getX()+","+m.getOldPos().getY()+"New Pos:"+m.getNewPos().getX()+","+m.getNewPos().getY());
				}
				}
			assertTrue(moves.size()==1);
		}
}
