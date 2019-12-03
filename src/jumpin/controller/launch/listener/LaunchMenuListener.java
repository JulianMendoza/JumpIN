package jumpin.controller.launch.listener;

import jumpin.controller.launch.LaunchController;
import jumpin.view.listener.MenuEvent;
import jumpin.view.listener.MenuListener;
import jumpin.view.util.Waiter;

/**
 * 
 * @author Giuseppe
 *
 */
public class LaunchMenuListener implements MenuListener {

	private LaunchController lc;

	/**
	 * constructor method
	 * @param lc
	 */
	public LaunchMenuListener(LaunchController lc) {
		this.lc = lc;
	}

	@Override
	public void menuActionPerformed(int menuEvent) {
		Waiter waiter = new Waiter(lc.getStartView());
		waiter.startWaiting();
		switch (menuEvent) {
		case MenuEvent.PLAY_GAME:
			lc.handlePlay();
			break;
		case MenuEvent.LOAD_LEVEL:
			lc.handleLoad();
			break;
		case MenuEvent.BUILD_LEVEL:
			lc.handleBuild();
			break;
		}
		waiter.stopWaiting();

	}

}
