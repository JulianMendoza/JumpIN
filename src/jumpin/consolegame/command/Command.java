package jumpin.consolegame.command;

import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;

/**
 * Interface for all commands
 * 
 * @author Giuseppe
 *
 */
public interface Command {

	public static final String EXIT = "exit";

	public static final String MOVE = "move";
	
	public static final String HELP = "help";

	/**
	 * Method for parsing the a command
	 * 
	 * @param command
	 * @throws InvalidCommandException
	 * if parse fails
	 */
	public void parse(String command) throws InvalidCommandException;

	/**
	 * Method for executing the command
	 * 
	 * @throws JumpINException
	 */
	public void execute() throws JumpINException;
	
}
