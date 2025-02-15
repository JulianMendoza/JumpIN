package jumpin.exception;

/**
 * Parent Exception for all exceptions in JumpIN model
 * 
 * @author Giuseppe Papalia
 * @Documentation Cameron Davis
 */
public class JumpINException extends Exception {

	private static final long serialVersionUID = -7664817730285277499L;

	/**
	 * Constructs a JumpINException with the specified message
	 * 
	 * @param message the detail message
	 */
	public JumpINException(String message) {
		super(message);
	}

}