package jumpin.view.style.font;

import java.awt.Font;

import jumpin.view.constants.FontConstants;

/**
 * A class that creates a specified font for the main menu
 * 
 * @author Giuseppe
 */
public class MenuFont extends Font {

	private static final long serialVersionUID = -8031205800990957209L;
	
	/**
	 * Create a font with certain style and size
	 * 
	 * @param style int value of the style
	 * @param size int value of the size
	 */
	public MenuFont(int style, int size) {
		super(FontConstants.MENU_FONT, style, size);
	}

}
