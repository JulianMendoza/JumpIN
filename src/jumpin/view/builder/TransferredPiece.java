package jumpin.view.builder;

import java.io.Serializable;

import jumpin.util.Pair;
import jumpin.view.game.board.tile.TileView;
import jumpin.view.game.piece.PieceView;

public class TransferredPiece extends Pair<PieceView, TileView> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2739469193592266670L;

	public TransferredPiece(PieceView pieceView, TileView tileView) {
		super(pieceView, tileView);
	}

	public TileView getOldTile() {
		return getSecond();
	}

	public PieceView getPiece() {
		return getFirst();
	}

}
