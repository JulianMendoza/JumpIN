package jumpin.consolegame;

import jumpin.consolegame.command.CommandHelper;

/**
 * A class for printing all the console messages 
 * 
 * @author Giuseppe
 *
 */
public class Printer {

	private static final String PREFIX = "JumpIN: ";	
	
	private static final String HELP = "Here are the list of other commands:";
	
	/**
	 * Prints a message to error stream with prefix
	 * @param s
	 */
	public static void printError(String s) {
		System.err.println(PREFIX + s);
	}

	/**
	 * Prints a message to print stream with prefix
	 * @param s
	 */
	public static void print(String s) {
		System.out.println(PREFIX + s);
	}

	/**
	 * Prints the board
	 * @param board
	 */
	public static void printBoard(String board) {
		print("Here's the board!\n" + board);
	}

	/**
	 * Prints the welcome messages
	 * @param board
	 */
	public static void printWelcome(String board) {
		print("Welcome to JumpIN!");
		print("For help try " + CommandHelper.HELP);
		printBoard(board);
		printHelpCommand(CommandHelper.MOVE);
	}
	
	/**
	 * Prints the help messages for each command
	 * @param commands (CommandHelper)
	 */
	public static void printHelp(String[] commands) {
		StringBuilder help = new StringBuilder(HELP);
		for(String command : commands) {
			help.append("\n" + command);
		}
		print(help.toString());
	}
	
	/**
	 * Prints a help message for the command
	 * @param help
	 */
	public static void printHelpCommand(String help) {
		print("Have you tried " + help);
	}

}
