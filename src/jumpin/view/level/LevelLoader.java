package jumpin.view.level;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jumpin.model.GameModel;
import jumpin.model.exception.LevelParseException;
import jumpin.model.file.LevelParser;
import jumpin.view.game.GameView;
import jumpin.view.launch.ScreenSplasher;

/**
 * 
 * @author Giuseppe, Julian
 *
 */
public class LevelLoader {

	private LevelParser parser;
	private JFrame parentFrame;
	private GameView gameView;

	public LevelLoader(GameModel gameModel, GameView gameView) {
		this.gameView = gameView;
		parser = new LevelParser(gameModel.getBoard(), gameModel.getGameState());
		constructFrame();
	}

	private void constructFrame() {
		parentFrame = new JFrame();
		parentFrame.setLocationRelativeTo(null);
		parentFrame.setSize(200, 200);
	}

	public GameView launchChooser(boolean mandatoryChoice) {
		LevelChooser fc = new LevelChooser();
		int userSelection = fc.showOpenDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				parser.parseLevelXML(fc.getSelectedFile());
				parentFrame.dispose();

				ScreenSplasher splasher = new ScreenSplasher(gameView);
				splasher.execute();
			} catch (LevelParseException e) {
				JOptionPane.showMessageDialog(parentFrame, e.getMessage(), "Parsing Error", JOptionPane.ERROR_MESSAGE);
				launchChooser(mandatoryChoice);
			}
		} else if (userSelection == JFileChooser.CANCEL_OPTION) {
			if (mandatoryChoice) {
				JOptionPane.showMessageDialog(parentFrame, "You must choose a level!", "Severe Error", JOptionPane.ERROR_MESSAGE);
				launchChooser(mandatoryChoice);
			} else {
				parentFrame.dispose();
			}
		}
		return gameView;
	}

}
