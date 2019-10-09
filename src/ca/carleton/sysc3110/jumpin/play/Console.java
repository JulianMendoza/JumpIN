package ca.carleton.sysc3110.jumpin.play;

import ca.carleton.sysc3110.jumpin.consolegame.ConsoleGame;
import ca.carleton.sysc3110.jumpin.model.Game;
import ca.carleton.sysc3110.jumpin.model.Position;
import ca.carleton.sysc3110.jumpin.model.constants.Direction;
import ca.carleton.sysc3110.jumpin.model.exception.IllegalMoveException;
import ca.carleton.sysc3110.jumpin.model.exception.NoPieceException;

public class Console {

	public static void main(String args[]) throws NoPieceException, IllegalMoveException {
		ConsoleGame game = new ConsoleGame();
		game.start();
	}
	
	
}
