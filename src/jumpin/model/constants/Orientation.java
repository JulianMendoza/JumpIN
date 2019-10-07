package jumpin.model.constants;

public enum Orientation {

	NORTH_SOUTH, EAST_WEST, STATIC, DYNAMIC;
	
	public boolean isValidDirection(Direction direction) {
		switch(this) {
		case DYNAMIC:
			return true;
		case EAST_WEST:
			if(direction.equals(Direction.EAST) || direction.equals(Direction.WEST)) {
				return true;
			}
			break;
		case NORTH_SOUTH:
			if(direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
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
