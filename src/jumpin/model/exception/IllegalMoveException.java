package jumpin.model.exception;

import jumpin.exception.JumpINException;

/**
 * Exception for Illegal Moves
 * 
 * @author Giuseppe
 */
public class IllegalMoveException extends JumpINException {
	
	/**
	 * Construct an IllegalMoveException with specified detail message
	 * 
	 * @param message the exception detail message
	 */
	public IllegalMoveException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4633007134596874030L;

}
