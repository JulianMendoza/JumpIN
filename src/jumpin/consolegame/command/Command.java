package jumpin.consolegame.command;

import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;

/**
 * Interface for all commands
 * <p>
 * Contains constant Strings of all the commands
 * @author Giuseppe Papalia
 *
 */
public interface Command {

	public static final String EXIT = "exit";

	public static final String MOVE = "move";

	public void parse(String command) throws InvalidCommandException;

	public void execute() throws JumpINException;

}
