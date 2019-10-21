package jumpin.view.menu;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import jumpin.view.constants.ComponentSize;

public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -436968148338186761L;

	public MainMenu() {
		setBounds(0, 0, ComponentSize.MENU_WIDTH, ComponentSize.MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(new Color(2, 145, 55));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

}
