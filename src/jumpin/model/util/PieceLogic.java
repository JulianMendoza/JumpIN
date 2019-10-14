package jumpin.model.util;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.constants.Direction;
import jumpin.model.move.FoxMove;
import jumpin.model.move.Move;
import jumpin.model.piece.pieces.Fox;

/**
 * A class with knowledge of the logic of various pieces on the board
 * @author Giuseppe, Julian
 *
 */
public class PieceLogic {

	/**
	 * 
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static FoxMove findFoxMove(Board board, Direction direction, int distance) {
		Fox fox = (Fox) board.getSelectedPiece();

		Position currentPos = board.getSelectedPosition().nextPosition(direction);
		Tile currentTile = board.getTile(currentPos);
		/*
		 * Remove this if statement if foxes can block holes
		 */
		if (board.getTile(currentPos) instanceof RabbitHole) {
			return null;
		}
		if (!currentTile.isEmpty()) {
			if (fox.isSameFox(currentTile.getPiece())) {
				board.selectPiece(currentPos); // set the board to select the direction facing piece of the fox
				return slideFox(board, direction, distance);
			} else {
				return null;
			}
		}

		return slideFox(board, direction, distance); // direction facing piece of fox is already selected
	}

	private static FoxMove slideFox(Board board, Direction direction, int distance) {
		Position currentPos = board.getSelectedPosition();
		
		/*
		 * 
		 */
		while (BoardUtilities.isValidPosition(currentPos.nextPosition(direction)) && board.getTile(currentPos.nextPosition(direction)).isEmpty() && !(board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole) && distance > 0) {
			currentPos = currentPos.nextPosition(direction);
			distance--;
		}
		
		if(distance != 0) { 
			return null;
		}
		
		return new FoxMove(new Move(board.getSelectedPosition(), currentPos), new Move(board.getSelectedPosition().prevPosition(direction), currentPos.prevPosition(direction)));
	}
	
	

	/**
	 * Returns pos if no move
	 * 
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static Move findRabbitMove(Board board, Direction direction) {
		Position selectedPosition = board.getSelectedPosition();
		Position currentPos = selectedPosition.nextPosition(direction);
		if (board.getTile(currentPos).isEmpty()) { // nothing for rabbit to jump over
			return null;
		}
		while (BoardUtilities.isValidPosition(currentPos)) {
			currentPos = currentPos.nextPosition(direction);
			if(!BoardUtilities.isValidPosition(currentPos)) { //edge conditions
				break;
			}
			if (board.getTile(currentPos).isEmpty()) { // we found an empty tile on the board
				return new Move(selectedPosition, currentPos);
			}
		}
		return null;
	}
}
