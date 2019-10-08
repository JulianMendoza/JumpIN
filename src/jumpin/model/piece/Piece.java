package jumpin.model.piece;

import jumpin.model.constants.Direction;
import jumpin.model.constants.Orientation;

/**
 * 
 * @author Giuseppe Papalia
 * @version text
 * @since 07/10/19
 * 
 */
public class Piece {
	/**
	 * The orientation of the piece . NORTH_SOUTH/EAST_WEST if it is a fox. STATIC
	 * if it is a mushroom DYNAMIC if i is a rabbit
	 */
	private Orientation orientation;

	/**
	 * Default constructor of a Piece
	 * 
	 * @param orientation
	 */
	public Piece(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * Determine if the piece is allowed to move in a certain direction
	 * 
	 * @param direction The direction where it wants to move
	 * @return true if it is available to move in the direction false otherwise.
	 */
	public boolean allowsDirection(Direction direction) {
		return orientation.isValidDirection(direction);
	}

}
