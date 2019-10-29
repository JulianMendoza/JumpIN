package jumpin.model.move;

import jumpin.model.util.Position;
import jumpin.util.Pair;

/**
 * A class for representing the positional moves
 * 
 * @author Giuseppe
 *
 */
public class Move {

	private Position oldPos;
	private Position newPos;

	/**
	 * Constructor for the movement of a piece
	 * 
	 * @param oldPos Old position of piece
	 * @param newPos New position of piece
	 */
	public Move(Position oldPos, Position newPos) {
		this.oldPos = oldPos;
		this.newPos = newPos;
	}
	public Pair<Integer,Integer> getIncrement(){
		Pair<Integer,Integer> increment = new Pair<Integer, Integer>(newPos.getX() - oldPos.getX(), newPos.getY() - oldPos.getY());
		return increment;
		
	}
	/**
	 * method for getting new position of a piece
	 * 
	 * @return new position of a piece
	 */
	public Position getNewPos() {
		return newPos;
	}

	/**
	 * method for getting previous position of a piece
	 * 
	 * @return previous position
	 */
	public Position getOldPos() {
		return oldPos;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o instanceof Move) {
			Move move = (Move) o;
			return move.getNewPos() == this.getNewPos() && move.getOldPos() == this.getOldPos();
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return oldPos.hashCode() * 37 + newPos.hashCode() * 17;
	}

}