
package jumpin.model.util;

import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.board.RabbitHole;
import jumpin.model.board.Tile;
import jumpin.model.constants.BoardConstants;
import jumpin.model.constants.Direction;
import jumpin.model.move.FoxMove;
import jumpin.model.move.Move;

/**
 * Utility class for operations with the board. Has knowledge of the default
 * board model and other constants
 * 
 * @author Giuseppe
 *
 */
public class BoardUtilities {

	public static Tile[][] createDefaultBoard() {
		int height = BoardConstants.HEIGHT;
		int width = BoardConstants.WIDTH;

		Tile[][] board = new Tile[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (isRabbitHole(i, j)) {
					board[i][j] = createRabbitHole();
				} else {
					board[i][j] = createTile();
				}
			}
		}
		return board;
	}

	/**
	 * Only east and south are considered positive directions when north west is
	 * (0,0)
	 *
	 * @param direction
	 * @return
	 */
	public static boolean isPositive(Direction direction) {
		switch (direction) {
		case EAST:
		case SOUTH:
			return true;
		default:
			return false;
		}
	}

	public static boolean allowsDirection(Direction direction, Position pos) {
		int minPos = BoardConstants.MIN_POS;
		int maxPos = BoardConstants.MAX_POS;

		int x = pos.getX();
		int y = pos.getY();
		if (direction.isEastWest()) {
			if (x == minPos) { // if the pos is on the west border it can only go east
				return direction.equals(Direction.EAST);
			} else if (x == maxPos) { // if the pos is on the east border it can only go west
				return direction.equals(Direction.WEST);
			}
		} else if (direction.isNorthSouth()) {
			if (y == minPos) { // if the pos is on the north border it can only go south
				return direction.equals(Direction.SOUTH);
			} else if (y == maxPos) { // if the pos is on the south border it can only go north
				return direction.equals(Direction.NORTH);
			}
		}
		return true; // gets here if the move is in the middle of the board
	}

	public static boolean isValidPosition(Position pos) {
		int maxPos = BoardConstants.MAX_POS;
		int minPos = BoardConstants.MIN_POS;
		int x = pos.getX();
		int y = pos.getY();
		return x <= maxPos && x >= minPos && y <= maxPos && y >= minPos;
	}

	public static Move findRabbitMove(Board board, Direction direction) {
		return PieceLogic.findRabbitMove(board, direction);
	}

	public static FoxMove findFoxMove(Board board, Direction direction, int moveTiles) {
		return PieceLogic.findFoxMove(board, direction, moveTiles);
	}

	private static boolean isRabbitHole(int x, int y) {
		Position[] rabbitHoles = BoardConstants.RABBIT_HOLES;
		for (int i = 0; i < rabbitHoles.length; i++) {
			if (new Position(x, y).equals(rabbitHoles[i])) {
				return true;
			}
		}
		return false;
	}

	private static Tile createTile() {
		return new Tile();
	}

	private static RabbitHole createRabbitHole() {
		return new RabbitHole();
	}

}
