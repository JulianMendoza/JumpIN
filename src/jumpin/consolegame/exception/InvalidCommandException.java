package jumpin.consolegame.exception;

import jumpin.exception.JumpINException;

/**
 * Exception for Invalid Commands
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class InvalidCommandException extends JumpINException {

	private static final long serialVersionUID = -9008364331848936548L;
	
	/**
	 * Constructs a InvalidCommandException with the specified message
	 * 
	 * @param message the detail message
	 */
	public InvalidCommandException(String message) {
		super(message);
	}

}
