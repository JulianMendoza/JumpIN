package jumpin.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import jumpin.model.GameModel;
import jumpin.model.logic.FoxLogic;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.util.Position;
import jumpin.view.GameView;
import jumpin.view.board.BoardView;
import jumpin.view.board.tile.TileView;
import jumpin.view.piece.PieceView;

/**
 * 
 * @author Giuseppe, Julian
 *
 */
public class PieceListener implements MouseListener {

	private GameModel model;
	private GameView view;

	/**
	 * Default constructor for PieceListener 
	 * 
	 * @param model
	 * @param view
	 */
	public PieceListener(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	// A comment
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		BoardView view = this.view.getBoardView();
		if (e.getSource() instanceof PieceView) {
			PieceView pieceView = (PieceView) e.getSource();
			if (pieceView.getParent() instanceof TileView) {
				TileView selectTile = (TileView) pieceView.getParent();
				Position pos = view.getPosition(selectTile);
				model.getBoard().selectPiece(pos);
				List<TileView> selectTiles = tilesToSelect(selectTile);
				List<TileView> highlightTiles = tilesToHighlight();
				view.highlight(highlightTiles, selectTiles);
			}
		}
	}

	/**
	 * Method to return select tiles
	 * 
	 * @param selectTile
	 * @return select tiles
	 */
	private List<TileView> tilesToSelect(TileView selectTile) {
		BoardView view = this.view.getBoardView();

		List<TileView> selectTiles = new ArrayList<TileView>();
		selectTiles.add(selectTile); // most pieces only have one tile to highlight
		if (selectTile.getModel().getPiece() instanceof Fox) { // except fox - find its other piece
			selectTiles.add(view.getTileView(FoxLogic.getOtherFoxPosition(model.getBoard(), (Fox) selectTile.getModel().getPiece())));
		}
		return selectTiles;
	}

	/**
	 * Method to return the tiles to highlight
	 * 
	 * @return highlightable tiles
	 */
	private List<TileView> tilesToHighlight() {
		BoardView view = this.view.getBoardView();

		List<TileView> highlightTiles = new ArrayList<TileView>();
		for (MoveSet validMoveSets : model.getBoard().getValidMoveSets()) {
			for (Move move : validMoveSets) {
				highlightTiles.add(view.getTileView(move.getNewPos()));
			}
		}
		return highlightTiles;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// comment part 2

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
