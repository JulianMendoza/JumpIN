package jumpin.view.builder.transfer.handler;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import jumpin.view.builder.transfer.TransferablePiece;
import jumpin.view.game.board.tile.TileView;
import jumpin.view.game.piece.PieceView;

/**
 * 
 * @author Giuseppe
 *
 */
public class DragHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5164773908328999100L;

	/**
	 * constructor method
	 */
	public DragHandler() {
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
