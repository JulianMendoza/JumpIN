package jumpin.controller.launch;

import javax.swing.JFrame;

import jumpin.controller.builder.BuilderController;
import jumpin.controller.game.GameController;
import jumpin.controller.launch.listener.LaunchMenuListener;
import jumpin.view.launch.LaunchView;
import jumpin.view.launch.splash.ScreenSplasher;

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

	/**
	 * constructor method
	 */
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

	/**
	 * method to show the menu
	 */
	public void startMenu() {
		startView.setVisible(true);
	}

	/**
	 * method to handle the play
	 */
	public void handlePlay() {
		gameController.launch();
		checkLevelLoaded();
	}

	/**
	 * method to handle the load
	 */
	public void handleLoad() {
		gameController.loadLevel();
		checkLevelLoaded();
	}

	/**
	 * method to handle win
	 */
	public void handleWin() {
		splash(startView);
	}

	/**
	 * method to check level
	 */
	private void checkLevelLoaded() {
		if (gameController.isLevelLoaded()) {
			startView.setVisible(false);
			gameController.launch();
		}
	}

	/**
	 * method to handle build
	 */
	public void handleBuild() {
		startView.setVisible(false);
		splash(buildController.getView());
	}

	/**
	 * method to handle back 
	 */
	public void handleBack() {
		splash(startView);
	}

	/**
	 * method to launch splash screen
	 * @param view
	 */
	public void splash(JFrame view) {
		ScreenSplasher splasher = new ScreenSplasher(view);
		splasher.execute();
	}
}
