package jumpin.view.launch;

import javax.swing.SwingWorker;

import jumpin.view.game.GameView;

public class ScreenSplasher extends SwingWorker<Void, Void> {

	private SplashScreen splashScreen;
	private GameView gameView;

	public ScreenSplasher(GameView gameView) {
		this.gameView = gameView;
		splashScreen = new SplashScreen();
	}

	@Override
	protected Void doInBackground() throws Exception {
		if (gameView != null) {
			gameView.setVisible(false);
		}
		splashScreen.splash();
		return null;
	}

	@Override
	protected void done() {

		if (gameView != null) {
			gameView.setVisible(true);
		}
	}

}
