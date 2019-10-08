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

	public String toString() {
		String pieceName = "NUL";
		if(piece != null) {
			StringBuilder s = new StringBuilder(piece.toString().toUpperCase());
			s.setLength(3);
			pieceName = s.toString();
		}
		return "[ " + pieceName +  " ]";
	}
	
}
