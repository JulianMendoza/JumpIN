package ca.carleton.sysc3110.jumpin.model.exception;

import ca.carleton.sysc3110.jumpin.exception.JumpINException;

public class NoPieceException extends JumpINException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5727709423860244117L;

	public NoPieceException() {
		super("There is no piece at the selected board index");
	}
	
}
