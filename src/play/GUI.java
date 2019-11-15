package play;

import jumpin.controller.GameController;
import jumpin.model.GameModel;
import jumpin.view.GameView;

public class GUI {

	public static void main(String args[]) {
		GameModel model = new GameModel();
		model.getGenerator().createLevelTest();
		GameView view = new GameView(model);
		GameController controller = new GameController(model, view);
		controller.launch();
	}

}