package jumpin.model.constants;

/**
 * Enumeration for identifying different parts of the fox
 * 
 * @author Giuseppe
 */
public enum FoxPart {

	HEAD, TAIL;
	public static FoxPart getFoxPart(String foxpart) {
		switch(foxpart) {
		case "HEAD": return HEAD;
		case "TAIL": return TAIL;
		}
		return null;
	}
}