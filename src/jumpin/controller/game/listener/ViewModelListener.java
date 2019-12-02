package jumpin.controller.game.listener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import jumpin.controller.game.GameController;
import jumpin.model.GameModel;
import jumpin.model.board.BoardHistory;
import jumpin.model.board.BoardModelListener;
import jumpin.model.board.event.BoardModelEvent;
import jumpin.model.constants.StateOfGame;
import jumpin.model.structures.move.Move;
import jumpin.model.structures.move.MoveSet;
import jumpin.view.game.GameView;
import jumpin.view.game.board.BoardView;
import jumpin.view.game.menu.MainMenu;
import jumpin.view.game.piece.PieceView;

/**
 * 
 * @author Giuseppe
 *
 */
public class ViewModelListener implements BoardModelListener {

	private GameView view;
	private GameModel model;

	/**
	 * Default constructor for ViewModelListener
	 * 
	 * @param boardView
	 */

	public ViewModelListener(GameController gc) {
		this.model = gc.getModel();
		this.view = gc.getGameView();
	}

	@Override
	public void update(BoardModelEvent e) {
		updateBoard(e);
		updateMenu();
		checkGameState();
	}

	private void updateMenu() {
		MainMenu menu = view.getMainMenu();
		BoardHistory history = model.getBoard().getHistory();
		menu.getHistoryMenu().setRedo(history.hasRedo());
		menu.getHistoryMenu().setUndo(history.hasUndo());
		menu.getGameStateMenu().update(model.getGameState());
		view.repaint();
	}

	private void updateBoard(BoardModelEvent e) {
		BoardView boardView = view.getBoardView();
		MoveSet moveSet = e.getUpdates();
		for (Move move : moveSet) {
			SwingUtilities.invokeLater(new Runnable() { // gui updates should be dispatched to the edt
				@Override
				public void run() {
					PieceView toMove = boardView.getTileView(move.getOldPos()).clearPiece();
					boardView.getTileView(move.getNewPos()).setPiece(toMove);
				}
			});
		}
		boardView.stopHighlighting();
		view.repaint();
	}

	private void checkGameState() {
		if (model.getGameState().getState().equals(StateOfGame.WON)) {
			JOptionPane.showMessageDialog(view, model.getGameState().getState().toString(), "Victory", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
}