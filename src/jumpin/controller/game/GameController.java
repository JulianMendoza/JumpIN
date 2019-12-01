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
import jumpin.view.level.LevelLoader;

/**
 * 
 * @author Giuseppe
 *
 */
public class GameController {

	private GameModel model;
	private View view;
	private GameView gameView;
	private boolean levelLoaded;

	public GameController() {
		this.model = new GameModel();
		this.gameView = new GameView(model);
		levelLoaded=false;
	}
	
	public GameView getGameView() {
		return gameView;
	}
	
	public GameModel getModel() {
		return model;
	}

	public void launch() {
		if(levelLoaded) {
			initializeListeners();
			gameView.getMainMenu().initialize(model);
			splash();
		}else {
			//generate a random level
			System.out.println("TODO");
			System.exit(0);
		}
		levelLoaded=false;
	}
	/*
	 * Internal load from the game
	 */
	public void handleload(File f) {
		gameView.dispose();
		this.model = new GameModel();
		this.view = new View(model,f);
		this.gameView = view.getGameView();
		levelLoaded=true;
		launch();
	}
	/*
	 * Load from the menu
	 */
	public void loadLevel() {
		File f=new LevelLoader(model, gameView).launchChooser(false);
		if(f!=null) {
			this.model = new GameModel();
			this.view = new View(model,f);
			this.gameView = view.getGameView();
			levelLoaded=true;
		}
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

	public void handleGeneration(GameModel model, GameView view) {
		this.model=model;
		this.gameView=view;
		
		levelLoaded=true;
	}


}
