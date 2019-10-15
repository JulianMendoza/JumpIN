package jumpin.model.piece.pieces;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.piece.Piece;

/**
 * A class for representing the mushroom piece
 * @author Giuseppe
 *
 */
public class Mushroom extends Piece {

	/**
	 * Constructs a mushroom piece that has a static orientation
	 */
	public Mushroom() {
		super(Orientation.STATIC);
	}

	/**
	 * method to return PieceConstant for mushroom
	 * 
	 * @return string constant for mushroom
	 */
	public String toString() {
		return PieceConstants.MUSHROOM;
	}

}
