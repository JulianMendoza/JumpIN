package jumpin.controller;

import jumpin.controller.listener.PieceListener;
import jumpin.model.GameModel;
import jumpin.view.GameView;

public class GameController {

	public GameController(GameModel model, GameView view) {

		view.addPieceListener(new PieceListener(model, view));

	}

}
