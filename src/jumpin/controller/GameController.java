package jumpin.controller;

import jumpin.model.GameModel;
import jumpin.view.GameView;

public class GameController {

	private GameModel model;
	private GameView view;

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

}
