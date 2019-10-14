package jumpin.consolegame.command.commands;

import jumpin.consolegame.command.Command;
import jumpin.consolegame.exception.InvalidCommandException;

public class Exit implements Command {
	
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
