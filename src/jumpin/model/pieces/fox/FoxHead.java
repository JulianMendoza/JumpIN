package jumpin.model.pieces.fox;

import jumpin.model.constants.Orientation;
import jumpin.model.pieces.Piece;

public class FoxHead extends Piece{

	private FoxTail tail;
	
	public FoxHead(Orientation orientation) {
		super(orientation);
		tail = new FoxTail(orientation, this);
	}
	
	public FoxTail getTail() {
		return tail;
	}
	

}
