package jumpin.model.piece.pieces;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.piece.Piece;
import jumpin.model.piece.UniquePiece;

/**
 * A class for representing the rabbit piece
 * 
 * @author Giuseppe
 *
 */
public class Rabbit extends Piece implements UniquePiece {

	private String pieceID;

	/**
	 * Constructs rabbit piece
	 * 
	 * @param pieceID String representation of Rabbit piece number
	 */
	public Rabbit(String pieceID) {
		super(Orientation.DYNAMIC);
		this.pieceID = pieceID;
	}

	/**
	 * method to return string representation of rabbit piece
	 * 
	 * @return string constant for Rabbit piece
	 */
	public String toString() {
		return PieceConstants.RABBIT;
	}

	/**
	 * method to return string representation of Rabbit piece ID
	 * 
	 * @return ID for a rabbit piece
	 */
	@Override
	public String getPieceID() {
		return pieceID;
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
			return pieceID.equals(((Rabbit) o).getPieceID());
		} else {
			return false;
		}
	}

}