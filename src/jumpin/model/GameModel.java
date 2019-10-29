package jumpin.model;

import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.constants.MoveConstants;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.exception.NoTileException;
import jumpin.model.move.Move;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.LevelGenerator;
import jumpin.model.util.Position;

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
	 */
	public GameModel() {
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