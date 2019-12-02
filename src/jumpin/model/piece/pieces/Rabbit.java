package jumpin.model.piece.pieces;

import java.io.Serializable;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.UniquePiece;

/**
 * A class for representing the rabbit piece
 * 
 * @author Giuseppe
 *
 */
public class Rabbit extends UniquePiece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -776390835267869484L;

	/**
	 * Constructs rabbit piece
	 * 
	 * @param pieceID String representation of Rabbit piece number
	 */
	public Rabbit(String pieceID) {
		super(Orientation.DYNAMIC, pieceID);
	}

	/**
	 * method to return string representation of rabbit piece
	 * 
	 * @return string constant for Rabbit piece
	 */
	@Override
	public String toString() {
		return PieceID.RABBIT;
	}

	/**
	 * method to compare two objects of type Rabbit
	 * 
	 * @param o Object
	 * @return True if instance of rabbit is equal to an object of Rabbit otherwise
	 *         false
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o == this) {
			return true;
		} else if (o instanceof Rabbit) {
			return getPieceID().equals(((Rabbit) o).getPieceID());
		} else {
			return false;
		}
	}

}