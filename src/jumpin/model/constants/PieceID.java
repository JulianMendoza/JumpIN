package jumpin.model.constants;

/**
 * Constants related to pieces
 * 
 * @author Giuseppe
 */
public class PieceID {

	public static final String RABBIT = "Rabbit";

	public static final String FOX = "Fox";

	public static final String MUSHROOM = "Mushroom";

	public static final String FOX_ID_1 = FOX + "1";

	public static final String FOX_ID_2 = FOX + "2";

	public static final String RABBIT_ID_1 = RABBIT + "1";

	public static final String RABBIT_ID_2 = RABBIT + "2";

	public static final String RABBIT_ID_3 = RABBIT + "3";

	public static final String EMPTY = "---";
	
	public static String getRabbitPieceID(String id) {
		switch(id) {
		case "1": return RABBIT_ID_1;
		case "2": return RABBIT_ID_2;
		case "3": return RABBIT_ID_3;
		}
		return null;
	}
	public static String getFoxPieceID(String id) {
		switch(id) {
		case "1": return FOX_ID_1;
		case "2": return FOX_ID_2;
		}
		return null;
	}
}