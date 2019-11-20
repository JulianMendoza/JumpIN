package play;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jumpin.controller.GameController;
import jumpin.model.GameModel;
import jumpin.view.GameView;

public class GUI {

	public static void main(String args[]) throws CloneNotSupportedException, ParserConfigurationException, TransformerException {
		GameModel model = new GameModel();
		//model.getGenerator().createLevel1();
		//model.getGenerator().createLevelTest();
		//model.getGenerator().createLevelTest2();
		//model.getGenerator().createLevelTest3();
		model.getGenerator().loadLevelXML("levels/Level1.xml");
		GameView view = new GameView(model);
		GameController controller = new GameController(model, view);
		controller.launch();
		model.getGenerator().saveLevelXML();
	}

}