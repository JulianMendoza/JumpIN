package jumpin.model.board.event;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.structures.move.Move;
import jumpin.model.structures.move.MoveSet;

/**
 * 
 * @author Giuseppe
 *
 */
public class EventFactory {

	/**
	 * Get Movement on board
	 * 
	 * @param moveSet movement on board
	 * @param board initial board
	 * @return
	 */
	public static BoardModelEvent generateBoardModelEvent(MoveSet moveSet, Board board) {
		int id = BoardModelEventID.MOVE_GENERIC;
		for (Move move : moveSet) {
			if (board.getTile(move.getOldPos()) instanceof RabbitHole && !(board.getTile(move.getNewPos()) instanceof RabbitHole)) {
				id = BoardModelEventID.MOVE_OFF_RABBIT_HOLE;
			} else if (board.getTile(move.getNewPos()) instanceof RabbitHole && !(board.getTile(move.getOldPos()) instanceof RabbitHole)) {
				id = BoardModelEventID.MOVE_ON_RABBIT_HOLE;
			}
		}
		return new BoardModelEvent(id, moveSet);
	}

}
