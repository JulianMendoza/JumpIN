package jumpin.model.move;

import jumpin.model.util.Position;

/**
 * A class for representing the positional moves
 * 
 * @author Giuseppe
 *
 */
public class Move {

	private Position oldPos;
	private Position newPos;

	public Move(Position oldPos, Position newPos) {
		this.oldPos = oldPos;
		this.newPos = newPos;
	}

	public Position getNewPos() {
		return newPos;
	}

	public Position getOldPos() {
		return oldPos;
	}

}
