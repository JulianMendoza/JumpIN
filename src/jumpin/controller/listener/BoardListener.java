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
		}
	}

	private void doMove(Move move) {
		try {
			model.getBoard().movePiece(move);
		//	view.getMainMenu().disableShowBestMoves();
			view.getMainMenu().enableFindBestMoves();
			checkGameState();
		} catch (IllegalMoveException e) {
			model.getBoard().deselectPiece();
		}
	}

	/**
	 * Method to check the state of the game
	 * 
	 */
	private void checkGameState() {
		if (model.getGameState().getState().equals(StateOfGame.WON)) {
			JOptionPane.showMessageDialog(view, "GAME WON");
			System.exit(0);
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