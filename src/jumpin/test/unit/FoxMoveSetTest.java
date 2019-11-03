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
/**
 * 
 * @author Julian
 * TestCase for the MoveSet Class
 */
 public class FoxMoveSetTest extends TestCase{
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
			moveset=new MoveSet();
		}
		public void testFindFoxMovesMiddleOfBoardHeal() {
			board.assignPiece(new Position(1, 2), fox2);
			board.assignPiece(new Position(1, 1), fox);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==3);
			moveset.add(new Move(new Position(1,1),new Position(1,2)));
			moveset.add(new Move(new Position(1,2),new Position(1,3)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,1),new Position(1,0)));
			moveset.add(new Move(new Position(1,2),new Position(1,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,1),new Position(1,3)));
			moveset.add(new Move(new Position(1,2),new Position(1,4)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesMiddleOfBoardTail() {
			board.assignPiece(new Position(1, 2), fox2);
			board.assignPiece(new Position(1, 1), fox);
			board.selectPiece(new Position(1,2));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==3);
			moveset.add(new Move(new Position(1,1),new Position(1,2)));
			moveset.add(new Move(new Position(1,2),new Position(1,3)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,1),new Position(1,0)));
			moveset.add(new Move(new Position(1,2),new Position(1,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,1),new Position(1,3)));
			moveset.add(new Move(new Position(1,2),new Position(1,4)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesEdgeCaseWestGoingEastHead() {
			board.assignPiece(new Position(0,1 ), fox3);
			board.assignPiece(new Position(1,1), fox4);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==3);
			moveset.add(new Move(new Position(0,1),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(2,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(2,1)));
			moveset.add(new Move(new Position(1,1),new Position(3,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(3,1)));
			moveset.add(new Move(new Position(1,1),new Position(4,1)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesEdgeCaseWestGoingEastTail() {
			board.assignPiece(new Position(0,1 ), fox3);
			board.assignPiece(new Position(1,1), fox4);
			board.selectPiece(new Position(0,1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==3);
			moveset.add(new Move(new Position(0,1),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(2,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(2,1)));
			moveset.add(new Move(new Position(1,1),new Position(3,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(3,1)));
			moveset.add(new Move(new Position(1,1),new Position(4,1)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesCollisionWithRabbitSouthHead() {
			board.assignPiece(new Position(1, 0), fox);
			board.assignPiece(new Position(1, 1), fox2);
			board.assignPiece(new Position(1,4),rabbit);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==2);
			moveset.add(new Move(new Position(1,0),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(1,2)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,0),new Position(1,2)));
			moveset.add(new Move(new Position(1,1),new Position(1,3)));
			assertTrue(moves.contains(moveset));
		}
		public void  testFindFoxMovesCollisionWithRabbitSouthTail() {
			board.assignPiece(new Position(1, 0), fox);
			board.assignPiece(new Position(1, 1), fox2);
			board.assignPiece(new Position(1,4),rabbit);
			board.selectPiece(new Position(1,0));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==2);
			moveset.add(new Move(new Position(1,0),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(1,2)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,0),new Position(1,2)));
			moveset.add(new Move(new Position(1,1),new Position(1,3)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesCollisionWithRabbitNorthTail() {
			board.assignPiece(new Position(1, 3), fox);
			board.assignPiece(new Position(1, 4), fox2);
			board.assignPiece(new Position(1,0),rabbit);
			board.selectPiece(new Position(1,4));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==2);
			moveset.add(new Move(new Position(1,3),new Position(1,2)));
			moveset.add(new Move(new Position(1,4),new Position(1,3)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,3),new Position(1,1)));
			moveset.add(new Move(new Position(1,4),new Position(1,2)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesCollisionWithRabbitWestTail() {
			board.assignPiece(new Position(0, 1), fox3);
			board.assignPiece(new Position(1, 1), fox4);
			board.assignPiece(new Position(4,1),rabbit);
			board.selectPiece(new Position(0,1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==2);
			moveset.add(new Move(new Position(0,1),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(2,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(2,1)));
			moveset.add(new Move(new Position(1,1),new Position(3,1)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesCollisionWithRabbitEastTail() {
			board.assignPiece(new Position(0, 1), fox3);
			board.assignPiece(new Position(1, 1), fox4);
			board.assignPiece(new Position(4,1),rabbit);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==2);
			moveset.add(new Move(new Position(0,1),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(2,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(2,1)));
			moveset.add(new Move(new Position(1,1),new Position(3,1)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesCollisionWithRabbitNotOnEdge() {
			board.assignPiece(new Position(0, 1), fox3);
			board.assignPiece(new Position(1, 1), fox4);
			board.assignPiece(new Position(3,1),rabbit);
			board.selectPiece(new Position(1,1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==1);
			moveset.add(new Move(new Position(0,1),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(2,1)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesGoingWest() {
			board.assignPiece(new Position(3, 1), fox3);
			board.assignPiece(new Position(4, 1), fox4);
			board.selectPiece(new Position(4, 1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==3);
			moveset.add(new Move(new Position(3,1),new Position(2,1)));
			moveset.add(new Move(new Position(4,1),new Position(3,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(3,1),new Position(1,1)));
			moveset.add(new Move(new Position(4,1),new Position(2,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(3,1),new Position(0,1)));
			moveset.add(new Move(new Position(4,1),new Position(1,1)));
			assertTrue(moves.contains(moveset));
		}
		//WEST GOING EAST
		public void testFindFoxMovesGoingEast() {
			board.assignPiece(new Position(0, 1), fox3);
			board.assignPiece(new Position(1, 1), fox4);
			board.selectPiece(new Position(1, 1));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==3);
			moveset.add(new Move(new Position(0,1),new Position(1,1)));
			moveset.add(new Move(new Position(1,1),new Position(2,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(2,1)));
			moveset.add(new Move(new Position(1,1),new Position(3,1)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(0,1),new Position(3,1)));
			moveset.add(new Move(new Position(1,1),new Position(4,1)));
			assertTrue(moves.contains(moveset));
		}
		public void testFindFoxMovesSouthGoingNorth() {
			board.assignPiece(new Position(1, 3), fox);
			board.assignPiece(new Position(1, 4), fox2);
			board.selectPiece(new Position(1, 4));
			moves=FoxLogic.findFoxMoves(board);
			assertTrue(moves.size()==3);
			moveset.add(new Move(new Position(1,3),new Position(1,2)));
			moveset.add(new Move(new Position(1,4),new Position(1,3)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,3),new Position(1,1)));
			moveset.add(new Move(new Position(1,4),new Position(1,2)));
			assertTrue(moves.contains(moveset));
			moveset=new MoveSet();
			moveset.add(new Move(new Position(1,3),new Position(1,0)));
			moveset.add(new Move(new Position(1,4),new Position(1,1)));
			assertTrue(moves.contains(moveset));
		}
}
