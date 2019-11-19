package play;

import jumpin.controller.GameController;
import jumpin.model.GameModel;
import jumpin.view.GameView;

public class GUI {

	public static void main(String args[]) throws CloneNotSupportedException {
		GameModel model = new GameModel();
		//model.getGenerator().createLevel1();
		//model.getGenerator().createLevelTest();
		//model.getGenerator().createLevelTest2();
		model.getGenerator().createLevelTest3();
		GameView view = new GameView(model);
		GameController controller = new GameController(model, view);
		controller.launch();
	}

}