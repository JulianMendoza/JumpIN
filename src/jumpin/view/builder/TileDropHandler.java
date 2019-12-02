package jumpin.view.builder;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.TransferHandler;

import jumpin.view.game.board.tile.TileView;
import jumpin.view.game.piece.PieceView;

public class TileDropHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 581970729998717500L;

	@Override
	public boolean canImport(TransferSupport support) {
		return support.isDataFlavorSupported(PieceTransferable.FLAVOR);
	}

	@Override
	public boolean importData(TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}
		// Check target component
		try {
			Object o = support.getTransferable().getTransferData(PieceTransferable.FLAVOR);
			if (o instanceof PieceView && support.getComponent() instanceof TileView) {
				TileView tileView = (TileView) support.getComponent();
				tileView.setPiece((PieceView) o);
				System.out.println(tileView.getPieceView());
			}
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
