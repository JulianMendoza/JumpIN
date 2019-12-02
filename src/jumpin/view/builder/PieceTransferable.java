package jumpin.view.builder;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import jumpin.view.game.piece.PieceView;

public class PieceTransferable implements Transferable {

	private PieceView piece;

	public static final DataFlavor FLAVOR = new DataFlavor(PieceView.class, "Piece");

	public PieceTransferable(PieceView piece) {
		this.piece = piece;
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
		return piece;
	}

}
