package jumpin.model;

import jumpin.model.board.Board;

/**
 * A class that constructs the game
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class GameModel {

	private Board board;
	private GameState gameState;

	/**
	 * Default constructor for the Game
	 * 
	 */
	public GameModel() {
		board = new Board();
		gameState = new GameState();
	}

	public GameModel(Board board, GameState gameState) {
		this.board = board;
		this.gameState = gameState;
	}

	public Board getBoard() {
		return board;
	}

	public GameState getGameState() {
		return this.gameState;
	}

	public void setBoard(Board b) {
		this.board = b;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
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