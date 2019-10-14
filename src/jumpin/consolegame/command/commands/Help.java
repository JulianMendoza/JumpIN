package jumpin.consolegame.command.commands;

import jumpin.consolegame.Printer;
import jumpin.consolegame.command.Command;
import jumpin.consolegame.command.CommandHelper;
import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;

public class Help implements Command {
	
	public Help(String command) throws InvalidCommandException {
		parse(command);
	}
	
	@Override
	public void parse(String command) throws InvalidCommandException {
		if(!command.equals(HELP)) {
			throw new InvalidCommandException("Invalid help command");
		}
	}

	@Override
	public void execute() throws JumpINException {
		Printer.printHelp(CommandHelper.HELPER);
	}


}
