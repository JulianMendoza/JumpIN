package jumpin.view.builder;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.TransferHandler;

import jumpin.view.factory.DragFactory;
import jumpin.view.game.board.BoardView;
import jumpin.view.game.board.tile.TileView;
import jumpin.view.game.piece.PieceView;

public class TileDropHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 581970729998717500L;
	private BoardView boardView;

	public TileDropHandler(BoardView boardView) {
		this.boardView = boardView;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if (support.isDataFlavorSupported(TransferablePiece.FLAVOR)) {
			if (support.getComponent() instanceof TileView) {
				TileView tileView = (TileView) support.getComponent();
				return tileView.getModel().isEmpty();
			}
		}
		return false;
	}

	@Override
	public boolean importData(TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}
		// Check target component
		try {
			Object o = support.getTransferable().getTransferData(TransferablePiece.FLAVOR);
			if (o instanceof TransferredPiece && support.getComponent() instanceof TileView) {
				updateBoard((TileView) support.getComponent(), (TransferredPiece) o);
			}
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private void updateBoard(TileView newTile, TransferredPiece transferredPiece) {
		PieceView piece = transferredPiece.getPiece();
		TileView oldTile = transferredPiece.getOldTile();
		if (oldTile != null) {
			oldTile.getModel().clear();
			oldTile.populate();
		}

		newTile.getModel().setPiece(piece.getPiece());
		newTile.populate();
		DragFactory.makeDraggablePiece(newTile.getPieceView());

		boardView.validate();
		boardView.repaint();
	}

}
