package jumpin.consolegame;

import java.util.Scanner;

import jumpin.consolegame.commands.Exit;
import jumpin.consolegame.commands.Move;
import jumpin.exception.JumpINException;
import jumpin.model.Game;

public class ConsoleGame extends Game {

	private final String PREFIX = "JumpIN: ";
		
	private Scanner scanner;

	public ConsoleGame() {
		super();
		scanner = new Scanner(System.in);
	}

	public void start() {
		printWelcome();
		while(true) {
			if(scanner.hasNext()) {
				Command command = parseCommand(scanner.nextLine());
				if(command != null) {
					try {
						command.execute();
						print("Successful move!");
						printBoard();
					} catch (JumpINException e) {
						printError(e.getMessage());
					}
				}
			}
		}
	}
	
	private Command parseCommand(String command) {
		command = command.toLowerCase().trim();
		if(command.startsWith(Command.MOVE)) {
			try {
				return  new Move(command, this);
			} catch (InvalidCommandException e) {
				printError(e.getMessage());
				print("Have you tried \"move (x,y) direction\"");
			}
		} else if(command.startsWith(Command.EXIT)) {
			try {
				return new Exit(command);
			} catch (InvalidCommandException e) {
				printError(e.getMessage());
				print("Have you tried \"exit\"");
			}
		} else {
			printError("Invalid command");
			print("Have you tried \"move (x,y) direction\" or \"exit\"");
		}
		return null;
	}
	
	private void printError(String s) {
		System.err.println(PREFIX + s);
	}
	
	private void print(String s) {
		System.out.println(PREFIX + s);
	}
	
	private void printBoard() {
		print("Here's the board!\n" + toString());
	}
	
	private void printWelcome() {
		print("Welcome to JumpIN!");
		printBoard();
		print("Try \"move (x,y) direction\"");
	}

}
