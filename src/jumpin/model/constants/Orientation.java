package jumpin.model.constants;

/**
 * Enumeration for representing the (orientation) directions that foxes may move in
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public enum Orientation {

	NORTH_SOUTH, EAST_WEST, STATIC, DYNAMIC;
	
	/**
	 * Checks if the specified direction the foxes move in is valid
	 * 
	 * @param direction direction the fox may move in
	 * @return true if it is dynamic, east-west, or north-south, otherwise false
	 */
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
}
