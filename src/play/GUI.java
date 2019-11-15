package play;

import jumpin.controller.GameController;
import jumpin.model.GameModel;
import jumpin.model.util.Position;
import jumpin.view.GameView;

public class GUI {

	public static void main(String args[]) {
		GameModel model = new GameModel();
<<<<<<< HEAD
		model.getGenerator().createLevel1();
		//model.getBoard().stuff();
=======
		model.getGenerator().createLevelTest();
>>>>>>> branch 'master' of https://github.com/JulianMendoza/JumpIN.git
		GameView view = new GameView(model);
		GameController controller = new GameController(model, view);
		controller.launch();
	}

}