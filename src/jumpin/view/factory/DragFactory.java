package jumpin.view.factory;

import jumpin.controller.builder.listener.DragListener;
import jumpin.view.builder.PieceDragHandler;
import jumpin.view.game.piece.PieceView;

public class DragFactory {

	public static PieceView makeDraggablePiece(PieceView pieceView) {
		pieceView.setTransferHandler(new PieceDragHandler());
		pieceView.addMouseListener(new DragListener());
		return pieceView;
	}

}
