package jumpin.model.util;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.Board;
import jumpin.model.Move;
import jumpin.model.Position;
import jumpin.model.Tile;
import jumpin.model.constants.Direction;
import jumpin.model.piece.pieces.Fox;

public class PieceLogic {

	
	/**
	 * 
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static List<Move> findFoxMove(Board board, Direction direction) {
		Fox fox = (Fox) board.getSelectedPiece();
		
		Position currentPos = board.getSelectedPosition().nextPosition(direction);
		Tile currentTile = board.getTile(currentPos);
		
		if(!currentTile.isEmpty()) {
			if(fox.isSameFox(currentTile.getPiece())) {
				board.selectPiece(currentPos); //set the front facing piece of the fox
				return findFoxSlide(board, direction);
			} else {
				return null;
			}			
		} 	
		
		return findFoxSlide(board, direction);
	} 
	
	private static List<Move> findFoxSlide(Board board, Direction direction) {
		List<Move> moves = new ArrayList<Move>();
		Position currentPos = board.getSelectedPosition().nextPosition(direction);
		while(BoardUtilities.isValidPosition(currentPos.nextPosition(direction)) && board.getTile(currentPos.nextPosition(direction)).isEmpty()) {
			currentPos = currentPos.nextPosition(direction);
		}
		moves.add(new Move(board.getSelectedPosition(), currentPos));
		moves.add(new Move(board.getSelectedPosition().prevPosition(direction), currentPos.prevPosition(direction)));
		
		return moves;
	}

	/**
	 * Returns pos if no move
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static Move findRabbitMove(Board board, Direction direction) {
		Position selectedPosition = board.getSelectedPosition();
		Position currentPos = selectedPosition.nextPosition(direction);
		if(board.getTile(currentPos).isEmpty()) { //nothing for rabbit to jump over
			return null;
		}
		while(BoardUtilities.isValidPosition(currentPos)) {
			currentPos = currentPos.nextPosition(direction);
			if(board.getTile(currentPos).isEmpty()) { //we found an empty tile on the board
				return new Move(selectedPosition, currentPos); 
			}
		}
		return null;
	}
}
