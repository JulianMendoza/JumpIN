package jumpin.model.piece.pieces;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.piece.Piece;
import jumpin.model.piece.UniquePiece;

public class Rabbit extends Piece implements UniquePiece {

	private String pieceID;

	public Rabbit(String pieceID) {
		super(Orientation.DYNAMIC);
		this.pieceID = pieceID;
	}

	public String toString() {
		return PieceConstants.RABBIT;
	}

	@Override
	public String getPieceID() {
		return pieceID;
	}

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
