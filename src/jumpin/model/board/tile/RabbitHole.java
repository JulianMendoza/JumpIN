package jumpin.model.board.tile;

import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.Position;

/**
 * Child class for all rabbit holes on the board
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class RabbitHole extends Tile {

	/**
	 * Constructs a rabbit hole
	 */
	public RabbitHole(Position position) {
		super(position);
	}

	/**
	 * Checks if the hole has a rabbit in it
	 * 
	 * @return true if a rabbit is in the hole, otherwise false
	 */
	public boolean isFilled() {
		if (getPiece() != null) {
			return getPiece() instanceof Rabbit;
		}
		return false;
	}

	/**
	 * Generates a string representation of a rabbit hole
	 */
	@Override
	public String toString() {
		return super.toString().replace('[', '{').replace(']', '}');

	}

}