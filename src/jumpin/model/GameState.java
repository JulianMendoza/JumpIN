package jumpin.model;

import jumpin.model.board.BoardModelEvent;
import jumpin.model.board.BoardModelListener;

/**
 * Knowledge of the number of rabbits left and the current state of the game.
 * 
 * @author Julian
 *
 */
public class GameState implements BoardModelListener {
	private int rabbitsToWin;
	private String gameState;

	public GameState(int rabbitsToWin, String gameState) {
		this.rabbitsToWin = rabbitsToWin;
		this.gameState = gameState;
	}

	@Override
	public void update(int event) {
		if (event == BoardModelEvent.ON_RABBIT_HOLE) {
			rabbitsToWin--;
		} else {
			rabbitsToWin++;
		}
		if (rabbitsToWin == 0) {
			gameState = "YOU WON!";
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Current # of rabbits on the board:" + rabbitsToWin + "\n");
		str.append("Current state of the game:" + gameState + "\n");
		return str.toString();
	}
}
