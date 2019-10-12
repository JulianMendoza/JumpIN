package jumpin.consolegame.command.commands;

import jumpin.consolegame.command.Command;
import jumpin.consolegame.exception.InvalidCommandException;
import jumpin.exception.JumpINException;
import jumpin.model.Game;
import jumpin.model.constants.Direction;
import jumpin.model.util.Position;

public class MoveCommand implements Command {

	private Direction direction;
	private Position position;
	private int tilesMoved;
	private Game game;

	public MoveCommand(String command, Game game) throws InvalidCommandException {
		tilesMoved = -1;
		parse(command);
		this.game = game;
	}

	@Override
	public void parse(String command) throws InvalidCommandException {
		String args[] = command.split(" ");
		if (args.length < 3 || args.length > 4) {
			throw new InvalidCommandException("Invalid number of args for move command");
		}

		position = Position.parseString(args[1]);

		if (position == null) {
			throw new InvalidCommandException("Invalid position formatting for move command");
		}

		direction = Direction.parseString(args[2]);
		if (direction == null) {
			throw new InvalidCommandException("Invalid direction for move command");
		}
		
		if(args.length == 4) {
			try {
				tilesMoved = Integer.parseInt(args[3]);
			} catch(NumberFormatException e) {
				throw new InvalidCommandException("Invalid move value for move command");
			}
		}

	}

	@Override
	public void execute() throws JumpINException {
		game.movePiece(position, direction, tilesMoved);
	}

}
