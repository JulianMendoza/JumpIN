package jumpin.play;

import jumpin.consolegame.ConsoleGame;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;

public class Console {

	public static void main(String args[]) throws NoPieceException, IllegalMoveException {
		ConsoleGame game = new ConsoleGame();
		game.start();
	}

}
