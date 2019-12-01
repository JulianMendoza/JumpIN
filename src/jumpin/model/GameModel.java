package jumpin.model;

import John.GenerateLevel;
import jumpin.model.board.Board;
import jumpin.model.file.LevelGenerator;

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
	private GenerateLevel level;

	/**
	 * Default constructor for the Game
	 * 
	 */
	public GameModel() {
		board = new Board();
		gameState = new GameState();
		generator = new LevelGenerator(this.board, this.gameState);
		level=new GenerateLevel(this.board,this.gameState);
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
	public GenerateLevel getLevel() {
		return level;
	}
	public void setBoard(Board b) {
		this.board=b;
		generator = new LevelGenerator(this.board, this.gameState);
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