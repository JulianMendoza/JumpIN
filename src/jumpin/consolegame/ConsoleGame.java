package jumpin.consolegame;

import java.util.Scanner;

import jumpin.consolegame.command.Command;
import jumpin.consolegame.command.CommandHelper;
import jumpin.consolegame.command.commands.Exit;
import jumpin.consolegame.command.commands.Help;
import jumpin.consolegame.command.commands.MoveCommand;
import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;
import jumpin.model.Game;

/**
 * A class for creating a console game
 * 
 * @author Giuseppe
 *
 */
public class ConsoleGame extends Game {

	private Scanner scanner;

	/**
	 * Constructs a Console Game
	 */
	public ConsoleGame() {
		super();
		scanner = new Scanner(System.in);
	}

	/**
	 * Starts the game. Welcomes the player and continuously polls user commands.
	 */
	public void start() {
		Printer.printWelcome(toString());
		while (true) { //Continuously parses commands
			if (scanner.hasNext()) {
				Command command = parseCommand(scanner.nextLine());
				if (command != null) {
					try {
						command.execute();
						Printer.print("Successful move!");
						Printer.printBoard(toString());
					} catch (JumpINException e) {
						Printer.printError(e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * Attempts to parse the command
	 * 
	 * @param command	command to parse
	 * @return command that was parsed
	 */
	private Command parseCommand(String command) {
		command = command.toLowerCase().trim();
		if (command.startsWith(Command.MOVE)) {
			try {
				return new MoveCommand(command, this);
			} catch (InvalidCommandException e) {
				Printer.printError(e.getMessage());
				Printer.printHelpCommand(CommandHelper.MOVE);
			}
		} else if (command.startsWith(Command.EXIT)) {
			try {
				return new Exit(command);
			} catch (InvalidCommandException e) {
				Printer.printError(e.getMessage());
				Printer.printHelpCommand(CommandHelper.EXIT);
			}
		} else if (command.startsWith(Command.HELP)) {
			try {
				return new Help(command);
			} catch (InvalidCommandException e) {
				Printer.printError(e.getMessage());
				Printer.printHelpCommand(CommandHelper.HELP);
			}
		} else {
			Printer.printError("Invalid command");
			Printer.printHelpCommand(CommandHelper.HELP);
		}
		return null;
	}

}
