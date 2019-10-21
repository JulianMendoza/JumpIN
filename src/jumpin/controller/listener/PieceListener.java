package jumpin.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import jumpin.model.GameModel;
import jumpin.model.exception.NoPieceException;
import jumpin.model.exception.NoTileException;
import jumpin.model.logic.FoxLogic;
import jumpin.model.move.Move;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.util.Position;
import jumpin.view.GameView;
import jumpin.view.board.BoardView;
import jumpin.view.board.tile.TileView;
import jumpin.view.piece.PieceView;

public class PieceListener implements MouseListener {

	private GameModel model;
	private BoardView view;

	public PieceListener(GameModel model, GameView view) {
		this.model = model;
		this.view = view.getBoardView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof PieceView) {
			PieceView pieceView = (PieceView) e.getSource();
			if (pieceView.getParent() instanceof TileView) {
				TileView selectTile = (TileView) pieceView.getParent();
				Position pos = selectTile.getModel().getPosition();

				model.getBoard().selectPiece(pieceView.getModel(), pos);

				List<TileView> selectTiles = new ArrayList<TileView>();
				selectTiles.add(selectTile); // most pieces only have one tile to highlight
				if (selectTile.getModel().getPiece() instanceof Fox) { // except fox - find its other piece
					selectTiles.add(view.getTileView(FoxLogic.getOtherFoxPosition(model.getBoard(), (Fox) selectTile.getModel().getPiece())));
				}

				List<TileView> highlightTiles = new ArrayList<TileView>();
				try {
					for (Move move : model.getValidMoves(pos)) {
						highlightTiles.add(view.getTileView(move.getNewPos()));
					}
				} catch (NoTileException | NoPieceException e1) {
					e1.printStackTrace();
				}

				view.highlight(highlightTiles, selectTiles);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
