package jumpin.view.builder.menu.menus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.game.menu.MenuFrame;
import jumpin.view.listener.MenuEvent;
import jumpin.view.listener.MenuListener;
import jumpin.view.util.GroupBox;

/**
 * 
 * @author Giuseppe
 *
 */
public class BuilderControlMenu extends GroupBox implements MenuFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8565271088138017918L;

	private List<MenuListener> menuListeners;

	private JButton validateLevel;
	private JButton back;
	private JButton saveLevel;

	private final String BACK = "Back";
	private final String VALIDATE = "Validate Level";
	private final String SAVE = "Save Level";

	public BuilderControlMenu(String boxName) {
		super(boxName);
		menuListeners = new ArrayList<MenuListener>();
		populate();
	}

	@Override
	public void populate() {
		setBackground(ViewConstants.BOARD_COLOR);
		setLayout(new GridLayout(0, 1, 0, 0));
		setForeground(ViewConstants.BOARD_COLOR);
		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);

		validateLevel = ComponentFactory.create3DMenuButton(VALIDATE);
		saveLevel = ComponentFactory.create3DMenuButton(SAVE);
		saveLevel.setEnabled(false);
		back = ComponentFactory.create3DMenuButton(BACK);
		addButtonListeners();
		add(validateLevel);
		add(saveLevel);
		add(back);
	}
	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		validateLevel.addActionListener(l);
		saveLevel.addActionListener(l);
		back.addActionListener(l);
	}

	@Override
	public void addMenuListener(MenuListener l) {
		menuListeners.add(l);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(validateLevel)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.VALIDATE_LEVEL);
				}
			} else if (e.getSource().equals(saveLevel)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.SAVE_LEVEL);
				}
			} else if (e.getSource().equals(back)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.BACK);
				}
			}

		}

	}

}
