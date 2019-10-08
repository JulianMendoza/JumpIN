package jumpin.model;

import jumpin.model.constants.BoardConstants;
import jumpin.model.piece.Piece;
import jumpin.model.util.BoardUtilities;

public class Board {

	private Tile[][] board;
	private Piece selectedPiece;
	private Position selectedPosition;

	public Board() {
		board = BoardUtilities.createDefaultBoard();
	}

	public Piece selectPiece(Position pos) {
		selectedPosition = pos;
		selectedPiece = board[pos.getY()][pos.getX()].getPiece();
		return selectedPiece;
	}

	public Tile getTile(Position pos) {
		return board[pos.getY()][pos.getX()];
	}

	public void setTile(Position pos, Piece piece) {
		board[pos.getY()][pos.getX()].setPiece(piece);
	}

	public void clearTile(Position pos) {
		getTile(pos).clear();
	}

	public void updateBoard(Move move) {
		Piece movePiece = getTile(move.getOldPos()).getPiece();
		setTile(move.getNewPos(), movePiece);
		clearTile(move.getOldPos());
	}

	public Position getSelectedPosition() {
		return selectedPosition;
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public String toString() {
		int width = BoardConstants.WIDTH;
		int height = BoardConstants.HEIGHT;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				str.append(getTile(new Position(i, j)).toString());
				if(j != width -1) {
					str.append(" ");
				}
			}
			if (i != height - 1) {
				str.append("\n");
			}
		}
		return str.toString();
	}

}
