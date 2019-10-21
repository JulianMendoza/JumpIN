package jumpin.view.board;

import javax.swing.JPanel;

import jumpin.view.constants.ViewConstants;
import jumpin.view.piece.PieceView;

public class TileView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9177793025684748087L;

	public TileView() {
		setBounds(0, 0, ViewConstants.TILE_WIDTH, ViewConstants.TILE_HEIGHT);
	}
	
	public void setPiece(PieceView pieceView) {
		add(pieceView);
	}
	
	public void clearPiece() {
		removeAll();
	}

}
