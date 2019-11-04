package jumpin.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.StateOfGame;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.logic.FoxLogic;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.util.BoardUtilities;
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
				if (board.getSelectedPiece() instanceof Fox) {
					for (MoveSet m : BoardUtilities.getValidMoves(board)) {
						if (m.contains(new Move(board.getSelectedPosition(), view.getBoardView().getPosition(tileView)))) {
							doMove(move);
							break;
						} else if (m.contains(new Move(FoxLogic.getOtherFoxPosition(board, (Fox) board.getSelectedPiece()), view.getBoardView().getPosition(tileView)))) {
							model.getBoard().selectPiece(FoxLogic.getOtherFoxPosition(board, (Fox) board.getSelectedPiece()));
							doMove(new Move(board.getSelectedPosition(), view.getBoardView().getPosition(tileView)));
							break;
						}
					}
				} else {
					doMove(move);
				}

			}
		}
		System.out.println(model);
	}

	private void doMove(Move move) {
		try {
			model.getBoard().movePiece(move);
			checkGameState();

		} catch (IllegalMoveException e) {
			model.getBoard().deselectPiece();
		}
		view.getBoardView().stopHighlighting();
	}

	private void checkGameState() {
		if (model.getGameState().getState().equals(StateOfGame.WON)) {
			JOptionPane.showMessageDialog(view, "GAME WON");

		}
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
