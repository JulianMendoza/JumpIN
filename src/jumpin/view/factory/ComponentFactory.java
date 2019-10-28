package jumpin.view.factory;

import jumpin.model.board.tile.Tile;
import jumpin.model.piece.Piece;
import jumpin.view.board.tile.TileView;
import jumpin.view.piece.PieceView;

public class ComponentFactory {

	public static TileView createTileView(Tile tile) {
		return new TileView(tile);
	}

	public static PieceView createPieceView(Piece piece) {
		return new PieceView(piece);
	}

}
