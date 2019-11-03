package jumpin.controller;

import jumpin.controller.listener.BoardListener;
import jumpin.controller.listener.PieceListener;
import jumpin.model.GameModel;
import jumpin.view.GameView;

public class GameController {

	private GameModel model;
	private GameView view;

	public GameController(GameModel model, GameView view) {
		this.view = view;
		this.model = model;

		view.addPieceListener(new PieceListener(model, view));
		view.addBoardListener(new BoardListener(model, view));
	}

	public void launch() {
		view.setVisible(true);
	}

}
