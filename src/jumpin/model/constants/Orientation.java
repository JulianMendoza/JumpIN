package jumpin.model.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumeration for representing the (orientation) directions that foxes may move
 * in
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public enum Orientation {

	NORTH_SOUTH, EAST_WEST, STATIC, DYNAMIC;

	/**
	 * Checks if the specified direction is valid
	 * 
	 * @param direction direction the piece may move in
	 * @return true if it is dynamic, east-west, or north-south, otherwise false
	 */
	public static Orientation getOrientation(String orientation) {
		switch(orientation) {
		case "NORTH_SOUTH":return NORTH_SOUTH;
		case "EAST_WEST": return EAST_WEST; 
		}
		return null;
	}
	public boolean isValidDirection(Direction direction) {
		switch (this) {
		case DYNAMIC:
			return true;
		case EAST_WEST:
			if (direction.equals(Direction.EAST) || direction.equals(Direction.WEST)) {
				return true;
			}
			break;
		case NORTH_SOUTH:
			if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
				return true;
			}
			break;
		case STATIC:
			return false;
		default:
			break;
		}
		return false;
	}

	public List<Direction> getValidDirections() {
		List<Direction> validDirections = new ArrayList<Direction>();

		switch (this) {
		case DYNAMIC:
			validDirections.addAll(Arrays.asList(Direction.values()));
			break;
		case EAST_WEST:
			validDirections.add(Direction.EAST);
			validDirections.add(Direction.WEST);
			break;
		case NORTH_SOUTH:
			validDirections.add(Direction.NORTH);
			validDirections.add(Direction.SOUTH);
			break;
		default:
			break;
		}

		return validDirections;
	}
}