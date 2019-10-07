package jumpin.model;

import jumpin.model.constants.Direction;
import jumpin.model.constants.Orientation;

public class Piece {

	private Orientation orientation;

	public Piece(Orientation orientation) {
		this.orientation = orientation;
	}
	
	public boolean allowsDirection(Direction direction) {
		return orientation.isValidDirection(direction);
	}
	
	
}

