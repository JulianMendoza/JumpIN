package ca.carleton.sysc3110.jumpin.model.piece.pieces;

import ca.carleton.sysc3110.jumpin.model.constants.Orientation;
import ca.carleton.sysc3110.jumpin.model.constants.PieceConstants;
import ca.carleton.sysc3110.jumpin.model.piece.Piece;

public class Mushroom extends Piece {

	public Mushroom() {
		super(Orientation.STATIC);
	}
	
	public String toString() {
		return PieceConstants.MUSHROOM;
	}

}
