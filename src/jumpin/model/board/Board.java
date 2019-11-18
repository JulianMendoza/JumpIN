package jumpin.model.board;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.event.BoardModelEvent;
import jumpin.model.board.event.EventFactory;
import jumpin.model.board.tile.Tile;
import jumpin.model.constants.BoardConstants;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.logic.BoardLogic;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.Piece;
import jumpin.model.solver.Solver;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.Position;

/**
 * A class that gets and mutates attributes of the game board
 * 
 * @author Giuseppe, Julian
 * @documentation Cameron
 */
public class Board implements Cloneable {

	private BoardModel model;
	/**
	 * Currently selected piece on the board
	 */
	private Piece selectedPiece;
	/**
	 * Currently selected position on the board
	 */
	private Position selectedPosition;

	private List<MoveSet> validMoveSets;

	private BoardHistory history;

	private Solver solver;

	/**
	 * Constructs the board
	 * 
	 * @throws CloneNotSupportedException
	 */
	public Board() {
		model = new BoardModel(BoardUtilities.createDefaultBoardModel());
		history = new BoardHistory();
		solver = new Solver(this);
	}

	public void computeSolution(int threshHold) throws CloneNotSupportedException {
		solver.populateMoveTree(threshHold);
	}

	/**
	 * Copy constructor
	 */
	public Board(Board board) {
		this.model = board.model;
		this.selectedPiece = board.selectedPiece;
		this.selectedPosition = board.selectedPosition;
		this.validMoveSets = board.validMoveSets;
		this.history = board.history;
	}

	/**
	 * Gets the piece in the specified position
	 * 
	 * @param pos position of the piece
	 * @throws NoTileException  if the board model is given an invalid position
	 * @throws NoPieceException
	 */
	public void selectPiece(Position pos) {
		Tile tile = model.getTile(pos.getX(), pos.getY());
		if (tile != null && tile.getPiece() != null) {
			selectedPosition = pos;
			selectedPiece = tile.getPiece();
			validMoveSets = BoardLogic.getValidMoves(this);
		}
	}

	public void deselectPiece() {
		selectedPiece = null;
		selectedPosition = null;
		validMoveSets = null;
	}

	/**
	 * Gets the tile in the specified position
	 * 
	 * @param pos position of the tile
	 * @return tile in the specified position
	 */
	public Tile getTile(Position pos) {
		return model.getTile(pos.getX(), pos.getY());
	}

	/**
	 * Assigns a piece to the specified position
	 * 
	 * @param pos   the specified position
	 * @param piece piece to be assigned
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
	 * @param move move of a piece
	 */
	private void update(MoveSet moveSet) {
		notify(EventFactory.generateBoardModelEvent(moveSet, this));
		for (Move move : moveSet) {
			Piece movePiece = getTile(move.getOldPos()).getPiece();
			assignPiece(move.getNewPos(), movePiece);
			clearTile(move.getOldPos());
		}
	}

	public void movePiece(Move move) throws IllegalMoveException {
		this.selectPiece(move.getOldPos());
		MoveSet moves = BoardUtilities.generateMoveSet(move, this);
		if (validMoveSets != null && validMoveSets.contains(moves)) {
			history.add(moves);
			update(moves);
		} else {
			throw new IllegalMoveException();
		}
	}

	public void undoMove() {
		update(history.undo());
	}

	public void redoMove() {
		update(history.redo());
	}

	public List<MoveSet> getValidMoveSets() {
		return validMoveSets;
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
	 * @param listener listener of Board
	 */
	public void addModelListener(BoardModelListener listener) {
		model.addListener(listener);
	}

	/**
	 * Notify listeners of Board
	 * 
	 * @param e the event
	 */
	public void notify(BoardModelEvent e) {
		for (BoardModelListener l : model.getListeners()) {
			l.update(e);
		}
	}

	/**
	 * Returns text representation of the board
	 */
	@Override
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
	 * Checks if the position on the Board is valid
	 * 
	 * @param pos the position on the Board
	 * @return true if the tile at the position is not null, false otherwise
	 */
	public boolean isValidPosition(Position pos) {
		return getTile(pos) != null;
	}

	/**
	 * Gets the model of the board
	 * 
	 * @return the board model
	 */
	public BoardModel getModel() {
		return model;
	}

	public BoardHistory getHistory() {
		return history;
	}

	public void setHistory(BoardHistory history) {
		this.history = history;
	}

	/**
	 * Get board Clone
	 * 
	 */
	@Override
	public Board clone() throws CloneNotSupportedException {
		Board boardCopy = new Board();
		int height = model.getHeight();
		int width = model.getWidth();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				if (!getTile(pos).isEmpty()) {
					boardCopy.assignPiece(pos, getTile(pos).getPiece());
				}

			}
		}
		if (selectedPosition != null) {
			boardCopy.selectPiece(selectedPosition);
		}
		boardCopy.setHistory(history.clone());
		return boardCopy;
	}

	/**
	 * 
	 * @return best moves
	 */
	public List<MoveSet> getBestMoves() {
		return solver.getBestMoves();
	}

	public void solve() throws IllegalMoveException {
		this.movePiece(solver.getBestMoves().get(0).get(0));
		solver.getBestMoves().remove(0);
	}

	public List<MoveSet> getAllValidMoveSets(List<Position> toOmit) throws CloneNotSupportedException {
		List<MoveSet> allValidMoveSets = new ArrayList<MoveSet>();
		Board boardCopy = clone();
		int height = model.getHeight();
		int width = model.getWidth();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				if (toOmit.contains(pos)) {
					continue;
				}
				if (!getTile(pos).isEmpty()) {
					boardCopy.selectPiece(pos);
					for (MoveSet moveSet : boardCopy.getValidMoveSets()) {
						if (!allValidMoveSets.contains(moveSet)) { // dont add duplicate movesets for both pieces of the fox
							allValidMoveSets.add(moveSet);
						}
					}
				}

			}
		}
		return allValidMoveSets;
	}

}