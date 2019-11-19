package jumpin.model.factory;

import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;

/**
 * Factory for creating piece components
 * if there is extra work to do to create a component it goes here
 * (there is none currently)
 * 
 * @author Giuseppe
 *
 */
public class PieceFactory {

	public static Fox createFox(FoxPart part, Orientation orientation, String pieceID) {
		return new Fox(part, orientation, pieceID);
	}

	public static Rabbit createRabbit(String pieceID) {
		return new Rabbit(pieceID);
	}

	public static Mushroom createMushroom() {
		return new Mushroom();
	}
}
