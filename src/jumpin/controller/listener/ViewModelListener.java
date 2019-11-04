package jumpin.controller.listener;

import javax.swing.SwingUtilities;

import jumpin.model.board.BoardModelListener;
import jumpin.model.board.event.BoardModelEvent;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.view.board.BoardView;
import jumpin.view.piece.PieceView;

/**
 * 
 * @author Giuseppe
 *
 */
public class ViewModelListener implements BoardModelListener {

	private BoardView boardView;

	public ViewModelListener(BoardView boardView) {
		this.boardView = boardView;
	}

	@Override
	public void update(BoardModelEvent e) {
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
	}

}
