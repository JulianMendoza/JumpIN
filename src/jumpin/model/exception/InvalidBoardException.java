package jumpin.model.exception;

import jumpin.exception.JumpINException;

/**
 * 
 * @author Giuseppe
 *
 */
public class InvalidBoardException extends JumpINException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1371940742259078563L;

	public InvalidBoardException(String message) {
		super(message);
	}

}
