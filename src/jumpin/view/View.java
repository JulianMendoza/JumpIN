package jumpin.view;

import java.io.File;

import jumpin.model.GameModel;
import jumpin.view.game.GameView;
import jumpin.view.level.LevelLoader;

/**
 * 
 * @author Giuseppe
 *
 */
public class View {

	private GameView gameView;

	public View(GameModel model) {
		new LevelLoader(model, gameView).launchChooser(true);
		gameView = new GameView(model);
	}

	public View(GameModel model, File f) {
		new LevelLoader(model, gameView).load(f);
		gameView = new GameView(model);
	}

	public GameView getGameView() {
		return gameView;
	}

}
