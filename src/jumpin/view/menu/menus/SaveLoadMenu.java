package jumpin.view.menu.menus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.menu.MenuFrame;
import jumpin.view.menu.listener.MenuEvent;
import jumpin.view.menu.listener.MenuListener;
import jumpin.view.util.GroupBox;

public class SaveLoadMenu extends GroupBox implements MenuFrame {

	private static final long serialVersionUID = 1L;

	private List<MenuListener> menuListeners;

	private JButton saveXMLButton;
	private JButton loadXMLButton;
	private JButton saveTXTButton;
	private JButton loadTXTButton;

	private final String SAVEXML = "Save to XML";
	private final String LOADXML = "Load from XML";
	private final String SAVETXT = "Serialize";
	private final String LOADTXT = "Deserialize";

	public SaveLoadMenu() {
		super("Save/Load");
		populate();
	}

	@Override
	public void populate() {
		menuListeners = new ArrayList<MenuListener>();
		setBackground(ViewConstants.BOARD_COLOR);

		saveXMLButton = ComponentFactory.create3DMenuButton(SAVEXML);
		loadXMLButton = ComponentFactory.create3DMenuButton(LOADXML);
		saveTXTButton = ComponentFactory.create3DMenuButton(SAVETXT);
		loadTXTButton = ComponentFactory.create3DMenuButton(LOADTXT);

		setLayout(new GridLayout(0, 1, 0, 0));
		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);
		addButtonListeners();
		add(saveXMLButton);
		add(loadXMLButton);
		add(saveTXTButton);
		add(loadTXTButton);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		saveXMLButton.addActionListener(l);
		loadXMLButton.addActionListener(l);
		saveTXTButton.addActionListener(l);
		loadTXTButton.addActionListener(l);
	}

	@Override
	public void addMenuListener(MenuListener l) {
		menuListeners.add(l);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(saveXMLButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.XML_SAVE);
				}
			} else if (e.getSource().equals(loadXMLButton)) {

			} else if (e.getSource().equals(loadTXTButton)) {

			} else if (e.getSource().equals(saveTXTButton)) {

			}

		}

	}

}
