package jumpin.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jumpin.model.GameModel;
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
	private GameModel model;
	
	public MainMenu(GameModel model) {
		this.model = model;
		
		undoButton = new JButton("UNDO");
		redoButton = new JButton("REDO");
		addListeners();
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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				redoButton.setEnabled(enabled);
			}
		});
	}

	public void setUndo(boolean enabled) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				undoButton.setEnabled(enabled);
			}
		});	
	}

	private void addListeners() {
		ButtonListener l = new ButtonListener();
		undoButton.addActionListener(l);
		redoButton.addActionListener(l);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(undoButton)) {
				model.getBoard().undoMove();
			} else if(e.getSource().equals(redoButton)) {
				model.getBoard().redoMove();
			}
		}
	}
}
