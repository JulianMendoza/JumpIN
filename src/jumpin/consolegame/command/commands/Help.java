package jumpin.consolegame.command.commands;

import jumpin.consolegame.Printer;
import jumpin.consolegame.command.Command;
import jumpin.consolegame.command.CommandHelper;
import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;

/**
 * Command implementation for "Help"
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class Help implements Command {

	/**
	 * Constructor for Help command
	 * <p>
	 * Parses the command
	 * 
	 * @param command command to parse
	 * @throws InvalidCommandException if the specified command is not valid
	 */
	public Help(String command) throws InvalidCommandException {
		parse(command);
	}

	@Override
	public void parse(String command) throws InvalidCommandException {
		if (!command.equals(HELP)) {
			throw new InvalidCommandException("Invalid help command");
		}
	}

	@Override
	public void execute() throws JumpINException {
		Printer.printHelp(CommandHelper.HELPER);
	}

}
