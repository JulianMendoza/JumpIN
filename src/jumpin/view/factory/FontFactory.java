package jumpin.view.factory;

import java.awt.Font;

import jumpin.view.font.DefaultFont;

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

	public static Font createDefaultFont() {
		return new DefaultFont();
	}

}
