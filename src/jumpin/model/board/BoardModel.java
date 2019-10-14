package jumpin.model.board;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.tile.Tile;
import jumpin.model.piece.Piece;
/**
 * Model of the Tiles on the board and its listeners
 * 
 * @author Giuseppe, Julian
 *
 */
public class BoardModel {
	private List<BoardModelListener> listeners;
	private Tile[][] model;
	
	public BoardModel(Tile[][] model) {
		this.model = model;
		listeners = new ArrayList<BoardModelListener>();
	}
	
	public List<BoardModelListener> getListeners() {
		return listeners;
	}
	
	public void addListener(BoardModelListener l) {
		listeners.add(l);
	}
	
	public Tile getTile(int x, int y) {
		return model[y][x];
	}
	
	public void assignPiece(int x, int y, Piece piece) {
		model[y][x].setPiece(piece);
	}
	

}
