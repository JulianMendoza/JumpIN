package play;

import jumpin.consolegame.ConsoleGame;
import jumpin.model.exception.IllegalMoveException;

public class Console {

	public static void main(String args[]) throws IllegalMoveException, CloneNotSupportedException {
		ConsoleGame game = new ConsoleGame();
		game.start();
	}

}