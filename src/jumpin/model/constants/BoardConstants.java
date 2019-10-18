package jumpin.model.constants;

import jumpin.model.util.Position;

/**
 * Constants related to the game board
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class BoardConstants {

	public static final int HEIGHT = 5;

	public static final int WIDTH = 5;
	/**
	 * Maximum x or y coordinate a piece can move to on the board
	 */
	public static final int MAX_POS = 4;
	/**
	 * Minimum x or y coordinate a piece can move to on the board
	 */
	public static final int MIN_POS = 0;
	/**
	 * Array of positions on the board that contain a rabbit hole
	 */
	public static final Position[] RABBIT_HOLES = { new Position(0, 0), new Position(0, 4), new Position(2, 2),
			new Position(4, 0), new Position(4, 4) };

}