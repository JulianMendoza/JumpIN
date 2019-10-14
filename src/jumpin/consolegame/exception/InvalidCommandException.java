package jumpin.consolegame.exception;

import jumpin.exception.JumpINException;

/**
 * Exception for Invalid Commands
 * @author Giuseppe
 *
 */
public class InvalidCommandException extends JumpINException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9008364331848936548L;

	public InvalidCommandException(String message) {
		super(message);
	}

}
