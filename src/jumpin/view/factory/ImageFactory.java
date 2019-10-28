package jumpin.view.factory;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import jumpin.view.constants.ImageConstants;

public class ImageFactory {

	public static Image generateRabbit() {
		return generateImage(ImageConstants.RABBIT_1);
	}

	public static List<Image> generateRabbitMove() {
		List<Image> rabbitMove = new ArrayList<Image>();
		for (String filename : ImageConstants.RABBIT_MOVES) {
			rabbitMove.add(generateImage(filename));
		}
		return rabbitMove;
	}

	public static Image generateImage(String filename) {
		return Toolkit.getDefaultToolkit().createImage(filename);
	}

}
