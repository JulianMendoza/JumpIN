package jumpin.consolegame;

import jumpin.consolegame.command.commands.Help;

public class Printer {

	private static final String PREFIX = "JumpIN: ";	
	
	private static final String HELP = "Here are the list of other commands";
	
	
	public static void printError(String s) {
		System.err.println(PREFIX + s);
	}

	public static void print(String s) {
		System.out.println(PREFIX + s);
	}

	public static void printBoard(String board) {
		print("Here's the board!\n" + board);
	}

	public static void printWelcome(String board) {
		print("Welcome to JumpIN!");
		print("For help try " + Help.HELP);
		printBoard(board);
		print("Try \"move (x,y) direction\"");
	}
	
	public static void printHelp(String[] commands) {
		StringBuilder help = new StringBuilder(HELP);
		for(String command : commands) {
			help.append("\n" + command);
		}
		print(help.toString());
	}
	
	public static void printHelpCommand(String help) {
		print("Have you tried " + help);
	}

	
}
