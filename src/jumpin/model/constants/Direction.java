package jumpin.model.constants;

public enum Direction {

	NORTH, SOUTH, EAST, WEST;

	public boolean isEastWest() {
		switch(this) {
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
	
	public boolean isNorthSouth() {
		switch(this) {
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
	
	public static Direction parseString(String direction) {
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
		return null;
	}
	
	public String toString() {
		switch(this) {
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
