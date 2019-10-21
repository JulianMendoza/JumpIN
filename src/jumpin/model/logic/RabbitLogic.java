package jumpin.model.logic;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.move.Move;
import jumpin.model.util.Position;

public class RabbitLogic {

	public static List<Move> findRabbitMoves(Board board) {
		List<Move> rabbitMoves = new ArrayList<Move>();
		for (Direction direction : Direction.values()) {
			Position selectedPosition = board.getSelectedPosition();
			Position currentPos = selectedPosition.nextPosition(direction);
			if (board.getTile(currentPos).isEmpty()) { // nothing for rabbit to jump over
				continue;
			}
			while (board.isValidPosition(currentPos)) {
				currentPos = currentPos.nextPosition(direction);
				if (!board.isValidPosition(currentPos)) { // edge conditions
					break;
				} else if (board.getTile(currentPos).isEmpty()) { // we found an empty tile on the board
					rabbitMoves.add(new Move(selectedPosition, currentPos));
				}
			}
		}
		return rabbitMoves;
	}

}
