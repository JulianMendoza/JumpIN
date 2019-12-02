package jumpin.view.builder.transfer.handler;

import java.awt.Container;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.TransferHandler;

import jumpin.model.constants.PieceID;
import jumpin.model.piece.UniquePiece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.view.builder.BuilderView;
import jumpin.view.builder.menu.menus.TrashCan;
import jumpin.view.builder.transfer.TransferablePiece;
import jumpin.view.builder.transfer.TransferredPiece;
import jumpin.view.factory.DragFactory;
import jumpin.view.game.board.BoardView;
import jumpin.view.game.board.tile.TileView;
import jumpin.view.game.piece.PieceView;

/**
 * 
 * @author Giuseppe
 *
 */
public class DropHandler extends TransferHandler {

	private BuilderView view;

	public DropHandler(BuilderView view) {
		this.view = view;
		addTileDropHandlers(view.getBoardView());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 581970729998717500L;

	@Override
	public boolean canImport(TransferSupport support) {
		if (support.isDataFlavorSupported(TransferablePiece.FLAVOR)) {
			if (support.getComponent() instanceof TileView) {
				TileView tileView = (TileView) support.getComponent();
				return tileView.getModel().isEmpty();
			}
			return support.getComponent() instanceof TrashCan;
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
			if (o instanceof TransferredPiece) {
				if (support.getComponent() instanceof TileView) {
					updateBoard((TileView) support.getComponent(), (TransferredPiece) o);
				} else if (support.getComponent() instanceof TrashCan) {
					trashPiece(((TransferredPiece) o).getOldTile());
				}
				resetIDs(view.getBoardView());
				disableSave();
			}
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private void trashPiece(TileView oldTile) {
		if (oldTile != null) {
			oldTile.getModel().clear();
			oldTile.populate();
			Container c = oldTile.getParent();
			c.validate();
			c.repaint();
		}
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

		view.validate();
		view.repaint();
	}

	private void resetIDs(BoardView boardView) {
		for (TileView tileView : boardView.getTileViews()) {
			if (!tileView.getModel().isEmpty()) {
				if (tileView.getModel().getPiece() instanceof Rabbit) {
					((UniquePiece) tileView.getModel().getPiece()).setPieceID(PieceID.RABBIT);
				} else if (tileView.getModel().getPiece() instanceof Fox) {
					((UniquePiece) tileView.getModel().getPiece()).setPieceID(PieceID.FOX);
				}
			}
		}
	}

	private void disableSave() {
		view.getMenu().getMenu().setSaveEnabled(false);
	}

	private void addTileDropHandlers(BoardView boardView) {
		for (TileView tileView : boardView.getTileViews()) {
			tileView.setTransferHandler(this);
		}
	}

}
