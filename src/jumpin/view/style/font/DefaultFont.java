package jumpin.view.style.font;

import java.awt.Font;

import jumpin.view.constants.FontConstants;

/**
 * A class that creates a 12-point plain font
 * 
 * @author Giuseppe
 */
public class DefaultFont extends Font {

	private static final long serialVersionUID = 5460735436140883020L;

	/**
	 * Default constructor for DefaultFont
	 */
	public DefaultFont() {
		super(FontConstants.DEFAULT_FONT, Font.PLAIN, 12);
	}

}
