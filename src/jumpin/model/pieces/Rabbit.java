package jumpin.model.pieces;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;

public class Rabbit extends Piece {
	
	public Rabbit() {
		super(Orientation.DYNAMIC);
	}

	public String toString() {
		return PieceConstants.RABBIT;
	}
	
}
