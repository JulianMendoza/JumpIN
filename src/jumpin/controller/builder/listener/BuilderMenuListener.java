package jumpin.controller.builder.listener;

import javax.swing.JFrame;

import jumpin.controller.builder.BuilderController;
import jumpin.view.builder.BuilderView;
import jumpin.view.game.menu.MainMenu;
import jumpin.view.listener.MenuEvent;
import jumpin.view.listener.MenuListener;
import jumpin.view.util.Waiter;

public class BuilderMenuListener implements MenuListener {

	private BuilderView view;
	private JFrame previousScreen;
	private BuilderController bc;
	public BuilderMenuListener(BuilderController bc) {
		this.view = bc.getView();
		this.previousScreen = bc.getPreviousScreen();
		this.bc=bc;
	}

	@Override
	public void menuActionPerformed(int menuEvent) {
		Waiter waiter = new Waiter(view);
		waiter.startWaiting();
		
		switch (menuEvent) {
		case MenuEvent.VALIDATE_LEVEL:
			break;
		case MenuEvent.SAVE_LEVEL:
			break;
		case MenuEvent.BACK:
			bc.handleBack();
			break;
		}
		waiter.stopWaiting();
	}

}
