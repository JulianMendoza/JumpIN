package jumpin.model;

import jumpin.model.piece.Piece;

public class Tile {

	private Piece piece;

	public Tile() {
	}

	public void clear() {
		piece = null;
	}
	
	public boolean isEmpty() {
		return piece == null;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}
