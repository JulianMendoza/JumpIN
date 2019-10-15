package jumpin.model.piece.pieces;

import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.piece.Piece;
import jumpin.model.piece.UniquePiece;

/**
 * A class for representing the fox piece
 * @author Giuseppe
 *
 */
public class Fox extends Piece implements UniquePiece {

	private String pieceID;
	private FoxPart part;

	/**
	 * Constructs a fox piece 
	 * 
	 * @param part Represents the different parts of a fox
	 * @param orientation Direction that a fox can move
	 * @param pieceID String representation of fox piece number
	 */
	public Fox(FoxPart part, Orientation orientation, String pieceID) {
		super(orientation);
		this.pieceID = pieceID;
		this.part = part;
	}

	/**
	 * method for getting part for fox piece
	 * 
	 * @return parts of fox piece
	 */
	public FoxPart getPart() {
		return part;
	}

	/**
	 * method for getting fox piece id
	 * 
	 * @return fox ID number
	 */
	@Override
	public String getPieceID() {
		return pieceID;
	}

	/**
	 * method to check if fox piece
	 * 
	 * @param piece 
	 * @return true if fox piece is the same
	 */
	public boolean isSameFox(Piece piece) {
		if (piece instanceof Fox) {
			Fox fox = (Fox) piece;
			return fox.getPieceID().equals(pieceID);
		}
		return false;
	}

	/**
	 * method to return PieceConstant for fox
	 * 
	 * @return string constant of fox
	 */
	public String toString() {
		return PieceConstants.FOX;
	}
}
