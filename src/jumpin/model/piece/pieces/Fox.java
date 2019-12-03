package jumpin.model.piece.pieces;

import java.io.Serializable;

import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.Piece;
import jumpin.model.piece.UniquePiece;

/**
 * A class for representing the fox piece
 * 
 * @author Giuseppe
 *
 */
public class Fox extends UniquePiece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311570701232204712L;
	private FoxPart part;

	/**
	 * Constructs a Fox piece
	 * 
	 * @param part        Represents the different parts of a Fox
	 * @param orientation Direction that a Fox can move
	 * @param pieceID     String representation of Fox piece number
	 */
	public Fox(FoxPart part, Orientation orientation, String pieceID) {
		super(orientation, pieceID);
		this.part = part;
	}

	/**
	 * method for getting part for Fox piece
	 * 
	 * @return parts of Fox piece
	 */
	public FoxPart getPart() {
		return part;
	}

	/**
	 * method to check if Fox piece corresponds to head or to tail
	 * 
	 * @param piece
	 * @return true if Fox piece is the same
	 */
	public boolean isSameFox(Piece piece) {
		if (piece instanceof Fox) {
			Fox fox = (Fox) piece;
			return fox.getPieceID().equals(getPieceID());
		}
		return false;
	}

	/**
	 * method to return PieceConstant for Fox
	 * 
	 * @return string constant of Fox
	 */
	@Override
	public String toString() {
		return PieceID.FOX;
	}
}