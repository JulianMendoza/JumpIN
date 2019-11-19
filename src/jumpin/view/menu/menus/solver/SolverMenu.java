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
 * 
 * @author Giuseppe
 *
 */
public class SolverMenu extends GroupBox implements MenuFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2729791032030947685L;
	private ThresholdPrompt thresholdPrompt;

	private List<MenuListener> menuListeners;

	private JButton doMove;
	private JButton findSolution;
	private JButton showSolution;

	private final String DO_MOVE_TEXT = "Do Best Move";
	private final String FIND_SOLUTION_TEXT = "Find Solution";
	private final String SHOW_SOLUTION_TEXT = "Show Solution";

	public SolverMenu() {
		super("Solver");
		populate();
	}

	@Override
	public void populate() {
		menuListeners = new ArrayList<MenuListener>();

		setBackground(ViewConstants.BOARD_COLOR);

		thresholdPrompt = new ThresholdPrompt();
		doMove = ComponentFactory.create3DMenuButton(DO_MOVE_TEXT);
		findSolution = ComponentFactory.create3DMenuButton(FIND_SOLUTION_TEXT);
		showSolution = ComponentFactory.create3DMenuButton(SHOW_SOLUTION_TEXT);

		doMove.setEnabled(false);
		showSolution.setEnabled(false);

		setLayout(new GridLayout(0, 1, 0, 0));

		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);

		addButtonListeners();
		add(findSolution);
		add(doMove);
		add(showSolution);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		doMove.addActionListener(l);
		findSolution.addActionListener(l);
		showSolution.addActionListener(l);
	}

	public void resetSolution() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				doMove.setEnabled(false);
				showSolution.setEnabled(false);
				findSolution.setEnabled(true);
			}
		});
	}

	public void setSolution() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				doMove.setEnabled(true);
				showSolution.setEnabled(true);
				findSolution.setEnabled(false);
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
			if (e.getSource().equals(doMove)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.DO_MOVE);
				}
			} else if (e.getSource().equals(findSolution)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.FIND_SOLUTION);
				}
			} else if (e.getSource().equals(showSolution)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.SHOW_SOLUTION);
				}
			}

		}

	}

}
