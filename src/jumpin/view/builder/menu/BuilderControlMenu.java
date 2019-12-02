package jumpin.view.builder.menu;

import java.awt.GridLayout;

import javax.swing.JButton;

import jumpin.view.AbstractFrame;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.util.GroupBox;

/**
 * 
 * @author Giuseppe
 *
 */
public class BuilderControlMenu extends GroupBox implements AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8565271088138017918L;

	private JButton validateLevel;
	private JButton saveLevel;

	private final String VALIDATE = "Validate Level";
	private final String SAVE = "Save Level";

	public BuilderControlMenu(String boxName) {
		super(boxName);
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

		add(validateLevel);
		add(saveLevel);
	}

}
