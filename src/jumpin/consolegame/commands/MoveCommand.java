package jumpin.consolegame.commands;

import jumpin.consolegame.Command;
import jumpin.consolegame.InvalidCommandException;
import jumpin.exception.JumpINException;
import jumpin.model.Game;
import jumpin.model.Position;
import jumpin.model.constants.Direction;

public class MoveCommand implements Command {

	private Direction direction;
	private Position position;
	private Game game;

	public MoveCommand(String command, Game game) throws InvalidCommandException {
		parse(command);
		this.game = game;
	}

	@Override
	public void parse(String command) throws InvalidCommandException {
		String args[] = command.split(" ");
		if (args.length != 3) {
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

	}

	@Override
	public void execute() throws JumpINException {
		game.movePiece(position, direction);
	}

}
