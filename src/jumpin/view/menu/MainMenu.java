package jumpin.view.menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import jumpin.model.GameModel;
import jumpin.view.constants.ComponentSize;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;

/**
 * GUI Components for the main menu (undo, redo, state, etc.)
 * 
 * @author Giuseppe, Cameron Davis
 */
public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -436968148338186761L;
	private GameModel model;
	private JButton undoButton, redoButton;
	private JLabel gameStateLabel;
	private JRadioButton rdbtnNewRadioButton;
	
	public MainMenu(GameModel model) {
		this.model = model;
		
		undoButton = new JButton("\u2190 UNDO");
		redoButton = new JButton("REDO \u2192");
		addListeners();
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);
		
		gameStateLabel = new JLabel();
		gameStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameStateLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		setStateLabelText();
		
		setBounds(0, 0, ComponentSize.MENU_WIDTH, ComponentSize.MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(new Color(2, 145, 55));
		
		rdbtnNewRadioButton = new JRadioButton("Toggle");
		rdbtnNewRadioButton.setBackground(Color.GREEN);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(247)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(gameStateLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(undoButton)
							.addGap(18)
							.addComponent(rdbtnNewRadioButton)
							.addGap(18)
							.addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(260, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(gameStateLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	public void setStateLabelText() {
		gameStateLabel.setText("<html># of Rabbits to win: " + model.getGameState().getNumToWin() + 
				"<br>Current state of the game: " + model.getGameState().getState().toString() +  "</html>");
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
			setStateLabelText();
		}
	}
}
