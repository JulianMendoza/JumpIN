package jumpin.view.game.menu.menus;

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
import jumpin.view.game.menu.listener.MenuEvent;
import jumpin.view.game.menu.listener.MenuListener;
import jumpin.view.util.GroupBox;

/**
 * 
 * @author Julian, Giuseppe, Cameron
 *
 */
public class LevelMenu extends GroupBox implements MenuFrame {

	private static final long serialVersionUID = 1L;

	private List<MenuListener> menuListeners;

	private JButton saveLevel;
	private JButton loadLevel;
	private JButton generateLevel;
	private JButton buildLevel;

	private final String SAVE = "Save Level";
	private final String LOAD = "Load Level";
	private final String GENERATE = "Generate Level";
	private final String BUILD = "Build Level";

	public LevelMenu() {
		super("Level");
		populate();
	}

	@Override
	public void populate() {
		menuListeners = new ArrayList<MenuListener>();
		setBackground(ViewConstants.BOARD_COLOR);

		saveLevel = ComponentFactory.create3DMenuButton(SAVE);
		loadLevel = ComponentFactory.create3DMenuButton(LOAD);
		generateLevel = ComponentFactory.create3DMenuButton(GENERATE);
		buildLevel = ComponentFactory.create3DMenuButton(BUILD);

		setLayout(new GridLayout(0, 1, 0, 0));
		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);
		addButtonListeners();
		add(saveLevel);
		add(loadLevel);
		add(generateLevel);
		add(buildLevel);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		saveLevel.addActionListener(l);
		loadLevel.addActionListener(l);
		generateLevel.addActionListener(l);
		buildLevel.addActionListener(l);
	}

	@Override
	public void addMenuListener(MenuListener l) {
		menuListeners.add(l);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(saveLevel)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.SAVE_LEVEL);
				}

			} else if (e.getSource().equals(loadLevel)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.LOAD_LEVEL);
				}

			} else if (e.getSource().equals(generateLevel)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.GENERATE_LEVEL);
				}

			} else if (e.getSource().equals(buildLevel)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.BUILD_LEVEL);
				}
			}
		}
	}
}
