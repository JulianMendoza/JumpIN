package jumpin.consolegame.commands;

import jumpin.consolegame.Command;
import jumpin.consolegame.InvalidCommandException;

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
