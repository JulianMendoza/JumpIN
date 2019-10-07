package jumpin.model.pieces.fox;

import jumpin.model.constants.Orientation;
import jumpin.model.pieces.Piece;

public class FoxTail extends Piece{

	private FoxHead head;
	
	public FoxTail(Orientation orientation, FoxHead head) {
		super(orientation);
		this.head = head;
	}
	
	public FoxHead getHead() {
		return head;
	}

}
