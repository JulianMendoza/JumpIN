package jumpin.view.factory;

import java.awt.Font;

import jumpin.view.style.font.ButtonFont;
import jumpin.view.style.font.DefaultFont;
import jumpin.view.style.font.MenuFont;

/**
 * <b>Factory for creating font</b>
 * <p>
 * if there is extra work to do to create a font it goes here
 * <p>
 * (there is none currently)
 * 
 * @author Giuseppe
 *
 */
public class FontFactory {

	/**
	 * Method to return default font
	 * 
	 * @return the default font
	 */
	public static Font createDefaultFont() {
		return new DefaultFont();
	}

	public static Font createMenuFont(int style, int size) {
		return new MenuFont(style, size);
	}

	public static Font createButtonFont(int style, int size) {
		return new ButtonFont(style, size);
	}

}
