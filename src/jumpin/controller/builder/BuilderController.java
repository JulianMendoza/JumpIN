package jumpin.controller.builder;

import jumpin.controller.builder.listener.BuilderMenuListener;
import jumpin.controller.launch.LaunchController;
import jumpin.view.builder.BuilderView;

/**
 * 
 * @author Giuseppe
 *
 */
public class BuilderController {

	private BuilderView view;
	private LaunchController lc;

	public BuilderController(LaunchController lc) {
		this.lc = lc;
		view = new BuilderView();
		initializeListeners();
	}

	public BuilderView getView() {
		return view;
	}

	public void handleBack() {
		view.dispose();
		lc.handleBack();
	}

	private void initializeListeners() {
		view.getMenu().addMenuListener(new BuilderMenuListener(this));
	}
}
