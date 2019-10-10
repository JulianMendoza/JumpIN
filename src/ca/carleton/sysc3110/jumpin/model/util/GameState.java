package ca.carleton.sysc3110.jumpin.model.util;

import ca.carleton.sysc3110.jumpin.model.board.RabbitHoleEvent;

/*
 * Knowledge of 
 * the number of rabbits left
 * and the current state of the game. 
 */
public class GameState implements RabbitHoleListener{
	private int rabbitsToWin;
	private String gameState;
	public GameState(int rabbitsToWin,String gameState) {
		this.rabbitsToWin=rabbitsToWin;
		this.gameState=gameState;
	}
	@Override
	public void update(RabbitHoleEvent event) {
		if(event.getEvent()==true) {
			rabbitsToWin--;
		}else {
			rabbitsToWin++;
		}
		if(rabbitsToWin==0) {
			gameState="YOU WON!";
		}
	}
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Current # of rabbits on the board:"+rabbitsToWin+"\n");
		str.append("Current state of the game:"+gameState+"\n");
		return str.toString();
	}
}
