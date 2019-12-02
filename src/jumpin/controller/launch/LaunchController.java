package jumpin.controller.launch;

import javax.swing.JFrame;

import jumpin.controller.builder.BuilderController;
import jumpin.controller.game.GameController;
import jumpin.view.LaunchMenuView;
import jumpin.view.game.GameView;
import jumpin.view.launch.ScreenSplasher;

/**
 * Main controller
 * 
 * @author Julian
 *
 */
public class LaunchController {
	private GameController gameController;
	private BuilderController buildController;
	private LaunchMenuView startView;

	public LaunchController() {
		gameController = new GameController(this);
		buildController = new BuilderController();
	}
	public void startMenu() {
		if (startView == null) {
			startView = new LaunchMenuView(this);
		} else {
			startView.setVisible(true);
		}
	}
	public void handlePlay() {
		gameController.launch();
		checkLevelLoaded();
	}
	public void handleLoad() {
		gameController.loadLevel();
		checkLevelLoaded();
	}
	public void handleWin() {
		splash(startView);
	}
	private void checkLevelLoaded() {
		if(gameController.isLevelLoaded()) {
			startView.setVisible(false);
			gameController.launch();
		}
	}
	public void handleBuild() {

	}
	
	public void splash(JFrame view) {
		ScreenSplasher splasher = new ScreenSplasher(view);
		splasher.execute();
	}
}
