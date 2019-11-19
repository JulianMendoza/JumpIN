package jumpin.view.menu.menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
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
public class HistoryMenu extends GroupBox implements MenuFrame {

	List<MenuListener> menuListeners;

	private JButton undoMove;
	private JButton redoMove;

	private final String UNDO_TEXT = "\u2190 UNDO";
	private final String REDO_TEXT = "REDO \u2192";

	/**
	 * 
	 */
	private static final long serialVersionUID = -4841219774868685113L;

	public HistoryMenu() {
		super("History");
		populate();
	}

	@Override
	public void populate() {
		menuListeners = new ArrayList<MenuListener>();
		setBackground(ViewConstants.BOARD_COLOR);

		undoMove = ComponentFactory.create3DMenuButton(UNDO_TEXT);
		redoMove = ComponentFactory.create3DMenuButton(REDO_TEXT);
		undoMove.setEnabled(false);
		redoMove.setEnabled(false);

		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);

		addButtonListeners();

		add(undoMove);
		add(Box.createRigidArea(new Dimension(13, 0)));
		add(redoMove);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		undoMove.addActionListener(l);
		redoMove.addActionListener(l);
	}

	@Override
	public void addMenuListener(MenuListener l) {
		menuListeners.add(l);
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
				redoMove.setEnabled(enabled);
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
				undoMove.setEnabled(enabled);
			}
		});
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(undoMove)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.UNDO);
				}
			} else if (e.getSource().equals(redoMove)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.REDO);
				}
			}

		}

	}

}
