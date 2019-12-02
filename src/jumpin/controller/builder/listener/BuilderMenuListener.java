package jumpin.controller.builder.listener;

import javax.swing.JFrame;

import jumpin.view.builder.BuilderView;
import jumpin.view.listener.MenuEvent;
import jumpin.view.listener.MenuListener;

public class BuilderMenuListener implements MenuListener {

	private BuilderView view;
	private JFrame previousScreen;

	public BuilderMenuListener(BuilderView view, JFrame previousFrame) {
		this.view = view;
		this.previousScreen = previousFrame;
	}

	@Override
	public void menuActionPerformed(int menuEvent) {
		switch (menuEvent) {
		case MenuEvent.VALIDATE_LEVEL:
			break;
		case MenuEvent.SAVE_LEVEL:
			break;
		case MenuEvent.BACK:
			break;
		}

	}

}
