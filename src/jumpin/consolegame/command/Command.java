package jumpin.consolegame.command;

import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;

/**
 * Interface for all commands
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public interface Command {

	public static final String EXIT = "exit";

	public static final String MOVE = "move";

	public static final String HELP = "help";

	/**
	 * Parses the specified command
	 * 
	 * @param command command to parse
	 * @throws InvalidCommandException if the command is not valid
	 */
	public void parse(String command) throws InvalidCommandException;

	/**
	 * Method for executing the command
	 * 
	 * @throws JumpINException if execution is unsuccessful
	 */
	public void execute() throws JumpINException;

}
