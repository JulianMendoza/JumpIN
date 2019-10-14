package jumpin.model.board;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.constants.BoardConstants;
import jumpin.model.move.Move;
import jumpin.model.piece.Piece;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.Position;
import jumpin.model.util.RabbitHoleListener;

public class Board {

	private BoardModel model;
	private Piece selectedPiece;
	private Position selectedPosition;

	public Board() {
		model = new BoardModel(BoardUtilities.createDefaultBoardModel());
	}

	public Piece selectPiece(Position pos) {
		selectedPosition = pos;
		selectedPiece = model.getTile(pos.getX(), pos.getY()).getPiece();
		return selectedPiece;
	}

	public Tile getTile(Position pos) {
		return model.getTile(pos.getX(), pos.getY());
	}

	public void assignPiece(Position pos, Piece piece) {
		model.assignPiece(pos.getX(), pos.getY(), piece);
	}

	public void clearTile(Position pos) {
		getTile(pos).clear();
	}

	public void updateBoard(Move move) {
		Piece movePiece = getTile(move.getOldPos()).getPiece();
		if (getTile(move.getNewPos()) instanceof RabbitHole) {
			this.notify(RabbitHoleEvent.ON);
		} else if (getTile(move.getOldPos()) instanceof RabbitHole) {
			this.notify(RabbitHoleEvent.OFF);
		}
		assignPiece(move.getNewPos(), movePiece);
		clearTile(move.getOldPos());
	}

	public Position getSelectedPosition() {
		return selectedPosition;
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void addListener(RabbitHoleListener listener) {
		model.addListener(listener);
	}

	public void notify(int e) {
		for (RabbitHoleListener l : model.getListeners()) {
			l.update(e);
		}
	}

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
	
	public BoardModel getModel() {
		return model;
	}

}
