package jumpin.view.style;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicButtonUI;

import jumpin.view.factory.FontFactory;

/**
 * A class that stylizes several JComponents by setting their font
 * 
 * @author Giuseppe
 */
public class Styler {
	/**
	 * Method to apply style to a component
	 * @param components
	 */
	public static void applyMenuStyle(JComponent... components) {
		Font buttonFont = FontFactory.createButtonFont(Font.BOLD, 14);
		Font menuFont = FontFactory.createMenuFont(Font.BOLD, 12);
		BasicButtonUI buttonUI = new Button3D();
		for (JComponent c : components) {
			if (c instanceof JButton) {
				c.setFont(buttonFont);
				((JButton) c).setUI(buttonUI);
			} else if (c instanceof JLabel) {
				c.setFont(menuFont);
			}
		}
	}

}
