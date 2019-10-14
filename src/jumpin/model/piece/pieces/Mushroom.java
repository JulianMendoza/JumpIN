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

	public Mushroom() {
		super(Orientation.STATIC);
	}

	public String toString() {
		return PieceConstants.MUSHROOM;
	}

}
