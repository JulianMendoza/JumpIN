package jumpin.view.launch;

import javax.swing.JFrame;
import javax.swing.SwingWorker;


/**
 * 
 * @author Giuseppe
 *
 */
public class ScreenSplasher extends SwingWorker<Void, Void> {

	private SplashScreen splashScreen;
	private JFrame view;

	public ScreenSplasher(JFrame view) {
		this.view = view;
		splashScreen = new SplashScreen();
	}

	@Override
	protected Void doInBackground() throws Exception {
		if (view != null) {
			view.setVisible(false);
		}
		splashScreen.splash();
		return null;
	}

	@Override
	protected void done() {

		if (view != null) {
			view.setVisible(true);
		}
	}

}
