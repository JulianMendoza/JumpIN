package jumpin.model.pieces.fox;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.pieces.Piece;

public class Fox extends Piece{

	public Fox(Orientation orientation) {
		super(orientation);
	}

	public String toString() {
		return PieceConstants.FOX;
	}
	
}
