package jumpin.model.piece;

import jumpin.model.constants.Orientation;

/**
 * Interface for all unique pieces
 * 
 * @author Giuseppe
 *
 */
public class UniquePiece extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6734532446081110805L;

	public UniquePiece(Orientation orientation, String pieceID) {
		super(orientation);
		setPieceID(pieceID);
	}

	private String pieceID;

	/**
	 * method for getting piece ID
	 * 
	 * @return piece ID
	 */
	public String getPieceID() {
		return pieceID;
	}

	public void setPieceID(String pieceID) {
		this.pieceID = pieceID;
	}

}