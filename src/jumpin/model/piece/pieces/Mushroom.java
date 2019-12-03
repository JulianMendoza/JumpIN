package jumpin.model.piece.pieces;

import java.io.Serializable;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.Piece;

/**
 * A class for representing the mushroom piece
 * 
 * @author Giuseppe
 *
 */
public class Mushroom extends Piece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1621070747898972743L;

	/**
	 * Constructs a Mushroom piece that has a static orientation
	 */
	public Mushroom() {
		super(Orientation.STATIC);
	}

	/**
	 * method to return PieceConstant for Mushroom
	 * 
	 * @return string constant for mushroom
	 */
	@Override
	public String toString() {
		return PieceID.MUSHROOM;
	}

}