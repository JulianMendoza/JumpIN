package jumpin.model.logic;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.constants.Direction;
import jumpin.model.move.FoxMove;
import jumpin.model.move.Move;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.util.Position;

/**
 * A class with knowledge of the logic of various pieces on the board
 * 
 * @author Giuseppe, Julian
 *
 */
public class PieceLogic {

	/**
	 * 
	 * @param board     Board object
	 * @param direction Direction of Fox Movement
	 * @param distance  Distance of fox movement
	 * @return The movement of the fox
	 */
	public static FoxMove findFoxMove(Board board, Direction direction, int distance) {
		Fox fox = (Fox) board.getSelectedPiece();

		Position currentPos = board.getSelectedPosition().nextPosition(direction);

		// Can't slide fox off the board or into rabbit hole
		if (!board.isValidPosition(currentPos) || board.getTile(currentPos) instanceof RabbitHole) {
			return null;
		}

		Tile currentTile = board.getTile(currentPos);

		if (!currentTile.isEmpty()) {
			if (fox.isSameFox(currentTile.getPiece())) {
				// set the board to select the direction facing piece of the fox
				board.selectPiece(currentTile.getPiece(), currentPos);
				return slideFox(board, direction, distance);
			} else {
				return null;
			}
		}

		return slideFox(board, direction, distance); // direction facing piece of fox is already selected
	}

	/**
	 * method to determine if fox can slide x number of distance in a direction
	 * 
	 * @param board     Board object
	 * @param direction
	 * @param distance
	 * @return
	 */
	private static FoxMove slideFox(Board board, Direction direction, int distance) {
		Position currentPos = board.getSelectedPosition();

		while (board.isValidPosition(currentPos.nextPosition(direction)) && board.getTile(currentPos.nextPosition(direction)).isEmpty() && !(board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole) && distance > 0) {
			currentPos = currentPos.nextPosition(direction);
			distance--;
		}

		if (distance != 0) { // if the fox moved anything other than its total distance
			return null;
		}

		return new FoxMove(new Move(board.getSelectedPosition(), currentPos), new Move(board.getSelectedPosition().prevPosition(direction), currentPos.prevPosition(direction)));
	}

	/**
	 * method to get the movement of Rabbit
	 * 
	 * @param board     Object of Board
	 * @param direction Direction of Rabbit movement
	 * @return The movement of Rabbit on Board
	 */
	public static Move findRabbitMove(Board board, Direction direction) {
		Position selectedPosition = board.getSelectedPosition();
		Position currentPos = selectedPosition.nextPosition(direction);
		if (board.getTile(currentPos).isEmpty()) { // nothing for rabbit to jump over
			return null;
		}
		while (board.isValidPosition(currentPos)) {
			currentPos = currentPos.nextPosition(direction);
			if (!board.isValidPosition(currentPos)) { // edge conditions
				break;
			} else if (board.getTile(currentPos).isEmpty()) { // we found an empty tile on the board
				return new Move(selectedPosition, currentPos);
			}
		}
		return null;
	}
}