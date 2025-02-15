package jumpin.view.factory;

import jumpin.controller.builder.listener.DragListener;
import jumpin.view.builder.transfer.handler.DragHandler;
import jumpin.view.game.piece.PieceView;

public class DragFactory {

	/**
	 * method to make draggable piece
	 * @param pieceView
	 * @return
	 */
	public static PieceView makeDraggablePiece(PieceView pieceView) {
		pieceView.setTransferHandler(new DragHandler());
		pieceView.addMouseListener(new DragListener());
		return pieceView;
	}

}
