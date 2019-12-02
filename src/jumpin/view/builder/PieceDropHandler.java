package jumpin.view.builder;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import jumpin.view.game.piece.PieceView;

public class PieceDropHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5164773908328999100L;

	public PieceDropHandler() {
		super("text");
	}

	@Override
	protected Transferable createTransferable(JComponent component) {
		if (component instanceof PieceView) {
			return new PieceTransferable((PieceView) component);
		}
		return null;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		return support.isDataFlavorSupported(PieceTransferable.FLAVOR);
	}
}
