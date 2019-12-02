package jumpin.controller.builder;

import javax.swing.JFrame;

import jumpin.controller.builder.listener.BuilderMenuListener;
import jumpin.controller.launch.LaunchController;
import jumpin.view.builder.BuilderView;

public class BuilderController {

	private JFrame previousScreen;
	private BuilderView view;
	private LaunchController lc;

	public BuilderController(LaunchController lc) {
		this.previousScreen = lc.getStartView();
		this.lc=lc;
		view = new BuilderView();
		
	}
	public JFrame getPreviousScreen() {
		return previousScreen;
	}
	public BuilderView getView() {
		return view;
	}
	public void launch() {
		initializeListeners();
	}
	public void handleBack() {
		view.dispose();
		lc.handleBack();
	}
	private void initializeListeners() {
		view.getMenu().addMenuListener(new BuilderMenuListener(this));
	}
}
