package jumpin.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.move.Move;
import jumpin.view.GameView;
import jumpin.view.board.tile.TileView;

public class BoardListener implements MouseListener {

	private GameModel model;
	private GameView view;

	public BoardListener(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Board board = model.getBoard();
		if (e.getSource() instanceof TileView) {
			TileView tileView = (TileView) e.getSource();

			Move move = new Move(board.getSelectedPosition(), view.getBoardView().getPosition(tileView));
			if (tileView.getModel().isEmpty() && board.getSelectedPiece() != null) {
				doMove(move);
			}
		}
	}

	private void doMove(Move move) {
		try {
			model.getBoard().movePiece(move);
		} catch (IllegalMoveException e) {
			model.getBoard().deselectPiece();
		}
		// view.getBoardView().stopHighlighting();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
