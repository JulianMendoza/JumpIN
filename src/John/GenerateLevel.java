package John;


import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.constants.StateOfGame;


/**
 * 
 * @author JOHN
 *
 */

public class GenerateLevel {
	
	private Board board;
	private GameState gameState;

	public GenerateLevel(Board board, GameState gamestate){
		this.board = board;
		this.gameState = gamestate;
	}
	
	public Board createLevel() throws CloneNotSupportedException{
		board=new Board();
		
		AssignFox assignFox = new AssignFox(board);
		assignFox.assign();
		
		AssignRabbit assignRabbit = new AssignRabbit(board);
		gameState.setNumToWin(assignRabbit.assign());
		
		AssignMushroom assignMush = new AssignMushroom(board);
		assignMush.assign();
		
		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
		return board;
		
	}
}