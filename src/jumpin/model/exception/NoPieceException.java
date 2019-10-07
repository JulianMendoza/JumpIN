package jumpin.model.exception;

public class NoPieceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5727709423860244117L;

	public NoPieceException() {
		super("There is no piece at the selected board index");
	}
	
}
