package jumpin.model.util;

import jumpin.model.Board;
import jumpin.model.Position;
import jumpin.model.constants.Direction;

public class PieceLogic {

	
	/**
	 * 
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static Position findFoxMove(Board board, Position pos, Direction direction) {
		Position currentPos = pos;
		currentPos = currentPos.nextPosition(direction);
		if(!board.getTile(currentPos).isEmpty()) {
			return pos;
		}
		while(BoardUtilities.isValidPosition(currentPos)) {
			currentPos = currentPos.nextPosition(direction);
			if(board.getTile(currentPos).isEmpty()) { //we found an empty tile on the board
				return currentPos; 
			}
		}
		return pos;
	}

	/**
	 * Returns pos if no move
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static Position findRabbitMove(Board board, Position pos, Direction direction) {
		Position currentPos = pos;
		currentPos = currentPos.nextPosition(direction);
		if(board.getTile(currentPos).isEmpty()) { //nothing for rabbit to jump over
			return pos;
		}
		while(BoardUtilities.isValidPosition(currentPos)) {
			currentPos = currentPos.nextPosition(direction);
			if(board.getTile(currentPos).isEmpty()) { //we found an empty tile on the board
				return currentPos; 
			}
		}
		return pos;
	}

}
