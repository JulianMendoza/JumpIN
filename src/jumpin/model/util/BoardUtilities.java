package jumpin.model.util;

import jumpin.model.Position;
import jumpin.model.Tile;
import jumpin.model.constants.BoardConstants;
import jumpin.model.constants.Direction;

public class BoardUtilities {

	public static Tile[][] createDefaultBoard() {
		int height = BoardConstants.HEIGHT;
		int width = BoardConstants.WIDTH;

		Tile[][] board = new Tile[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = createTile();
			}
		}
		return board;
	}
	
	/**
	 * Only east and south are considered positive directions when north west is (0,0)
	 *
	 * @param direction
	 * @return
	 */
	public static boolean isPositive(Direction direction) {
		switch(direction) {
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
		return true; //gets here if the move is in the middle of the board
	}

	private static Tile createTile() {
		return new Tile();
	}

}
