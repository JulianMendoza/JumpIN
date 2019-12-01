package jumpin.view.level;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jumpin.model.GameModel;
import jumpin.model.exception.LevelParseException;
import jumpin.model.file.LevelParser;
import jumpin.view.game.GameView;

/**
 * 
 * @author Giuseppe, Julian
 *
 */
public class LevelLoader {

	private LevelParser parser;
	private JFrame parentFrame;

	public LevelLoader(GameModel gameModel, GameView gameView) {
		parser = new LevelParser(gameModel.getBoard(), gameModel.getGameState());
		constructFrame();
	}

	private void constructFrame() {
		parentFrame = new JFrame();
		parentFrame.setLocationRelativeTo(null);
		parentFrame.setSize(200, 200);
		parentFrame.add(new JButton());
	}

	public File launchChooser(boolean mandatoryChoice) {
		LevelChooser fc = new LevelChooser();
		int userSelection = fc.showOpenDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				parser.parseLevel(fc.getSelectedFile());
				parentFrame.dispose();
				return fc.getSelectedFile();
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
		return null;
	}

	public void load(File f) {
		try {
			parser.parseLevel(f);
		} catch (LevelParseException e) {
			e.printStackTrace();
		}
	}

}
