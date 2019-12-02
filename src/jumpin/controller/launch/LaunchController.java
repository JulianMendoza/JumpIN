package jumpin.controller.launch;

import javax.swing.JFrame;

import jumpin.controller.builder.BuilderController;
import jumpin.controller.game.GameController;
import jumpin.controller.launch.listener.LaunchMenuListener;
import jumpin.view.launch.LaunchView;
import jumpin.view.launch.ScreenSplasher;

/**
 * Main controller
 * 
 * @author Julian, Giuseppe
 *
 */
public class LaunchController {
	private GameController gameController;
	private BuilderController buildController;
	private LaunchView startView;

	public LaunchController() {
		gameController = new GameController(this);
		buildController = new BuilderController(this);
		startView = new LaunchView();
		initializeListeners();
	}

	public LaunchView getStartView() {
		return startView;
	}

	private void initializeListeners() {
		startView.getMenu().addMenuListener(new LaunchMenuListener(this));
	}

	public void startMenu() {
		startView.setVisible(true);
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
		if (gameController.isLevelLoaded()) {
			startView.setVisible(false);
			gameController.launch();
		}
	}

	public void handleBuild() {
		startView.setVisible(false);
		splash(buildController.getView());
	}

	public void handleBack() {
		splash(startView);
	}

	public void splash(JFrame view) {
		ScreenSplasher splasher = new ScreenSplasher(view);
		splasher.execute();
	}
}
