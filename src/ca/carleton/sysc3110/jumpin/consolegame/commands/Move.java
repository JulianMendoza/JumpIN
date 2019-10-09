package ca.carleton.sysc3110.jumpin.consolegame.commands;

import ca.carleton.sysc3110.jumpin.consolegame.Command;
import ca.carleton.sysc3110.jumpin.consolegame.InvalidCommandException;
import ca.carleton.sysc3110.jumpin.exception.JumpINException;
import ca.carleton.sysc3110.jumpin.model.Game;
import ca.carleton.sysc3110.jumpin.model.Position;
import ca.carleton.sysc3110.jumpin.model.constants.Direction;

public class Move implements Command {

	private Direction direction;
	private Position position;
	private Game game;
	
	public Move(String command, Game game) throws InvalidCommandException {
		parse(command);
		this.game = game;
	}
	
	
	@Override
	public void parse(String command) throws InvalidCommandException {
		String args[] = command.split(" ");
		if(args.length != 3) {
			throw new InvalidCommandException("Invalid number of args for move command");
		}
		
		position = Position.parseString(args[1]);
		
		if(position == null) {
			throw new InvalidCommandException("Invalid position formatting for move command");
		}
		
		direction = Direction.parseString(args[2]);
		if(direction == null) {
			throw new InvalidCommandException("Invalid direction for move command");
		}
		
	}

	@Override
	public void execute() throws JumpINException {
		game.movePiece(position, direction);
	}

}
