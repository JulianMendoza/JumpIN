package jumpin.view.level;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import jumpin.model.GameModel;
import jumpin.model.exception.LevelParseException;
import jumpin.model.exception.LevelSaveException;
import jumpin.model.file.LevelParser;
import jumpin.model.file.LevelSaver;

/**
 * Class which contructs a level dialog
 * 
 * @author Giuseppe, Julian
 *
 */
public class LevelDialog {

	private JDialog parentFrame;

	public LevelDialog() {
		constructFrame();
	}

	private void constructFrame() {
		parentFrame = new JDialog();
		parentFrame.setLocationRelativeTo(null);
		parentFrame.setSize(200, 200);
	}
	/*
	 *  Parser dialog
	 */
	public LevelParser parseLevel(boolean mandatoryChoice) {
		LevelParser parser = new LevelParser();

		LevelChooser fc = new LevelChooser();
		int userSelection = fc.showOpenDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				parser.parseLevel(fc.getSelectedFile());
				parentFrame.dispose();
			} catch (LevelParseException e) {
				JOptionPane.showMessageDialog(parentFrame, e.getMessage(), "Parsing Error", JOptionPane.ERROR_MESSAGE);
				parseLevel(mandatoryChoice);
			}
		} else if (userSelection == JFileChooser.CANCEL_OPTION) {
			if (mandatoryChoice) {
				JOptionPane.showMessageDialog(parentFrame, "You must choose a level!", "Severe Error", JOptionPane.ERROR_MESSAGE);
				parseLevel(mandatoryChoice);
			} else {
				parentFrame.dispose();
			}
		}
		return parser;
	}
	/*
	 * Saving the level
	 */
	public void saveLevel(GameModel model) {
		LevelChooser fc = new LevelChooser();
		int userSelection = fc.showSaveDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				LevelSaver.saveLevel(fc.getSelectedFile(), model);
			} catch (LevelSaveException e) {
				JOptionPane.showMessageDialog(parentFrame, e.getMessage(), "Parsing Error", JOptionPane.ERROR_MESSAGE);
				saveLevel(model);
			}
		} else if (userSelection == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "File not saved", "Error", JOptionPane.ERROR_MESSAGE);
		}
		parentFrame.dispose();
	}

}
