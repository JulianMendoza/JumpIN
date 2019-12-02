package jumpin.controller.builder;

import javax.swing.JFrame;

import jumpin.controller.builder.listener.BuilderMenuListener;
import jumpin.view.builder.BuilderView;

public class BuilderController {

	private JFrame previousScreen;
	private BuilderView view;

	public BuilderController(JFrame previousScreen) {
		this.previousScreen = previousScreen;
		launchBuilder();
		initializeListeners();
	}

	private void launchBuilder() {
		view = new BuilderView();
		view.setVisible(true);
	}

	private void initializeListeners() {
		view.getMenu().addMenuListener(new BuilderMenuListener(view, previousScreen));
	}
}
