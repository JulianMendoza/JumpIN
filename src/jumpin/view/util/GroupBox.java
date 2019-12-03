package jumpin.view.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import jumpin.view.factory.FontFactory;

/**
 * A box to group components separate from each other in the main menu (Game State, History, Solver)
 * 
 * @author Giuseppe
 */
public class GroupBox extends JPanel {
	
	private static final long serialVersionUID = 558910162906322077L;
	/**
	 * Method to group the box
	 * @param boxName
	 */
	public GroupBox(String boxName) {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), boxName, TitledBorder.CENTER, TitledBorder.TOP, FontFactory.createMenuFont(Font.BOLD, 14), new Color(0, 0, 0)));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

}
