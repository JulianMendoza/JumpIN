package jumpin.model.util;

import jumpin.model.board.RabbitHoleEvent;

/**
 * Knowledge of the number of rabbits left and the current state of the game.
 * 
 * @author Julian
 *
 */
public class GameState implements RabbitHoleListener {
	private int rabbitsToWin;
	private String gameState;

	public GameState(int rabbitsToWin, String gameState) {
		this.rabbitsToWin = rabbitsToWin;
		this.gameState = gameState;
	}

	@Override
	public void update(int event) {
		if (event == RabbitHoleEvent.ON) {
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
