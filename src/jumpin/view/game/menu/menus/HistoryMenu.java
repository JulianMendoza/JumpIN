package jumpin.view.game.menu.menus;

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
import jumpin.view.game.menu.MenuFrame;
import jumpin.view.listener.MenuEvent;
import jumpin.view.listener.MenuListener;
import jumpin.view.util.GroupBox;

/**
 * Game History Menu component of the main menu
 * 
 * @author Giuseppe
 */
public class HistoryMenu extends GroupBox implements MenuFrame {

	private List<MenuListener> menuListeners;

	private JButton undoMoveButton;
	private JButton redoMoveButton;

	private final String UNDO_TEXT = "<UNDO"; //\u2190 unicode if you want to resize the button
	private final String REDO_TEXT = "REDO>"; //\u2192 unicode if you want to resize the button
	
	private static final long serialVersionUID = -4841219774868685113L;

	public HistoryMenu() {
		super("History");
		populate();
	}
	
	/**
	 * Fill History Menu with necessary components
	 */
	@Override
	public void populate() {
		menuListeners = new ArrayList<MenuListener>();
		setBackground(ViewConstants.BOARD_COLOR);

		undoMoveButton = ComponentFactory.create3DMenuButton(UNDO_TEXT);
		redoMoveButton = ComponentFactory.create3DMenuButton(REDO_TEXT);
		undoMoveButton.setEnabled(false);
		redoMoveButton.setEnabled(false);

		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);

		addButtonListeners();

		add(undoMoveButton);
		add(Box.createRigidArea(new Dimension(8, 0)));
		add(redoMoveButton);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		undoMoveButton.addActionListener(l);
		redoMoveButton.addActionListener(l);
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
				redoMoveButton.setEnabled(enabled);
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
				undoMoveButton.setEnabled(enabled);
			}
		});
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(undoMoveButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.UNDO);
				}
			} else if (e.getSource().equals(redoMoveButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.REDO);
				}
			}

		}

	}

}
