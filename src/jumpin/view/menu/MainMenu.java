package jumpin.view.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicButtonUI;

import jumpin.model.GameModel;
import jumpin.model.constants.StateOfGame;
import jumpin.model.exception.IllegalMoveException;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.FontFactory;
import jumpin.view.style.Button3D;
import jumpin.view.util.Waiter;

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
	private JFrame mainFrame;

	public MainMenu(GameModel model, JFrame mainFrame) {

		this.model = model;
		this.mainFrame = mainFrame;
		undoButton = new JButton("\u2190 UNDO");
		redoButton = new JButton("REDO \u2192");
		solveButton = new JButton("Do Best Move");
		bestMoves = new JButton("Find Solution");
		showBestMoves = new JButton("Show Solution");

		addListeners();
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);
		solveButton.setEnabled(false);
		showBestMoves.setEnabled(false);

		threshold = new JPanel();
		thresholdField = new JTextField(15);
		threshold.add(new JLabel("Please enter a maximum moves threshold (3-6 recommendation):"));
		threshold.add(thresholdField);

		gameStateLabel = new JLabel();
		gameStateLabel.setHorizontalAlignment(SwingConstants.CENTER);

		applyStyling(undoButton, redoButton, solveButton, bestMoves, showBestMoves, gameStateLabel);

		setStateLabelText();

		setBounds(0, 0, ComponentSize.MENU_WIDTH, ComponentSize.MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(ViewConstants.BOARD_COLOR);

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
		gameStateLabel.setText("<html># of Rabbits to win: " + model.getGameState().getNumToWin() + "<br>Current state of the game: " + model.getGameState().getState().toString() + "</html>");
		checkGameState();
	}

	/**
	 * Sets the Redo button to enabled
	 * 
	 * @param enabled True or False
	 */
	public void setRedo(boolean enabled) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
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
			@Override
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
			Waiter waiter = new Waiter(mainFrame);
			waiter.startWaiting();

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
				int result = JOptionPane.showConfirmDialog(null, threshold, "Search threshold", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					try {
						int threshold = Integer.parseInt(thresholdField.getText());
						model.getBoard().computeSolution(threshold);
						if (model.getBoard().getBestMoves() == null) {
							JOptionPane.showMessageDialog(null, "No Solutions found!", "Invalid entry!", JOptionPane.ERROR_MESSAGE);
							thresholdField.setText("");
						} else {
							solveButton.setEnabled(true);
							showBestMoves.setEnabled(true);
							bestMoves.setEnabled(false);
						}
					} catch (Exception x) {
						JOptionPane.showMessageDialog(null, "Please only enter integers", "Invalid entry!", JOptionPane.ERROR_MESSAGE);
						thresholdField.setText("");
					}
				}

			} else if (e.getSource().equals(showBestMoves)) {
				JOptionPane.showMessageDialog(null, model.getBoard().getBestMoves().toString(), "Current Best Solution", JOptionPane.PLAIN_MESSAGE);
			}

			waiter.stopWaiting();
			setStateLabelText();
		}
	}

	private void applyStyling(JComponent... components) {
		Font buttonFont = FontFactory.createButtonFont(Font.BOLD, 14);
		Font menuFont = FontFactory.createMenuFont(Font.BOLD, 14);
		BasicButtonUI buttonUI = new Button3D();
		for (JComponent c : components) {
			if (c instanceof JButton) {
				c.setFont(buttonFont);
				((JButton) c).setUI(buttonUI);
			} else if (c instanceof JLabel) {
				c.setFont(menuFont);
			}
		}
	}
}
