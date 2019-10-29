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
import jumpin.util.Pair;

public class FoxLogic {

	public static List<MoveSet> findFoxMoves(Board board) {
		List<MoveSet> foxMoves = new ArrayList<MoveSet>();

		Fox fox = (Fox) board.getSelectedPiece();

		int maxMove;
		if (fox.getOrientation().equals(Orientation.NORTH_SOUTH)) {
			maxMove = board.getModel().getHeight()-2;
		} else {
			maxMove = board.getModel().getWidth()-2;
		}

		for (Direction direction : fox.getOrientation().getValidDirections()) {
			for (int i = 1; i < maxMove; i++) {
				if (isValidMove(board, direction, i)) {
					Move move = new Move(board.getSelectedPosition(), board.getSelectedPosition().nextPosition(direction, i));
					foxMoves.add(createMoveSet(move, board));
				}
			}
		}
		return foxMoves;
	}
	public static MoveSet createMoveSet(Move move,Board board) {
		MoveSet moveSet = new MoveSet();
		moveSet.add(move);
		Position oldPos = getOtherFoxPosition(board, (Fox) board.getSelectedPiece());
		Pair<Integer, Integer> inc = move.getIncrement();
		Position newPos = new Position(oldPos.getX() + inc.getFirst(), oldPos.getY() + inc.getSecond());
		moveSet.add(new Move(oldPos, newPos));
		return moveSet;
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

		while (isNextValid(board,direction) && (isNextEmpty(board,direction) || isNextSameFox(board, direction)) && !isNextRabbitHole(board,direction) &&  distance > 0) {
			currentPos = currentPos.nextPosition(direction);
			distance--;
		}

		return distance == 0; // if the fox moved anything other than its total distance
		
	}
	
	private static boolean isNextRabbitHole(Board board,Direction direction) {
		return (board.getTile(board.getSelectedPosition().nextPosition(direction)) instanceof RabbitHole);
	}
	private static boolean isNextValid(Board board,Direction direction) {
		return board.isValidPosition(board.getSelectedPosition().nextPosition(direction));
	}
	private static boolean isNextEmpty(Board board,Direction direction) {
		return board.getTile(board.getSelectedPosition().nextPosition(direction)).isEmpty();
	}
	private static boolean isNextSameFox(Board board,Direction direction) {
		Position nextPos = board.getSelectedPosition().nextPosition(direction);
		if(board.getTile(nextPos).getPiece() instanceof Fox) {
			return ((Fox) board.getTile(nextPos).getPiece()).isSameFox(board.getSelectedPiece());
		}
		return false;
	}

}
