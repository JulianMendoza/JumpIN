package jumpin.model.constants;

import java.text.ParseException;

/**
 * Enumeration for representing potential directions of pieces on the board
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public enum Direction {

	NORTH, SOUTH, EAST, WEST;
	
	/**
	 * Checks if the direction of this piece is East or West
	 * 
	 * @return	true if the piece points east or west, otherwise false
	 */
	public boolean isEastWest() {
		switch (this) {
		case WEST:
		case EAST:
			return true;
		case SOUTH:
		case NORTH:
			return false;
		default:
			return false;
		}
	}
	
	/**
	 * Checks if the direction of this piece is North or South
	 * 
	 * @return true if the piece points North or South, otherwise false
	 */
	public boolean isNorthSouth() {
		switch (this) {
		case WEST:
		case EAST:
			return false;
		case SOUTH:
		case NORTH:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * Parses the pieces direction
	 * 
	 * @param direction	direction that this piece points
	 * @return	enum direction corresponding to the string
	 * @throws ParseException	if the string is an invalid direction
	 */
	public static Direction parseString(String direction) throws ParseException {
		switch (direction.toLowerCase()) {
		case "north":
		case "up":
			return NORTH;
		case "south":
		case "down":
			return SOUTH;
		case "west":
		case "left":
			return WEST;
		case "east":
		case "right":
			return EAST;
		}
		throw new ParseException("not a direction", 0);
	}

	/**
	 * Generates a string of the direction
	 */
	public String toString() {
		switch (this) {
		case EAST:
			return "East";
		case NORTH:
			return "North";
		case SOUTH:
			return "South";
		case WEST:
			return "West";
		default:
			return "";

		}
	}

}
