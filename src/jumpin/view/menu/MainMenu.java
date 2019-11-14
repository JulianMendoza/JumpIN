package jumpin.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jumpin.view.constants.ComponentSize;

/**
 * 
 * @author Giuseppe, Cameron Davis
 *
 */
public class MainMenu extends JPanel {
	
	private JButton undoButton, redoButton;
	private JTextField gameStateField;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -436968148338186761L;

	public MainMenu() {
		undoButton = new JButton("UNDO");
		redoButton = new JButton("REDO");
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);
		gameStateField = new JTextField();
		setBounds(0, 0, ComponentSize.MENU_WIDTH, ComponentSize.MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(new Color(2, 145, 55));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(undoButton, BorderLayout.WEST);
		add(redoButton, BorderLayout.WEST);
	}
	
	public void setRedo(boolean enabled) {
		redoButton.setEnabled(enabled);
	}

	public void setUndo(boolean enabled) {
		undoButton.setEnabled(enabled);
	}
	
}
