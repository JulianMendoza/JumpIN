package main;

import jumpin.model.Game;
import jumpin.model.Position;
import jumpin.model.constants.Direction;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;

public class Main {

	public static void main(String args[]) throws NoPieceException, IllegalMoveException {
		Game game = new Game();
		game.movePiece(new Position(1, 0), Direction.EAST);
		System.out.println("\n" + game.toString());
	}
	
	
}
