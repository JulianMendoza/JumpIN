package jumpin.view.factory;

import jumpin.controller.builder.listener.DragListener;
import jumpin.view.builder.PieceDropHandler;
import jumpin.view.game.piece.PieceView;

public class DragFactory {

	public static PieceView makeDraggablePiece(PieceView pieceView) {
		pieceView.setTransferHandler(new PieceDropHandler());
		pieceView.addMouseListener(new DragListener());
		return pieceView;
	}

}
