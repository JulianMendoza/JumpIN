package jumpin.consolegame.command.commands;

import java.text.ParseException;

import jumpin.consolegame.command.Command;
import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;
import jumpin.model.Game;
import jumpin.model.constants.Direction;
import jumpin.model.util.Position;

/**
 * Command implementation for "Move"
 * @author Giuseppe
 *
 */
public class MoveCommand implements Command {
	
	private Direction direction;
	private Position position;
	private int distance;
	private Game game;
	
	/**
	 * Constructor for Move command
	 * <p>
	 * Parses the command
	 * @param command
	 * @param game - instance of the game this command is being parsed for
	 * @throws InvalidCommandException
	 * if parse fails
	 */
	public MoveCommand(String command, Game game) throws InvalidCommandException {
		distance = -1;
		parse(command);
		this.game = game;
	}

	@Override
	public void parse(String command) throws InvalidCommandException {
		String args[] = command.split(" ");
		if (args.length < 3 || args.length > 4) { //3 or 4 arguments are valid for the move command
			throw new InvalidCommandException("Invalid number of args for move command");
		}

		try {
			position = Position.parseString(args[1]);
		} catch (ParseException e) {
			throw new InvalidCommandException("Invalid position (" + e.getMessage() + ") for move command");
		}

		try {
			direction = Direction.parseString(args[2]);
		} catch (ParseException e) {
			throw new InvalidCommandException("Invalid direction (" + e.getMessage() + ") for move command");
		}
		
		if(args.length == 4) {
			try {
				distance = Integer.parseInt(args[3]);
			} catch(NumberFormatException e) {
				throw new InvalidCommandException("Invalid move value for move command");
			}
		}

	}

	@Override
	public void execute() throws JumpINException {
		game.movePiece(position, direction, distance);
	}


}