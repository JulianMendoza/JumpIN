package jumpin.model.exception;

import jumpin.exception.JumpINException;

/**
 * Exception for trying to select a tile with no piece
 * @author Giuseppe
 *
 */
public class NoPieceException extends JumpINException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5727709423860244117L;

	/**
	 * Construct a NoPieceException message
	 */
	public NoPieceException() {
		super("There is no piece at the selected board index");
	}

}
