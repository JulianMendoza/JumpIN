package jumpin.view.builder.transfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import jumpin.view.game.board.tile.TileView;
import jumpin.view.game.piece.PieceView;

/**
 * 
 * @author Giuseppe
 *
 */
public class TransferablePiece implements Transferable {

	private TransferredPiece data;

	public static final DataFlavor FLAVOR = new DataFlavor(Object.class, "TransferredPiece");

	/**
	 * constructor method
	 * @param pieceView
	 * @param tileView
	 */
	public TransferablePiece(PieceView pieceView, TileView tileView) {
		data = new TransferredPiece(pieceView, tileView);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { FLAVOR };
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(FLAVOR);
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return data;
	}

}
