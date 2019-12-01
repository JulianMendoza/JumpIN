package play;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jumpin.controller.game.GameController;

public class GUI {

	public static void main(String args[]) throws CloneNotSupportedException, ParserConfigurationException, TransformerException {
		GameController controller = new GameController();
		controller.launch();
	}

}