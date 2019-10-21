package jumpin.model.board;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.tile.Tile;
import jumpin.model.piece.Piece;

/**
 * Model of the Tiles on the board and its listeners
 * 
 * @author Giuseppe, Julian
 * @documentation Cameron Davis
 */
public class BoardModel {
	private List<BoardModelListener> listeners;
	private Tile[][] model;

	/**
	 * Constructs the board's model
	 * 
	 * @param model model of the board (2D array of tiles)
	 */
	public BoardModel(Tile[][] model) {
		this.model = model;
		listeners = new ArrayList<BoardModelListener>();
	}

	/**
	 * Gets the BoardModel listeners
	 * 
	 * @return list of listeners
	 */
	public List<BoardModelListener> getListeners() {
		return listeners;
	}

	/**
	 * Add a BoardModel listener
	 * 
	 * @param l the listener to add
	 */
	public void addListener(BoardModelListener l) {
		listeners.add(l);
	}

	/**
	 * Gets the tile on the board at the specified position
	 * 
	 * @param x x-coordinate of the tile
	 * @param y y-coordinate of the tile
	 * @return tile at specified position
	 */
	public Tile getTile(int x, int y) {
		try {
			return model[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public int getWidth() {
		return model.length;
	}

	public int getHeight() {
		return model[0].length;
	}

	/**
	 * Assigns a piece to the specified position on the board
	 * 
	 * @param x     x-coordinate of position
	 * @param y     y-coordinate of position
	 * @param piece the piece to be placed
	 */
	public void assignPiece(int x, int y, Piece piece) {
		model[x][y].setPiece(piece);
	}

}