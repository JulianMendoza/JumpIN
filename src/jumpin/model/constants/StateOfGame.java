package jumpin.model.constants;

/**
 * Class for printing game status
 * 
 * @author Julian
 */
public enum StateOfGame {
	WON, IN_PROGRESS;

	/**
	 * Checks if game is in progress or won, and prints result
	 */
	@Override
	public String toString() {
		if (this == WON) {
			return "YOU_WON";
		} else if (this == IN_PROGRESS) {
			return "IN_PROGRESS.";
		} else {
			return "";
		}
	}

}