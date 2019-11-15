package jumpin.model.board.tile;

import jumpin.model.constants.PieceID;
import jumpin.model.piece.Piece;

/**
 * Parent class for all tiles on the board
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class Tile {

	/**
	 * The piece in this tile
	 */
	private Piece piece;

	/**
	 * Clears the piece in this tile
	 */
	public void clear() {
		piece = null;
	}

	/**
	 * Checks if the tile is empty
	 * 
	 * @return true if it is empty, otherwise false
	 */
	public boolean isEmpty() {
		return piece == null;
	}

	/**
	 * Gets the piece in this tile
	 * 
	 * @return piece in this tile
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Assigns a piece to this tile
	 * 
	 * @param piece piece to be assigned
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * Generates string representation of this tile
	 */
	@Override
	public String toString() {
		String pieceName = PieceID.EMPTY;
		if (piece != null) {
			StringBuilder s = new StringBuilder(piece.toString().toUpperCase());
			s.setLength(3);
			pieceName = s.toString();
		}
		return "[ " + pieceName + " ]";
	}
	
	
}