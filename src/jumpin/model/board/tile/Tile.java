package jumpin.model.board.tile;

import jumpin.model.constants.PieceConstants;
import jumpin.model.piece.Piece;

/**
 * Parent class for all tiles on the board
 * @author Giuseppe
 *
 */
public class Tile {

	private Piece piece;

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
		String pieceName = PieceConstants.EMPTY;
		if (piece != null) {
			StringBuilder s = new StringBuilder(piece.toString().toUpperCase());
			s.setLength(3);
			pieceName = s.toString();
		}
		return "[ " + pieceName + " ]";
	}

}