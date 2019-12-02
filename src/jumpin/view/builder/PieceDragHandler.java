package jumpin.view.builder;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import jumpin.view.game.board.tile.TileView;
import jumpin.view.game.piece.PieceView;

public class PieceDragHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5164773908328999100L;

	public PieceDragHandler() {
		super("text");
	}

	@Override
	protected Transferable createTransferable(JComponent component) {
		if (component instanceof PieceView) {
			if (component.getParent() instanceof TileView) {
				return new TransferablePiece((PieceView) component, (TileView) component.getParent());
			} else {
				return new TransferablePiece((PieceView) component, null);
			}
		}
		return null;
	}

	@Override
	public boolean canImport(TransferSupport support) {

		return !(support.getComponent() instanceof PieceView) && support.isDataFlavorSupported(TransferablePiece.FLAVOR);
	}

}