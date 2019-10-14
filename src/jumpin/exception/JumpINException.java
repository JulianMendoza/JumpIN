package jumpin.exception;

/**
 * Parent Exception for all exceptions in JumpIN model
 * 
 * @author Giuseppe Papalia
 */
public class JumpINException extends Exception {
	
	/**
	 * Constructs a JumpINException with the specified message
	 * @param message the detail message
	 */
	public JumpINException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -7664817730285277499L;

}
