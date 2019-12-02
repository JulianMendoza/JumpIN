package jumpin.view.prompt;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jumpin.view.AbstractFrame;

/**
 * Class for fetching the move threshold number input
 * 
 * @author Giuseppe
 */
public class ThresholdPrompt extends JPanel implements AbstractFrame {

	private static final long serialVersionUID = -1599647700466379424L;

	private JTextField thresholdField;
	private final String PROMPT = "Please enter a threshold for max moves to solve the game:";

	public ThresholdPrompt() {
		populate();
	}

	@Override
	public void populate() {
		thresholdField = new JTextField(15);
		add(new JLabel(PROMPT));
		add(thresholdField);
	}

	public String getText() {
		return thresholdField.getText();
	}

	public void clearText() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				thresholdField.setText("");
			}
		});
	}

}
