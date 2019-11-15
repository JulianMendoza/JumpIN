package jumpin.model.util;

import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.constants.StateOfGame;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;

/**
 * Class to generate levels on load
 * 
 * @author Julian
 *
 */
public class LevelGenerator {
	private Board board;
	private GameState gameState;

	/**
	 * Default constructor for LevelGenerator
	 * 
	 * @param board 
	 * @param gameState
	 */
	public LevelGenerator(Board board, GameState gameState) {
		this.board = board;
		this.gameState = gameState;
	}

	/**
	 * method to create the first level of the game
	 * 
	 */
	public void createLevel1() {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Fox foxb = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox foxb2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Rabbit rabbit2 = new Rabbit(PieceID.RABBIT_ID_2);
		Rabbit rabbit3 = new Rabbit(PieceID.RABBIT_ID_3);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		board.assignPiece(new Position(3, 0), rabbit);
		board.assignPiece(new Position(3, 1), mushroom);
		board.assignPiece(new Position(4, 3), foxb2);
		board.assignPiece(new Position(3, 3), foxb);
		board.assignPiece(new Position(2, 4), mushroom2);
		board.assignPiece(new Position(1, 4), rabbit2);
		board.assignPiece(new Position(4, 2), rabbit3);
		gameState.setNumToWin(3);
		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
	}
	public void createLevelTest() {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(3,2),rabbit);
		board.assignPiece(new Position(3,3),fox);
		board.assignPiece(new Position(4,3),fox2);
		board.assignPiece(new Position(2,4), mushroom);
		board.assignPiece(new Position(1,4), mushroom2);
		gameState.setNumToWin(1);
		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
	}

}
