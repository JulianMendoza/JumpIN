package jumpin.view.prompt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jumpin.controller.game.GameController;
import jumpin.model.GameModel;
import jumpin.model.board.generator.GenerateLevel;
import jumpin.view.AbstractFrame;
import jumpin.view.game.GameView;

/**
 * Class that generates a difficulty prompt
 * @author John
 *
 */
public class DifficultyPrompt extends JPanel implements AbstractFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField generateField;
	private final String PROMPT = "Please enter level of difficulty (1-5)";

	public DifficultyPrompt() {
		populate();
	}
	/**
	 * Styling for the prompt
	 */
	@Override
	public void populate() {
		generateField = new JTextField(15);
		add(new JLabel(PROMPT));
		add(generateField);
	}
	/**
	 * Method to prompt the user
	 */
	public void prompt(GameController gc) {
		GameModel model = gc.getModel();
		GameView gameView = gc.getGameView();
		int level = JOptionPane.showConfirmDialog(gameView, this, "Generate Level", JOptionPane.OK_CANCEL_OPTION);
		if (level == JOptionPane.OK_OPTION) {
			try {
				int difficulty = Integer.parseInt(this.getText());

				if (difficulty > 5 || difficulty < 1) {
					JOptionPane.showMessageDialog(gameView, "Please enter diffciulty within range", "Invalid entry!", JOptionPane.ERROR_MESSAGE);
					this.clearText();
				} else {
					do {
						model = GenerateLevel.createLevel();

						model.getBoard().computeSolution(difficulty);
					} while (model.getBoard().getSolution() == null || difficulty != model.getBoard().getSolution().size());
					GameView view = new GameView(model);
					gc.setRandomLevel(view, model, true);
				}
			} catch (Exception x) {
				JOptionPane.showMessageDialog(this, "Please only enter integers", "Invalid entry!", JOptionPane.ERROR_MESSAGE);
				this.clearText();
			}
		}
	}
	/**
	 * Method to get the string of the user input
	 * @return String of the user input
	 */
	public String getText() {
		return generateField.getText();
	}
	/**
	 * Clear text of the prompt
	 */
	public void clearText() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				generateField.setText("");
			}
		});
	}
}
