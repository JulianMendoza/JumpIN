package jumpin.controller.launch;

import jumpin.controller.builder.BuilderController;
import jumpin.controller.game.GameController;
import jumpin.view.LaunchMenuView;
/**
 *  Main controller
 * @author Julian
 *
 */
public class LaunchController {
	private GameController gameController;
	private BuilderController buildController;
	private LaunchMenuView startView;
	public LaunchController() {
		gameController = new GameController();
		buildController = new BuilderController();
	}
	public void startMenu() {
		if(startView==null) {
			startView=new LaunchMenuView(this);
		}else {
			startView.setVisible(true);
		}
	}
	public void handlePlay() {
		startView.setVisible(false);
		gameController.launch();
	}
	public void handleLoad() {
		gameController.loadLevel();
	}
	
	public void handleBuild() {	
		
	}
}
