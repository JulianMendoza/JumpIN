package jumpin.model.piece.pieces;

import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.piece.Piece;
import jumpin.model.piece.UniquePiece;

public class Fox extends Piece implements UniquePiece {

	private String pieceID;
	private FoxPart part;

	public Fox(FoxPart part, Orientation orientation, String pieceID) {
		super(orientation);
		this.pieceID = pieceID;
		this.part = part;
	}

	public FoxPart getPart() {
		return part;
	}

	@Override
	public String getPieceID() {
		return pieceID;
	}

	public boolean isSameFox(Piece piece) {
		if (piece instanceof Fox) {
			Fox fox = (Fox) piece;
			return fox.getPieceID().equals(pieceID);
		}
		return false;
	}

	public String toString() {
		return PieceConstants.FOX;
	}
}
