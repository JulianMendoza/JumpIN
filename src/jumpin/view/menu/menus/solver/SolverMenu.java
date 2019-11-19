package jumpin.view.menu.menus.solver;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.menu.MenuFrame;
import jumpin.view.menu.listener.MenuEvent;
import jumpin.view.menu.listener.MenuListener;
import jumpin.view.util.GroupBox;

/**
 * Solver Menu component of the main menu
 * 
 * @author Giuseppe
 */
public class SolverMenu extends GroupBox implements MenuFrame {

	private static final long serialVersionUID = 2729791032030947685L;
	
	private ThresholdPrompt thresholdPrompt;

	private List<MenuListener> menuListeners;

	private JButton doMoveButton;
	private JButton findSolutionButton;
	private JButton showSolutionButton;

	private final String DO_MOVE_TEXT = "Do Best Move";
	private final String FIND_SOLUTION_TEXT = "Find Solution";
	private final String SHOW_SOLUTION_TEXT = "Show Solution";

	public SolverMenu() {
		super("Solver");
		populate();
	}

	/**
	 * Fill Solver Menu with necessary components
	 */
	@Override
	public void populate() {
		menuListeners = new ArrayList<MenuListener>();

		setBackground(ViewConstants.BOARD_COLOR);

		thresholdPrompt = new ThresholdPrompt();
		doMoveButton = ComponentFactory.create3DMenuButton(DO_MOVE_TEXT);
		findSolutionButton = ComponentFactory.create3DMenuButton(FIND_SOLUTION_TEXT);
		showSolutionButton = ComponentFactory.create3DMenuButton(SHOW_SOLUTION_TEXT);

		doMoveButton.setEnabled(false);
		showSolutionButton.setEnabled(false);

		setLayout(new GridLayout(0, 1, 0, 0));

		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);

		addButtonListeners();
		add(findSolutionButton);
		add(doMoveButton);
		add(showSolutionButton);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		doMoveButton.addActionListener(l);
		findSolutionButton.addActionListener(l);
		showSolutionButton.addActionListener(l);
	}

	/**
	 * Disable all Solver Menu buttons except Find Solution button
	 */
	public void resetSolution() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				doMoveButton.setEnabled(false);
				showSolutionButton.setEnabled(false);
				findSolutionButton.setEnabled(true);
			}
		});
	}

	/**
	 * Enable all Solver Menu buttons except Find Solution button
	 */
	public void setSolution() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				doMoveButton.setEnabled(true);
				showSolutionButton.setEnabled(true);
				findSolutionButton.setEnabled(false);
			}
		});
	}

	@Override
	public void addMenuListener(MenuListener l) {
		menuListeners.add(l);
	}

	public ThresholdPrompt getThresholdPrompt() {
		return thresholdPrompt;
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(doMoveButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.DO_MOVE);
				}
			} else if (e.getSource().equals(findSolutionButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.FIND_SOLUTION);
				}
			} else if (e.getSource().equals(showSolutionButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.SHOW_SOLUTION);
				}
			}

		}

	}

}
