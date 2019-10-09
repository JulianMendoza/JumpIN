package ca.carleton.sysc3110.jumpin.consolegame;

import ca.carleton.sysc3110.jumpin.exception.JumpINException;

public class InvalidCommandException extends JumpINException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9008364331848936548L;

	public InvalidCommandException(String message) {
		super(message);
	}

}
