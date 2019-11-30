package jumpin.view.level;

import javax.swing.JOptionPane;

import jumpin.model.GameModel;
import jumpin.model.exception.LevelParseException;
import jumpin.model.file.LevelParser;

/**
 * 
 * @author Cameron
 *
 */
public class LevelBuilder {
	
	private LevelParser parser;

	public LevelBuilder(GameModel model) {
		parser = new LevelParser(model.getBoard(), model.getGameState());
		launchLevelBuilder();
	}

	private void launchLevelBuilder() {
		String level = JOptionPane.showInputDialog("To build a level, specify the piece (fox12NSEW, rabbit123, mush)\n"
												+ "and the \"XY\" coordinates of where you want to place it seperated by a '-'.\n"
												+ "Seperate pieces by a comma and space.\n\n"
												+ "Example: \"fox1NS-11, fox2EW-33, rabbit1-30, rabbit2-14, rabbit3-42, mush-31, mush-42\" \n\n");
		try {
			parser.parseLevelString(level);
		} catch (LevelParseException e) {
			e.printStackTrace();
		}
		
	}
}
