package jumpin.model;

import jumpin.model.constants.Direction;
import jumpin.model.util.BoardUtilities;

public class Position {

	private int x;
	private int y;
	
	/**
	 * Indexed from 0-5
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Position nextPosition(Direction direction) {
		int x = this.x;
		int y = this.y;
		if(direction.isEastWest()) {
			if(BoardUtilities.isPositive(direction)) {
				x++;
			} else {
				x--;
			}
		} else if(direction.isNorthSouth()) {
			if(BoardUtilities.isPositive(direction)) {
				y++;
			} else {
				y--;
			}
		}
		return new Position(x, y);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if (o == this) {
			return true;
		} else {
			Position pos = (Position) o;
			return pos.getX() == this.getX() && pos.getY() == this.getY();
		}
	}
	
}
