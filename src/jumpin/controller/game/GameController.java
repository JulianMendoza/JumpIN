package jumpin.controller.game;

import java.io.File;

import jumpin.controller.game.listener.BoardListener;
import jumpin.controller.game.listener.MainMenuListener;
import jumpin.controller.game.listener.PieceListener;
import jumpin.controller.game.listener.ViewModelListener;
import jumpin.model.GameModel;
import jumpin.view.View;
import jumpin.view.game.GameView;
import jumpin.view.launch.ScreenSplasher;

/**
 * 
 * @author Giuseppe
 *
 */
public class GameController {

	private GameModel model;
	private View view;
	private GameView gameView;

	public GameController() {
		this.model = new GameModel();
		this.view = new View(model);
		this.gameView = view.getGameView();
	}
	
	public GameView getGameView() {
		return gameView;
	}
	
	public GameModel getModel() {
		return model;
	}

	public void launch() {
		initializeListeners();
		gameView.getMainMenu().initialize(model);
		splash();
	}
	
	public void handleload(File f) {
		gameView.dispose();
		this.model = new GameModel();
		this.view = new View(model,f);
		this.gameView = view.getGameView();
		launch();
	}
	public void splash() {
		ScreenSplasher splasher = new ScreenSplasher(gameView);
		splasher.execute();
	}

	private void initializeListeners() {
		gameView.addPieceListener(new PieceListener(this));
		gameView.addBoardListener(new BoardListener(this));
		gameView.addMenuListener(new MainMenuListener(this));
		model.getBoard().addModelListener(new ViewModelListener(this));
	}

}
