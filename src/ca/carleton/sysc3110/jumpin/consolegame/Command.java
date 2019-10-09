package ca.carleton.sysc3110.jumpin.consolegame;

import ca.carleton.sysc3110.jumpin.exception.JumpINException;

public interface Command {
	
	public static final String EXIT = "exit";
	
	public static final String MOVE = "move";
	
	public void parse(String command) throws InvalidCommandException;
	
	public void execute() throws JumpINException;
	
}
