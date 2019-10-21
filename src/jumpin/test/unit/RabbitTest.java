package jumpin.test.unit;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import junit.framework.*;

public class RabbitTest extends TestCase {
	private GameModel game;
	private Board board;

	protected void setUp() {
		game = new GameModel();
		board = game.getBoard();
	}
}
