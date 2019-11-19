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
		case "head": return HEAD;
		case "tail": return TAIL;
		}
		return null;
	}
}