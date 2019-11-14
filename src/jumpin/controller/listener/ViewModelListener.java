package jumpin.controller.listener;

import javax.swing.SwingUtilities;

import jumpin.model.GameModel;
import jumpin.model.board.BoardHistory;
import jumpin.model.board.BoardModelListener;
import jumpin.model.board.event.BoardModelEvent;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.view.GameView;
import jumpin.view.board.BoardView;
import jumpin.view.menu.MainMenu;
import jumpin.view.piece.PieceView;

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
	public ViewModelListener(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void update(BoardModelEvent e) {
		updateBoard(e);
		updateMenu();
		view.repaint();
	}

	private void updateMenu() {
		MainMenu menu = view.getMainMenu();
		BoardHistory history = model.getBoard().getHistory();
		
		menu.setRedo(history.hasRedo());
		menu.setUndo(history.hasUndo());
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
	}
 	
}
