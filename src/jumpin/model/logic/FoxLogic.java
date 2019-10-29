package jumpin.model.logic;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.constants.Direction;
import jumpin.model.constants.Orientation;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.util.Position;

public class FoxLogic {

	public static List<MoveSet> findFoxMoves(Board board) {
		List<MoveSet> foxMoves = new ArrayList<MoveSet>();

		Fox fox = (Fox) board.getSelectedPiece();

		int maxMove;
		if (fox.getOrientation().equals(Orientation.NORTH_SOUTH)) {
			maxMove = board.getModel().getHeight();
		} else {
			maxMove = board.getModel().getWidth();
		}

		for (Direction direction : fox.getOrientation().getValidDirections()) {
			for (int i = 1; i < maxMove; i++) {
				if (isValidMove(board, direction, i)) {
					foxMoves.add(new Move(board.getSelectedPosition(), board.getSelectedPosition().nextPosition(direction, i)));
				}
			}
		}
		return foxMoves;
	}

	public static Position getOtherFoxPosition(Board board, Fox fox) {
		for (Direction direction : fox.getOrientation().getValidDirections()) {
			Position currentPos = board.getSelectedPosition().nextPosition(direction);
			Tile currentTile = board.getTile(currentPos);

			if (currentTile != null && !currentTile.isEmpty()) {
				if (fox.isSameFox(currentTile.getPiece())) {
					return currentPos;
				}
			}
		}
		return null; // should never return null
	}

	private static boolean isValidMove(Board board, Direction direction, int distance) {
		Position currentPos = board.getSelectedPosition();

		while (board.isValidPosition(currentPos.nextPosition(direction)) && board.getTile(currentPos.nextPosition(direction)).isEmpty() && !(board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole) && distance > 0) {
			currentPos = currentPos.nextPosition(direction);
			distance--;
		}

		return distance == 0; // if the fox moved anything other than its total distance

	}

}
