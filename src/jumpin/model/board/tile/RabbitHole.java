package jumpin.model.board.tile;

import jumpin.model.piece.pieces.Rabbit;

/**
 * Child class for all rabbit holes on the board
 * @author Giuseppe
 *
 */
public class RabbitHole extends Tile {

	public RabbitHole() {
		super();
	}

	public boolean isFilled() {
		if(getPiece() != null) {
			return getPiece() instanceof Rabbit;
		}
		return false;
	}

	public String toString() {
		return super.toString().replace('[', '{').replace(']', '}');

	}

}
