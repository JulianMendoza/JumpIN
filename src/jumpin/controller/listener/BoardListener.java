package jumpin.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import jumpin.model.GameModel;
import jumpin.view.GameView;
import jumpin.view.board.tile.TileView;

public class BoardListener implements MouseListener {

	private GameModel model;
	private GameView view;

	public BoardListener(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
