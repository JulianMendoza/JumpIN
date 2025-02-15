package jumpin.model.logic;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.board.tile.Tile;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.UniquePiece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;
import jumpin.model.structures.move.Move;
import jumpin.model.structures.move.MoveSet;
/**
 * Class to determine the logic of the board
 * 
 * @author Giuseppe
 *
 */
public class BoardLogic {

	/**
	 * Method to return valid moves on board
	 * 
	 * @param board
	 * @return valid moves
	 */
	public static List<MoveSet> getValidMoves(Board board) {
		List<MoveSet> validMoves = new ArrayList<MoveSet>();
		if (board.getSelectedPiece() instanceof Rabbit) {
			validMoves.addAll(RabbitLogic.findRabbitMoves(board));
		} else if (board.getSelectedPiece() instanceof Fox) {
			validMoves.addAll(FoxLogic.findFoxMoves(board));
		}
		return validMoves;
	}
	
	/**
	 * method to organize IDs for in level builder
	 * @param board
	 */
	public static void organizeID(Board board){
		int height = board.getModel().getHeight();
		int width = board.getModel().getWidth();
		int currRabbitID = 1;
		int currFoxID = 1;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				Tile tile = board.getTile(pos);
				if(tile.getPiece() != null && tile.getPiece() instanceof UniquePiece) {
					if(tile.getPiece() instanceof Fox){
						Fox fox = (Fox) tile.getPiece();
						assignFoxID(board,fox,currFoxID,pos);
						currFoxID++;
					}
					else{
						Rabbit rabbit = new Rabbit(PieceID.RABBIT+currRabbitID);
						board.assignPiece(pos,rabbit);
						currRabbitID++;
					}
				}
			}
		}
	}
	
	/**
	 * method to assign FoxID in level builder
	 * @param board
	 * @param fox
	 * @param currFoxID
	 * @param pos
	 */
	private static void assignFoxID(Board board,Fox fox,int currFoxID,Position pos) {
		if(fox.getPieceID().equals(PieceID.FOX)) {
			Fox newFox = new Fox(fox.getPart(),fox.getOrientation(),PieceID.FOX+currFoxID);
			board.assignPiece(pos,newFox);
			switch(fox.getOrientation()) {
			case NORTH_SOUTH:
				if(pos.getY()==0) { 
					if(fox.getPart().equals(FoxPart.TAIL)) {
						sameFoxHelper(new Position(pos.getX(),pos.getY()+1),board,currFoxID,Orientation.NORTH_SOUTH);
					}
				}else if(pos.getY()==4) {
					if(fox.getPart().equals(FoxPart.HEAD)) {
						sameFoxHelper(new Position(pos.getX(),pos.getY()-1),board,currFoxID,Orientation.NORTH_SOUTH);
					}
				}else {
					if(fox.getPart().equals(FoxPart.TAIL)) {
						sameFoxHelper(new Position(pos.getX(),pos.getY()+1),board,currFoxID,Orientation.NORTH_SOUTH);
					}else if(fox.getPart().equals(FoxPart.HEAD)) {
						sameFoxHelper(new Position(pos.getX(),pos.getY()-1),board,currFoxID,Orientation.NORTH_SOUTH);
					}
				}
				break;
			case EAST_WEST:
				if(pos.getX()==0) { 
					if(fox.getPart().equals(FoxPart.HEAD)) {
						sameFoxHelper(new Position(pos.getX()+1,pos.getY()),board,currFoxID,Orientation.EAST_WEST);
					}
				}else if(pos.getX()==4) {
					if(fox.getPart().equals(FoxPart.TAIL)) {
						sameFoxHelper(new Position(pos.getX()-1,pos.getY()),board,currFoxID,Orientation.EAST_WEST);
					}
				}else {
					if(fox.getPart().equals(FoxPart.HEAD)) {
						sameFoxHelper(new Position(pos.getX()+1,pos.getY()),board,currFoxID,Orientation.EAST_WEST);
					}else if(fox.getPart().equals(FoxPart.TAIL)) {
						sameFoxHelper(new Position(pos.getX()-1,pos.getY()),board,currFoxID,Orientation.EAST_WEST);
					}
				}
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * method to check for same fox
	 * @param pos
	 * @param board
	 * @param currFoxID
	 * @param o
	 */
	private static void sameFoxHelper(Position pos,Board board,int currFoxID,Orientation o) {
		Tile tile=board.getTile(pos);
		if(tile.getPiece() instanceof Fox){
			Fox fox2 = (Fox) tile.getPiece();
			if(fox2.getOrientation()==o) {
				Fox newFox = new Fox(fox2.getPart(),fox2.getOrientation(),PieceID.FOX+currFoxID);
				board.assignPiece(pos,newFox);
			}
		}
	}
	/**
	 * Method to generate moveset based on a move for a piece
	 * 
	 * @param move
	 * @param board
	 * @return moveset for selected piece
	 */
	public static MoveSet generateMoveSet(Move move, Board board) {
		if(board.getSelectedPiece() instanceof Rabbit) {
			return MoveSet.createSingleMoveSet(move);
		} else if(board.getSelectedPiece() instanceof Fox) {
			  return FoxLogic.createMoveSet(move, board);
		} else {
			return new MoveSet();
		}
		
	}

}
