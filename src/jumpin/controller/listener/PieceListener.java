package jumpin.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import jumpin.model.GameModel;
import jumpin.model.exception.NoPieceException;
import jumpin.model.exception.NoTileException;
import jumpin.model.move.Move;
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
				TileView tileView = (TileView) pieceView.getParent();
				Position pos = tileView.getModel().getPosition();
				model.getBoard().selectPiece(pieceView.getModel(), pos);
				List<TileView> tiles = new ArrayList<TileView>();
				try {
					for (Move move : model.getValidMoves(pos)) {
						tiles.add(view.getTileView(move.getNewPos()));
					}
				} catch (NoTileException | NoPieceException e1) {
					e1.printStackTrace();
				}
				view.highlight(tiles);
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
