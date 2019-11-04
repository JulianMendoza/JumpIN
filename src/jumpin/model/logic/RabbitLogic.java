package jumpin.model.logic;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.util.Position;

public class RabbitLogic {

	/**
	 * Method to implement the logic for rabbit movement
	 * 
	 * @param board
	 * @return possible rabbit moves
	 */
	public static List<MoveSet> findRabbitMoves(Board board) {
		List<MoveSet> rabbitMoves = new ArrayList<MoveSet>();
		for (Direction direction : Direction.values()) {
			Position selectedPosition = board.getSelectedPosition();
			Position currentPos = selectedPosition.nextPosition(direction);
			if (board.getTile(currentPos) == null || board.getTile(currentPos).isEmpty()||currentPos==null) { // nothing for rabbit to jump over
				continue;
			}
			while (board.isValidPosition(currentPos)) {
				currentPos = currentPos.nextPosition(direction);
				if (!board.isValidPosition(currentPos)) { // edge conditions
					break;
				} else if (board.getTile(currentPos).isEmpty()) { // we found an empty tile on the board
					rabbitMoves.add(MoveSet.createSingleMoveSet(new Move(selectedPosition, currentPos)));
					break;
				}
			}
		}
		return rabbitMoves;
	}

}
