package jumpin.view.font;

import java.awt.Font;

import jumpin.view.constants.FontConstants;

/**
 * 
 * @author Giuseppe
 *
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
