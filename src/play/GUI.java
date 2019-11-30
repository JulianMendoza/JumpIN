package play;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jumpin.controller.GameController;
import jumpin.model.GameModel;
import jumpin.view.View;

public class GUI {

	public static void main(String args[]) throws CloneNotSupportedException, ParserConfigurationException, TransformerException {
		GameModel model = new GameModel();
		//model.getGenerator().loadLevelXML();
		View view = new View(model);
		GameController controller = new GameController(model, view.getGameView());
		controller.launch();
	}

}