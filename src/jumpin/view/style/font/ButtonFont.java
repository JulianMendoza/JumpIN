package jumpin.view.style.font;

import java.awt.Font;

import jumpin.view.constants.FontConstants;

/**
 * A Class that creates a specified font for menu buttons
 * 
 * @author Giuseppe
 */
public class ButtonFont extends Font {

	private static final long serialVersionUID = 6744221501014611535L;
	
	/**
	 * Create a certain button text font
	 * 
	 * @param style int value of the style
	 * @param size int values of the size
	 */
	public ButtonFont(int style, int size) {
		super(FontConstants.BUTTON_FONT, style, size);
	}

}
