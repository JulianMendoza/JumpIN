package jumpin.model.board;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.piece.Piece;
import jumpin.model.util.RabbitHoleListener;
/**
 * 
 * @author Giuseppe, Julian
 *
 */
public class BoardModel {
	private List<RabbitHoleListener> listeners;
	private Tile[][] model;
	
	public BoardModel(Tile[][] model) {
		this.model = model;
		listeners = new ArrayList<RabbitHoleListener>();
	}
	
	public List<RabbitHoleListener> getListeners() {
		return listeners;
	}
	
	public void addListener(RabbitHoleListener l) {
		listeners.add(l);
	}
	
	public Tile getTile(int x, int y) {
		return model[y][x];
	}
	
	public void assignPiece(int x, int y, Piece piece) {
		model[y][x].setPiece(piece);
	}
	

}
