package jumpin.view.game.menu.menus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import John.GeneratePrompt;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.game.menu.MenuFrame;
import jumpin.view.game.menu.listener.MenuEvent;
import jumpin.view.game.menu.listener.MenuListener;
import jumpin.view.util.GroupBox;

/**
 * 
 * @author Julian, Giuseppe
 *
 */
public class LevelMenu extends GroupBox implements MenuFrame {

	private static final long serialVersionUID = 1L;

	private List<MenuListener> menuListeners;
	
	private GeneratePrompt generatePrompt;
	private JButton saveLevelBtn;
	private JButton loadLevelBtn;
	private JButton levelBuilderBtn;

	private final String SAVE = "Save Level";
	private final String LOAD = "Load Level";
	private final String GENERATE = "Level Builder";

	public LevelMenu() {
		super("Level");
		populate();
	}

	@Override
	public void populate() {
		menuListeners = new ArrayList<MenuListener>();
		setBackground(ViewConstants.BOARD_COLOR);

		generatePrompt = new GeneratePrompt();
		saveLevelBtn = ComponentFactory.create3DMenuButton(SAVE);
		loadLevelBtn = ComponentFactory.create3DMenuButton(LOAD);
		levelBuilderBtn = ComponentFactory.create3DMenuButton(GENERATE);

		setLayout(new GridLayout(0, 1, 0, 0));
		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);
		addButtonListeners();
		add(saveLevelBtn);
		add(loadLevelBtn);
		add(levelBuilderBtn);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		saveLevelBtn.addActionListener(l);
		loadLevelBtn.addActionListener(l);
		levelBuilderBtn.addActionListener(l);
	}

	@Override
	public void addMenuListener(MenuListener l) {
		menuListeners.add(l);
	}
	public GeneratePrompt getGeneratePrompt() {
		return generatePrompt;
	}
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(saveLevelBtn)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.SAVE_LEVEL);
				}
			} else if (e.getSource().equals(loadLevelBtn)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.LOAD_LEVEL);
				}
			} else if (e.getSource().equals(levelBuilderBtn)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.GENERATE_LEVEL);
				}
			}

		}

	}

}
