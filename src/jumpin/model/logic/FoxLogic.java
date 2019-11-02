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

/**
 * Foxlogic is a utility class used to compute a list of valid moves. The list
 * contains a set of moves (called a MoveSet) which contains the old and new
 * position for both pieces of the fox
 * 
 * @author Giuseppe, Julian
 * 
 *
 */
public class FoxLogic {
	/**
	 * Function to compute the list of MoveSets
	 * 
	 * @param board
	 * @return A List of moves. This list will always contain a list that is the
	 *         size of available tiles on the board. within the row/column of the
	 *         fox
	 */
	public static List<MoveSet> findFoxMoves(Board board) {
		List<MoveSet> foxMoves = new ArrayList<MoveSet>();

		Fox fox = (Fox) board.getSelectedPiece(); // **warning** make sure board.getSelectedPiece() is an instanceof Fox

		int maxMove;
		if (fox.getOrientation().equals(Orientation.NORTH_SOUTH)) {
			maxMove = board.getModel().getHeight() - 2; // Always 3
		} else {
			maxMove = board.getModel().getWidth() - 2; // Always 3
		}

		for (Direction direction : fox.getOrientation().getValidDirections()) {
			for (int i = 1; i <= maxMove; i++) {
				if (isValidMove(board, direction, i)) {
					Move move = new Move(board.getSelectedPosition(),
							board.getSelectedPosition().nextPosition(direction, i));
					foxMoves.add(createMoveSet(move, board));
				}
			}
		}
		return foxMoves;
	}

	/**
	 * Function to append to the list of MoveSet
	 * 
	 * @param move  The new move for the current selected piece
	 * @param board
	 * @return A new MoveSet containing the new and old positions of both pieces of
	 *         the fox (4 positions)
	 */
	public static MoveSet createMoveSet(Move move, Board board) {
		MoveSet moveSet = new MoveSet();
		moveSet.add(move);
		Position oldPos = getOtherFoxPosition(board, (Fox) board.getSelectedPiece());
		Pair<Integer, Integer> inc = move.getIncrement();
		Position newPos = new Position(oldPos.getX() + inc.getFirst(), oldPos.getY() + inc.getSecond());
		moveSet.add(new Move(oldPos, newPos));
		return moveSet;
	}

	/**
	 * Function to get the other fox position
	 * 
	 * @param board
	 * @param fox   The other piece of the fox
	 * @return The position of the fox relating to the piece passed in the
	 *         parameter.
	 */
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

	/**
	 * Function to determine if the fox can move in a specific manner
	 * 
	 * @param board
	 * @param direction
	 * @param distance
	 * @return True if the fox is allowed to move, false otherwise.
	 */
	private static boolean isValidMove(Board board, Direction direction, int distance) {
		Position currentPos = board.getSelectedPosition();

		while (isNextValid(board, currentPos, direction)
				&& (isNextEmpty(board, currentPos, direction) || isNextSameFox(board, currentPos, direction))
				&& !isNextRabbitHole(board, currentPos, direction) && distance > 0
				&& !isOnEdge(board, currentPos, direction)) {

			currentPos = currentPos.nextPosition(direction);
			distance--;
		}

		return distance == 0; // if the fox moved anything other than its total distance

	}

	private static boolean isNextRabbitHole(Board board, Position currentPos, Direction direction) {
		return (board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole);
	}

	private static boolean isNextValid(Board board, Position currentPos, Direction direction) {
		return board.isValidPosition(currentPos.nextPosition(direction));
	}

	private static boolean isNextEmpty(Board board, Position currentPos, Direction direction) {
		if (board.getTile(currentPos.nextPosition(direction)) == null)
			return false;
		return board.getTile(currentPos.nextPosition(direction)).isEmpty();
	}

	private static boolean isNextSameFox(Board board, Position currentPos, Direction direction) {
		Position nextPos = currentPos.nextPosition(direction);
		if (board.getTile(nextPos).getPiece() instanceof Fox) {
			return ((Fox) board.getTile(nextPos).getPiece()).isSameFox(board.getSelectedPiece());
		}
		return false;
	}

	/**
	 * @TODO calling position from tile indexed from 1-5 Calling index from board
	 *       indexed 0-4
	 * @param board
	 * @param currentPos
	 * @param direction
	 * @return
	 */
	private static boolean isOnEdge(Board board, Position currentPos, Direction direction) {
		Fox fox = (Fox) board.getSelectedPiece();
		Position fox2 = getOtherFoxPosition(board, fox);
		int check;// used to determine which fox is placed infront
		switch (fox.getOrientation()) {
		case EAST_WEST:
			if (direction == Direction.EAST) {
				check = board.getSelectedPosition().getX() - fox2.getX(); // -1 if tail is selected
				if (check == -1) {
					if (isNextEmpty(board, currentPos.nextPosition(direction), direction)
							|| (board.getTile(currentPos.nextPosition(direction)).getPiece() instanceof Fox)) { 
						// tail is selected so we must make sure there's nothing in front of it
						return board.getSelectedPosition().getX() == 4 || fox2.getX() == 4 || (check == -1)
								? currentPos.getX() == 3
								: currentPos.getX() == 4;
					} else {
						return true;
					}
				}
				return board.getSelectedPosition().getX() == 4 || fox2.getX() == 4 || (check == -1)
						? currentPos.getX() == 3
						: currentPos.getX() == 4;
			} else {
				check = board.getSelectedPosition().getX() - fox2.getX(); // 1 if tail is selected
				if (check == 1) {
					if (isNextEmpty(board, currentPos.nextPosition(direction), direction)
							|| (board.getTile(currentPos.nextPosition(direction)).getPiece() instanceof Fox)) {
						// tail is selected so we must make sure there's nothing in front of it
						return board.getSelectedPosition().getX() == 4 || fox2.getX() == 4 || (check == 1)
								? currentPos.getX() == 1
								: currentPos.getX() == 0;
					} else {
						return true;
					}
				}
				return board.getSelectedPosition().getX() == 0 || fox2.getX() == 0 || (check == 1)
						? currentPos.getX() == 1
						: currentPos.getX() == 0;
			}
		case NORTH_SOUTH:
			if (direction == Direction.SOUTH) {
				check = board.getSelectedPosition().getY() - fox2.getY(); // -1 if tail is selected
				if (check == -1) {
					if (isNextEmpty(board, currentPos.nextPosition(direction), direction)
							|| (board.getTile(currentPos.nextPosition(direction)).getPiece() instanceof Fox)) {
						// tail is selected so we must make sure there's nothing in front of it
						return board.getSelectedPosition().getX() == 4 || fox2.getX() == 4 || (check == -1)
								? currentPos.getY() == 3
								: currentPos.getY() == 4;
					} else {
						return true;
					}
				}
				return board.getSelectedPosition().getY() == 4 || fox2.getY() == 4 || (check == -1)
						? currentPos.getY() == 3
						: currentPos.getY() == 4;
			} else {
				check = board.getSelectedPosition().getY() - fox2.getY(); // 1 if tail is selected
				if (check == 1) {
					if (isNextEmpty(board, currentPos.nextPosition(direction), direction)
							|| (board.getTile(currentPos.nextPosition(direction)).getPiece() instanceof Fox)) {
						// tail is selected so we must make sure there's nothing in front of it
						return board.getSelectedPosition().getX() == 4 || fox2.getX() == 4 || (check == 1)
								? currentPos.getY() == 1
								: currentPos.getY() == 0;
					} else {
						return true;
					}
				}
				return board.getSelectedPosition().getY() == 0 || fox2.getY() == 0 || (check == 1)
						? currentPos.getY() == 1
						: currentPos.getY() == 0;
			}
		default:
			return false;
		}
	}
}
