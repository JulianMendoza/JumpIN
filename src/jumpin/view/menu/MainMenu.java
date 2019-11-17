package jumpin.view.menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import jumpin.model.GameModel;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.move.MoveSet;
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
	private JButton undoButton, redoButton, solveButton, bestMoves, showBestMoves;
	private JLabel gameStateLabel;
	//private JRadioButton rdbtnNewRadioButton;
	
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
		
		gameStateLabel = new JLabel();
		gameStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameStateLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		setStateLabelText();
		
		setBounds(0, 0, ComponentSize.MENU_WIDTH, ComponentSize.MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(new Color(2, 145, 55));
		
		//rdbtnNewRadioButton = new JRadioButton("Toggle");
		//rdbtnNewRadioButton.setBackground(Color.GREEN);
		
		this.add(undoButton);
		this.add(gameStateLabel);
		this.add(redoButton);
	//	this.add(rdbtnNewRadioButton);
		this.add(solveButton);
		this.add(bestMoves);
		this.add(showBestMoves);
//		GroupLayout groupLayout = new GroupLayout(this);
//		groupLayout.setHorizontalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addGap(239)
//					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
//						.addComponent(gameStateLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//						.addGroup(groupLayout.createSequentialGroup()
//							.addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
//							.addGap(18)
//							.addComponent(rdbtnNewRadioButton)
//							.addGap(18)
//							.addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//							.addPreferredGap(ComponentPlacement.RELATED)))
//					.addContainerGap(249, Short.MAX_VALUE))
//		);
//		groupLayout.setVerticalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addGap(19)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
//						.addComponent(rdbtnNewRadioButton)
//						.addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addComponent(gameStateLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(22, Short.MAX_VALUE))
//		);
//		setLayout(groupLayout);
	}
	
	public void setStateLabelText() {
		gameStateLabel.setText("<html># of Rabbits to win: " + model.getGameState().getNumToWin() + 
				"<br>Current state of the game: " + model.getGameState().getState().toString() +  "</html>");
	}

	public void setRedo(boolean enabled) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				redoButton.setEnabled(enabled);
				solveButton.setEnabled(false);
				showBestMoves.setEnabled(false);
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
		solveButton.addActionListener(l);
		bestMoves.addActionListener(l);
		showBestMoves.addActionListener(l);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(undoButton)) {
				model.getBoard().undoMove();
				solveButton.setEnabled(false);
			} else if(e.getSource().equals(redoButton)) {
				model.getBoard().redoMove();
				solveButton.setEnabled(false);
			}else if(e.getSource().equals(solveButton)) {
				try {
					model.getBoard().solve();
				} catch (IllegalMoveException e1) {
					e1.printStackTrace();
				} 
			}else if(e.getSource().equals(bestMoves)){
				solveButton.setEnabled(true);
				showBestMoves.setEnabled(true);
				try {
					model.getBoard().computeSolution();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getSource().equals(showBestMoves)){
				JOptionPane.showMessageDialog(null, model.getBoard().getBestMoves().toString(), "Current Best Solution",JOptionPane.PLAIN_MESSAGE);
			}
			setStateLabelText();
		}
	}
}
