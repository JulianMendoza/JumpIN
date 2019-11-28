package jumpin.view;

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

	public GameView getGameView() {
		return gameView;
	}

}
