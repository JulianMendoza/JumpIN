package jumpin.controller;

import jumpin.controller.listener.PieceListener;
import jumpin.model.GameModel;
import jumpin.view.GameView;

public class GameController {
<<<<<<< HEAD
=======

	private GameModel model;
	private GameView view;
>>>>>>> branch 'gui' of https://github.com/JulianMendoza/JumpIN.git

	public GameController(GameModel model, GameView view) {

		view.addPieceListener(new PieceListener(model, view));

	}

}
