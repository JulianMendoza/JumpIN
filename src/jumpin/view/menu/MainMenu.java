package jumpin.view.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jumpin.model.GameModel;
import jumpin.model.constants.StateOfGame;
import jumpin.model.exception.IllegalMoveException;
import jumpin.view.constants.ComponentSize;

/**
 * GUI Components for the main menu (undo, redo, state, etc.)
 * 
 * @author Julian, Giuseppe, John, Cameron Davis
 */
public class MainMenu extends JPanel {

	private static final long serialVersionUID = -436968148338186761L;
	private GameModel model;
	private JButton undoButton, redoButton, solveButton, bestMoves, showBestMoves;
	private JLabel gameStateLabel;
	private JPanel threshold;
	private JTextField thresholdField;

	public MainMenu(GameModel model) {
		this.model = model;
		undoButton = new JButton("\u2190 UNDO");
		redoButton = new JButton("REDO \u2192");
		solveButton = new JButton("Do best move");
		bestMoves = new JButton("Find best moves");
		showBestMoves = new JButton("Show best moves");

		addListeners();
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);
		solveButton.setEnabled(false);
		showBestMoves.setEnabled(false);

		threshold = new JPanel();
		thresholdField = new JTextField(15);
		threshold.add(new JLabel("Please enter a threshold (3-6 recommendation):"));
		threshold.add(thresholdField);

		gameStateLabel = new JLabel();
		gameStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameStateLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		setStateLabelText();

		setBounds(0, 0, ComponentSize.MENU_WIDTH, ComponentSize.MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(new Color(2, 145, 55));

		this.add(undoButton);
		this.add(gameStateLabel);
		this.add(redoButton);
		this.add(solveButton);
		this.add(bestMoves);
		this.add(showBestMoves);

	}

	/**
	 * Method to update the label text
	 */
	public void setStateLabelText() {
		gameStateLabel.setText("<html># of Rabbits to win: " + model.getGameState().getNumToWin()
				+ "<br>Current state of the game: " + model.getGameState().getState().toString() + "</html>");
		checkGameState();
	}

	/**
	 * Sets the Redo button to enabled
	 * 
	 * @param enabled True or False
	 */
	public void setRedo(boolean enabled) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				redoButton.setEnabled(enabled);
			}
		});
	}

	/**
	 * Sets the Undo button to enabled
	 * 
	 * @param enabled True or False
	 */
	public void setUndo(boolean enabled) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				undoButton.setEnabled(enabled);
			}
		});
	}

	/**
	 * Enables the bestmove button while disabling the solve and showbestmove button
	 */
	public void enableFindBestMoves() {
		bestMoves.setEnabled(true);
		showBestMoves.setEnabled(false);
		solveButton.setEnabled(false);
	}

	/**
	 * Checks the current state of the game
	 */
	private void checkGameState() {
		if (model.getGameState().getState().equals(StateOfGame.WON)) {
			JOptionPane.showMessageDialog(null, "GAME WON");
			System.exit(0);
		}
	}

	/**
	 * Adds action listeners to the buttons
	 */
	private void addListeners() {
		ButtonListener l = new ButtonListener();
		undoButton.addActionListener(l);
		redoButton.addActionListener(l);
		solveButton.addActionListener(l);
		bestMoves.addActionListener(l);
		showBestMoves.addActionListener(l);
	}

	/**
	 * inner class to perform actionPerformed
	 * 
	 * @author Julian, Giuseppe, John
	 *
	 */
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(undoButton)) {
				model.getBoard().undoMove();
				solveButton.setEnabled(false);
				bestMoves.setEnabled(true);
				showBestMoves.setEnabled(false);
			} else if (e.getSource().equals(redoButton)) {
				model.getBoard().redoMove();
				solveButton.setEnabled(false);
				bestMoves.setEnabled(true);
				showBestMoves.setEnabled(false);
			} else if (e.getSource().equals(solveButton)) {
				try {
					model.getBoard().solve();
				} catch (IllegalMoveException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(bestMoves)) {
				int result = JOptionPane.showConfirmDialog(null, threshold, "Search threshold",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					try {
						Integer.parseInt(thresholdField.getText());
						model.getBoard().computeSolution(Integer.parseInt(thresholdField.getText()));
						if (model.getBoard().getBestMoves() == null) {
							JOptionPane.showMessageDialog(null, "No Solutions found!", "Invalid entry!",
									JOptionPane.ERROR_MESSAGE);
							thresholdField.setText("");
							return;
						} else {
							solveButton.setEnabled(true);
							showBestMoves.setEnabled(true);
							bestMoves.setEnabled(false);
						}
					} catch (Exception x) {
						JOptionPane.showMessageDialog(null, "Please only enter integers", "Invalid entry!",
								JOptionPane.ERROR_MESSAGE);
						thresholdField.setText("");
						return;
					}
				}

			} else if (e.getSource().equals(showBestMoves)) {
				JOptionPane.showMessageDialog(null, model.getBoard().getBestMoves().toString(), "Current Best Solution",
						JOptionPane.PLAIN_MESSAGE);
			}
			setStateLabelText();
		}
	}
}
