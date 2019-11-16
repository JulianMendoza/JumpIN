package jumpin.model;

import jumpin.model.board.Board;
import jumpin.model.util.LevelGenerator;

/**
 * A class that constructs the game
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class GameModel {

	private Board board;
	private GameState gameState;
	private LevelGenerator generator;

	/**
	 * Default constructor for the Game
	 * @throws CloneNotSupportedException 
	 */
	public GameModel() throws CloneNotSupportedException {
		board = new Board();
		gameState = new GameState();
		generator = new LevelGenerator(this.board, this.gameState);
	}

	public Board getBoard() {
		return board;
	}

	public GameState getGameState() {
		return this.gameState;
	}

	public LevelGenerator getGenerator() {
		return generator;
	}

	/**
	 * Generates string representation of the game's state and board
	 * 
	 * @return string of game state and board
	 */
	@Override
	public String toString() {
		return gameState.toString() + board.toString();
	}

}