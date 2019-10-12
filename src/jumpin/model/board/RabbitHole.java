package jumpin.model.board;

import jumpin.model.piece.pieces.Rabbit;

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
