package jumpin.model.board.generator;

import jumpin.model.GameModel;
import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.board.generator.util.AssignFox;
import jumpin.model.board.generator.util.AssignMushroom;
import jumpin.model.board.generator.util.AssignRabbit;
import jumpin.model.constants.StateOfGame;

/**
 * 
 * @author JOHN
 *
 */

public class GenerateLevel {

	public static GameModel createLevel() throws CloneNotSupportedException {
		Board board = new Board();
		GameState gameState = new GameState();

		AssignFox assignFox = new AssignFox(board);
		assignFox.assign();

		AssignRabbit assignRabbit = new AssignRabbit(board);
		gameState.setNumToWin(assignRabbit.assign());

		AssignMushroom assignMush = new AssignMushroom(board);
		assignMush.assign();

		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
		return new GameModel(board, gameState);

	}
}