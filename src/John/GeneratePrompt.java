package John;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jumpin.view.AbstractFrame;

/**
		 * 
		 * @author JOHN
		 *
		 */
public class GeneratePrompt extends JPanel implements AbstractFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField generateField;
	private final String PROMPT = "Please enter level of difficulty (1-6)";
	
	public GeneratePrompt() {
		populate();
	}

	public void populate() {
		generateField = new JTextField(15);
		add(new JLabel(PROMPT));
		add(generateField);
	}

	public String getText() {
		return generateField.getText();
	}
	
	public void clearText() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				generateField.setText("");
			}
		});
	}
}
