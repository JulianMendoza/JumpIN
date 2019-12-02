package jumpin.controller.game;

import java.io.File;

import John.GeneratePrompt;
import jumpin.controller.game.listener.BoardListener;
import jumpin.controller.game.listener.MainMenuListener;
import jumpin.controller.game.listener.PieceListener;
import jumpin.controller.game.listener.ViewModelListener;
import jumpin.controller.launch.LaunchController;
import jumpin.model.GameModel;
import jumpin.view.View;
import jumpin.view.game.GameView;
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
	private GeneratePrompt generatePrompt;
	private LaunchController launchController;

	public GameController(LaunchController lc) {
		this.launchController=lc;
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
	public boolean isLevelLoaded() {
		return levelLoaded;
	}

	public void launch() {
		if(levelLoaded) {
			initializeListeners();
			gameView.getMainMenu().initialize(model);
			splash();
			levelLoaded=false;
		}else {
			handleGeneration();
		}
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
		launchController.splash(gameView);
	}

	public void setRandomLevel(GameView gv,GameModel model,boolean levelLoaded) {
		this.gameView=gv;
		this.model=model;
		this.levelLoaded=levelLoaded;
	}

	public void handleGeneration() {
		generatePrompt = new GeneratePrompt();
		generatePrompt.prompt(this);
	}

	private void initializeListeners() {
		gameView.addPieceListener(new PieceListener(this));
		gameView.addBoardListener(new BoardListener(this));
		gameView.addMenuListener(new MainMenuListener(this));
		model.getBoard().addModelListener(new ViewModelListener(this));
	}

	public void handleWin() {
		gameView.dispose();
		launchController.handleWin();
	}

}
