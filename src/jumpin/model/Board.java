package jumpin.model;

import jumpin.model.util.BoardUtilities;

public class Board {

	private Tile[][] board;
	
	public Board() {
		board = BoardUtilities.createDefaultBoard();
	}

	public Tile[][] getBoard() {
		return board;
	}
	
	public Piece selectPiece(Position pos) {
		return board[pos.getX()][pos.getY()].getPiece();
	}
	
	public Tile getTile(Position pos) {
		return board[pos.getX()][pos.getY()];
	}
	
	public void setTile(Position pos, Piece piece) {
		board[pos.getX()][pos.getY()].setPiece(piece);
	}
	
	public void clearTile(Position pos) {
		getTile(pos).clear();
	}
	
	public void updateBoard(Position oldPos, Position newPos) {
		Piece movePiece = getTile(oldPos).getPiece();
		setTile(newPos, movePiece);
		clearTile(oldPos);
	}
	
}
