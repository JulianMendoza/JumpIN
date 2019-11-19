package jumpin.view.factory;

import java.awt.Image;
import java.awt.Toolkit;

import jumpin.view.constants.ImageConstants;

/**
 * <b>Factory for creating images</b>
 * <p>
 * if there is extra work to do to create an image it goes here
 * <p>
 * 
 * @author Giuseppe
 *
 */
public class ImageFactory {

	/**
	 * Method to generate rabbit Image
	 * 
	 * @return a rabbit image from ImageConstants
	 */
	public static Image generateRabbit() {
		return generateImage(ImageConstants.RABBIT_1);
	}

	/**
	 * Method to generate an image given a string representation
	 * 
	 * @param filename string of image
	 * @return generated image
	 */
	public static Image generateImage(String filename) {
		return Toolkit.getDefaultToolkit().createImage(filename);
	}

}
