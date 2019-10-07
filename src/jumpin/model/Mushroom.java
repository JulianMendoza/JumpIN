package jumpin.model;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;

public class Mushroom extends Piece {

	public Mushroom() {
		super(Orientation.STATIC);
	}
	
	public String toString() {
		return PieceConstants.MUSHROOM;
	}

}
