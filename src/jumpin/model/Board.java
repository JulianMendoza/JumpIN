package jumpin.model;

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
		selectedPiece = board[pos.getX()][pos.getY()].getPiece();
		return selectedPiece; 
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
	
}
