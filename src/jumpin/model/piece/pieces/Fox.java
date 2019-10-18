package jumpin.model.piece.pieces;

import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.piece.Piece;
import jumpin.model.piece.UniquePiece;

/**
 * A class for representing the fox piece
 * 
 * @author Giuseppe
 *
 */
public class Fox extends Piece implements UniquePiece {

	private String pieceID;
	private FoxPart part;

	/**
	 * Constructs a Fox piece
	 * 
	 * @param part        Represents the different parts of a Fox
	 * @param orientation Direction that a Fox can move
	 * @param pieceID     String representation of Fox piece number
	 */
	public Fox(FoxPart part, Orientation orientation, String pieceID) {
		super(orientation);
		this.pieceID = pieceID;
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
	 * method for getting Fox piece id
	 * 
	 * @return fox ID number
	 */
	@Override
	public String getPieceID() {
		return pieceID;
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
			return fox.getPieceID().equals(pieceID);
		}
		return false;
	}

	/**
	 * method to return PieceConstant for Fox
	 * 
	 * @return string constant of Fox
	 */
	public String toString() {
		return PieceConstants.FOX;
	}
}