package jumpin.model.constants;

public enum StateOfGame {
	YOU_WON, IN_PROGRESS;

	@Override
	public String toString() {
		if(this==YOU_WON) {
			return "YOU_WON";
		}else if(this==IN_PROGRESS) {
			return "IN_PROGRESS.";
		}else {
			return "";
		}
	}

}
