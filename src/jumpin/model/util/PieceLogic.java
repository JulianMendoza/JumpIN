package jumpin.model.util;

import java.util.ArrayList;
import java.util.List;
import jumpin.model.Board;
import jumpin.model.Move;
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
	public static List<Move> findFoxMove(Board board, Direction direction) {
		List<Move> moves = new ArrayList<Move>();
		
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
