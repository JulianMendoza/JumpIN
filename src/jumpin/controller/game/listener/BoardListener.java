package jumpin.controller.game.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import jumpin.controller.game.GameController;
import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.board.util.BoardUtilities;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.logic.FoxLogic;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.structures.move.Move;
import jumpin.model.structures.move.MoveSet;
import jumpin.view.game.GameView;
import jumpin.view.game.board.tile.TileView;

/**
 * 
 * @author Giuseppe, Julian
 *
 */
public class BoardListener implements MouseListener {

	private GameModel model;
	private GameView view;

	/**
	 * Default Constructor for BoardListener
	 * 
	 * @param model
	 * @param view
	 */

	public BoardListener(GameController gc) {
		this.model = gc.getModel();
		this.view = gc.getGameView();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Board board = model.getBoard();
		if (e.getSource() instanceof TileView) {
			TileView tileView = (TileView) e.getSource();

			Move move = new Move(board.getSelectedPosition(), view.getBoardView().getPosition(tileView)); // try an initial move
			if (tileView.getModel().isEmpty() && board.getSelectedPiece() != null) { // can't click on an empty tile
				if (board.getSelectedPiece() instanceof Fox) {
					for (MoveSet m : BoardUtilities.getValidMoves(board)) { // find a valid moveset
						if (m.contains(new Move(board.getSelectedPosition(), view.getBoardView().getPosition(tileView)))) {
							doMove(move); // no refactoring needs to be done
							break;
						} else if (m.contains(new Move(FoxLogic.getOtherFoxPosition(board, (Fox) board.getSelectedPiece()), view.getBoardView().getPosition(tileView)))) { // try the other fox piece
							model.getBoard().selectPiece(FoxLogic.getOtherFoxPosition(board, (Fox) board.getSelectedPiece()));
							doMove(new Move(board.getSelectedPosition(), view.getBoardView().getPosition(tileView)));
							break;
						}
					}
				} else {
					doMove(move);
				}

			}
			view.getBoardView().stopHighlighting();
			model.getBoard().deselectPiece();
		}
	}

	/**
	 * method to move piece on board using solver
	 * @param move
	 */
	private void doMove(Move move) {
		try {
			model.getBoard().movePiece(move);
			view.getMainMenu().getSolverMenu().resetSolution();
		} catch (IllegalMoveException e) {
			model.getBoard().deselectPiece();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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