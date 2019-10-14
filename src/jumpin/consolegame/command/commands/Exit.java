package jumpin.consolegame.command.commands;

import jumpin.consolegame.command.Command;
import jumpin.consolegame.exception.InvalidCommandException;

/**
 * Command implementation for "Exit"
 * 
 * @author Giuseppe
 */
public class Exit implements Command {
	
	/**
	 * Constructor for Exit command
	 * <p>
	 * Parses the command
	 * 
	 * @param command	command to parse
	 * @throws InvalidCommandException	if the specified command is not valid
	 */
	public Exit(String command) throws InvalidCommandException {
		parse(command);
	}

	@Override
	public void parse(String command) throws InvalidCommandException {
		if (!command.equals(EXIT)) {
			throw new InvalidCommandException("Invalid exit command");
		}
	}

	@Override
	public void execute() {
		System.exit(0);
	}


}
