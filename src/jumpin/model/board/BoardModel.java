package jumpin.model.board;

import jumpin.model.piece.Piece;

public class BoardModel {

	private Tile[][] model;
	
	public BoardModel(Tile[][] model) {
		this.model = model;
	}
	
	public Tile getTile(int x, int y) {
		return model[y][x];
	}
	
	public void assignPiece(int x, int y, Piece piece) {
		model[y][x].setPiece(piece);
	}
	

}
