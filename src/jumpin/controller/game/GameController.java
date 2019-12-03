package jumpin.controller.game;

import jumpin.controller.game.listener.BoardListener;
import jumpin.controller.game.listener.GameMenuListener;
import jumpin.controller.game.listener.PieceListener;
import jumpin.controller.game.listener.ViewModelListener;
import jumpin.controller.launch.LaunchController;
import jumpin.model.GameModel;
import jumpin.model.file.LevelParser;
import jumpin.view.game.GameView;
import jumpin.view.level.LevelDialog;
import jumpin.view.prompt.DifficultyPrompt;

/**
 * 
 * @author Giuseppe
 *
 */
public class GameController {

	private GameModel model;
	private GameView gameView;
	private boolean levelLoaded;
	private DifficultyPrompt generatePrompt;
	private LaunchController launchController;

	/**
	 * constructor method
	 * @param lc
	 */
	public GameController(LaunchController lc) {
		this.model = new GameModel();
		this.gameView = new GameView(model);
		this.launchController = lc;
		levelLoaded = false;
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

	/**
	 * method to launch game
	 */
	public void launch() {
		if (levelLoaded) {
			initializeListeners();
			gameView.getMainMenu().initialize(model);
			splash();
			levelLoaded = false;
		} else {
			handleGeneration();
		}
	}

	/**
	 * method to handle the level load
	 * @param parser
	 */
	public void handleLoad(LevelParser parser) {
		gameView.dispose();
		this.model = parser.getModel();
		this.gameView = new GameView(model);
		levelLoaded = true;
		launch();
	}

	/**
	 * method to load level
	 */
	public void loadLevel() {
		LevelDialog loader = new LevelDialog();
		LevelParser parser = loader.parseLevel(false);
		if (parser.successfulyParsed()) {
			this.model = parser.getModel();
			this.gameView = new GameView(model);
			levelLoaded = true;
		}
	}


	public void splash() {
		launchController.splash(gameView);
	}

	/**
	 * method to set a random level
	 * @param gv
	 * @param model
	 * @param levelLoaded
	 */
	public void setRandomLevel(GameView gv, GameModel model, boolean levelLoaded) {
		this.gameView = gv;
		this.model = model;
		this.levelLoaded = levelLoaded;
	}

	/**
	 * method to handle random level generation
	 */
	public void handleGeneration() {
		generatePrompt = new DifficultyPrompt();
		generatePrompt.prompt(this);
	}

	private void initializeListeners() {
		gameView.addPieceListener(new PieceListener(this));
		gameView.addBoardListener(new BoardListener(this));
		gameView.addMenuListener(new GameMenuListener(this));
		model.getBoard().addModelListener(new ViewModelListener(this));
	}

	/**
	 * method to handle win
	 */
	public void handleWin() {
		gameView.dispose();
		launchController.handleWin();
	}

	/**
	 * method to handle the build level
	 */
	public void handleBuild() {
		launchController.handleBuild();
	}

}
