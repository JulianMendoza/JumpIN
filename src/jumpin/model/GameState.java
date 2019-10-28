package jumpin.model;

import jumpin.model.board.BoardModelEvent;
import jumpin.model.board.BoardModelListener;
import jumpin.model.constants.StateOfGame;

/**
 * Knowledge of the number of rabbits left and the current state of the game.
 * 
 * @author Julian
 * @documentation Cameron Davis
 */
public class GameState implements BoardModelListener {
	/**
	 * Number of rabbits left
	 */
	private int rabbitsToWin;
	private StateOfGame gameState;

	public GameState() {
		this.rabbitsToWin = -1;
		this.gameState = null;
	}

	/**
	 * Constructor for the game's state
	 * 
	 * @param rabbitsToWin amount of rabbits left
	 * @param gameState    current state of the game
	 */
	public GameState(int rabbitsToWin, StateOfGame gameState) {
		this.rabbitsToWin = rabbitsToWin;
		this.gameState = gameState;
	}

	public void setState(StateOfGame state) {
		this.gameState = state;
	}

	public void setNumToWin(int num) {
		this.rabbitsToWin = num;
	}

	/**
	 * Gets the current game state
	 * 
	 * @return gameState
	 */
	public StateOfGame getState() {
		return this.gameState;
	}

	/**
	 * Gets how many rabbits to win
	 * 
	 * @return rabbitsToWin
	 */
	public int getNumToWin() {
		return this.rabbitsToWin;
	}

	/**
	 * Updates amount of rabbits left if they are on a hole or not and if none are
	 * left, the player has won the game.
	 */
	@Override
	public void update(int event) {
		if (event == BoardModelEvent.ON_RABBIT_HOLE) {
			rabbitsToWin--;
		} else {
			rabbitsToWin++;
		}
		if (rabbitsToWin == 0) {
			gameState = StateOfGame.YOU_WON;
		}
	}

	/**
	 * Prints the amount of rabbits left and the current state of the game
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Current # of rabbits on the board: " + rabbitsToWin + "\n");
		str.append("Current state of the game: " + gameState.toString() + "\n");
		return str.toString();
	}
}