package jumpin.model;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;

public class Fox extends Piece{

	public Fox(Orientation orientation) {
		super(orientation);
	}

	public String toString() {
		return PieceConstants.FOX;
	}
	
}
