package jumpin.model.exception;

import jumpin.exception.JumpINException;

/**
 * Exception thrown if the board model is given an invalid position
 * 
 * @author Giuseppe
 *
 */
public class NoTileException extends JumpINException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2356793417164924405L;

	public NoTileException() {
		super("Position on the board");
	}

}
