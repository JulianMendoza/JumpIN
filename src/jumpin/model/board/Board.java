package jumpin.model.board;

import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.constants.BoardConstants;
import jumpin.model.move.Move;
import jumpin.model.piece.Piece;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.Position;

/**
 * A class that gets and mutates attributes of the game board
 * 
 * @author Giuseppe
 */
public class Board {

	private BoardModel model;
	/**
	 * Currently selected piece on the board
	 */
	private Piece selectedPiece;
	/**
	 * Currently selected position on the board
	 */
	private Position selectedPosition;
	
	/**
	 * Constructs the board
	 */
	public Board() {
		model = new BoardModel(BoardUtilities.createDefaultBoardModel());
	}
	
	/**
	 * Gets the piece in the specified position
	 * 
	 * @param pos	position of the piece
	 * @return	piece in the specified position
	 */
	public Piece selectPiece(Position pos) {
		selectedPosition = pos;
		selectedPiece = model.getTile(pos.getX(), pos.getY()).getPiece();
		return selectedPiece;
	}
	
	/**
	 * Gets the tile in the specified position
	 * 
	 * @param pos	position of the tile
	 * @return	tile in the specified position
	 */
	public Tile getTile(Position pos) {
		return model.getTile(pos.getX(), pos.getY());
	}

	/**
	 * Assigns a piece to the specified position
	 * 
	 * @param pos	the specified position
	 * @param piece	piece to be assigned
	 */
	public void assignPiece(Position pos, Piece piece) {
		model.assignPiece(pos.getX(), pos.getY(), piece);
	}
	
	/**
	 * Clears the tile at the specified position
	 * 
	 * @param pos position of tile to clear
	 */
	public void clearTile(Position pos) {
		getTile(pos).clear();
	}
	
	/**
	 * Notify board listeners and delegate board update
	 * 
	 * @param move	move of a piece
	 */
	public void updateBoard(Move move) {
		Piece movePiece = getTile(move.getOldPos()).getPiece();
		if (getTile(move.getNewPos()) instanceof RabbitHole) {
			this.notify(BoardModelEvent.ON_RABBIT_HOLE);
		} else if (getTile(move.getOldPos()) instanceof RabbitHole) {
			this.notify(BoardModelEvent.OFF_RABBIT_HOLE);
		}
		assignPiece(move.getNewPos(), movePiece);
		clearTile(move.getOldPos());
	}
	
	/**
	 * Gets currently selected position on the board
	 * 
	 * @return selected position
	 */
	public Position getSelectedPosition() {
		return selectedPosition;
	}
	
	/**
	 * Gets currently selected piece on the board
	 * 
	 * @return selected piece
	 */
	public Piece getSelectedPiece() {
		return selectedPiece;
	}
	
	/**
	 * Add Board listener
	 * 
	 * @param listener	listener of Board
	 */
	public void addListener(BoardModelListener listener) {
		model.addListener(listener);
	}
	
	/**
	 * Notify listeners of Board
	 * 
	 * @param e the event
	 */
	public void notify(int e) {
		for (BoardModelListener l : model.getListeners()) {
			l.update(e);
		}
	}
	
	/**
	 * Returns text representation of the board
	 */
	public String toString() {
		int width = BoardConstants.WIDTH;
		int height = BoardConstants.HEIGHT;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				str.append(getTile(new Position(j, i)).toString());
				if (j != width - 1) {
					str.append(" ");
				}
			}
			if (i != height - 1) {
				str.append("\n");
			}
		}
		return str.toString();
	}
	
	/**
	 * Gets the model of the board
	 * 
	 * @return	the board model
	 */
	public BoardModel getModel() {
		return model;
	}

}
