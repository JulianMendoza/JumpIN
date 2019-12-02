package jumpin.model.piece;

import java.io.Serializable;

import jumpin.model.constants.Direction;
import jumpin.model.constants.Orientation;

/**
 * 
 * @author Giuseppe
 * 
 */
public class Piece implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 727628923264983774L;
	/**
	 * The orientation of the piece . NORTH_SOUTH/EAST_WEST if it is a fox. STATIC
	 * if it is a mushroom DYNAMIC if i is a rabbit
	 */
	private final Orientation orientation;

	/**
	 * Default constructor of a Piece
	 * 
	 * @param orientation Direction in which a piece can move
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

	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public Piece clone() throws CloneNotSupportedException {
		return (Piece) super.clone();

	}

}