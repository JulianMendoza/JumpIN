package jumpin.model.structures;

import java.io.Serializable;
import java.text.ParseException;

import jumpin.model.board.util.BoardUtilities;
import jumpin.model.constants.Direction;

/**
 * A class for representing x and y positions on the board
 * 
 * @author Giuseppe
 *
 */
public class Position implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5242366384821817264L;
	/**
	 * 
	 */
	private int x;
	private int y;

	/**
	 * Indexed from 0-4
	 * 
	 * @param x position x of the board starting from the left
	 * @param y position y of the board starting from the top
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

	/**
	 * 
	 * @param direction The direction of the piece
	 * @return The position of the next position based on the piece's orientation.
	 */
	public Position nextPosition(Direction direction) {
		return nextPosition(direction, 1);
	}

	/**
	 * Traverse backwards (If the direction is East move the position West)
	 * 
	 * @param direction The direction of the piece
	 * @return The position of the previous position based on the piece's
	 *         orientation.
	 */
	public Position prevPosition(Direction direction) {
		return prevPosition(direction, 1);
	}

	/**
	 * 
	 * @param direction The direction of the piece
	 * @return The position of the next position based on the piece's orientation.
	 */
	public Position nextPosition(Direction direction, int distance) {
		int x = this.x;
		int y = this.y;
		if (direction.isEastWest()) {
			if (BoardUtilities.isPositive(direction)) {
				x += distance;
			} else {
				x -= distance;
			}
		} else if (direction.isNorthSouth()) {
			if (BoardUtilities.isPositive(direction)) {
				y += distance;
			} else {
				y -= distance;
			}
		}
		return new Position(x, y);
	}

	/**
	 * Traverse backwards (If the direction is East move the position West)
	 * 
	 * @param direction The direction of the piece
	 * @return The position of the previous position based on the piece's
	 *         orientation.
	 */
	public Position prevPosition(Direction direction, int distance) {
		int x = this.x;
		int y = this.y;
		if (direction.isEastWest()) {
			if (BoardUtilities.isPositive(direction)) {
				x += distance;
			} else {
				x += distance;
			}
		} else if (direction.isNorthSouth()) {
			if (BoardUtilities.isPositive(direction)) {
				y -= distance;
			} else {
				y += distance;
			}
		}
		return new Position(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o == this) {
			return true;
		} else {
			if (o instanceof Position) {
				Position pos = (Position) o;
				return pos.getX() == this.getX() && pos.getY() == this.getY();
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = 31 * hashCode + x;
		hashCode = 31 * hashCode + y;
		return hashCode;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 *
	 * String parser that allows coordinates with or without brackets.
	 * 
	 * @param str Input string of the user.
	 * @return A position of the parsed string
	 * @throws ParseException If the user did not use integers
	 */
	public static Position parseString(String str) throws ParseException {
		if (str.startsWith("(") && str.endsWith(")")) {
			str = str.replace("(", "").replace(")", "");
		}

		String[] points = str.split(",");
		if (points.length == 2) {
			try {
				int x = Integer.parseInt(points[0]);
				int y = Integer.parseInt(points[1]);

				return new Position(x, y);
			} catch (NumberFormatException e) {
				throw new ParseException("Not integers", 0);
			}
		}

		throw new ParseException("Incorrect formatting", 0);
	}

	@Override
	public Position clone() throws CloneNotSupportedException {
		return (Position) super.clone();

	}

}