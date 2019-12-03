package jumpin.model.board.util;

import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.board.validator.BoardValidator;
import jumpin.model.constants.BoardConstants;
import jumpin.model.constants.Direction;
import jumpin.model.exception.InvalidBoardException;
import jumpin.model.factory.TileFactory;
import jumpin.model.logic.BoardLogic;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;
import jumpin.model.structures.move.Move;
import jumpin.model.structures.move.MoveSet;

/**
 * Utility class for operations with the board. Has knowledge of the default
 * board model and other constants
 * 
 * @author Giuseppe
 *
 */
public class BoardUtilities {

	/**
	 * method to create visual representation of game board
	 * 
	 * @return text representation of board
	 */
	public static Tile[][] createDefaultBoardModel() {
		int height = BoardConstants.HEIGHT;
		int width = BoardConstants.WIDTH;

		Tile[][] board = new Tile[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (isDefaultRabbitHole(y, x)) {
					board[y][x] = TileFactory.createRabbitHole();
				} else {
					board[y][x] = TileFactory.createTile();
				}
			}
		}
		return board;
	}

	public static void organizeID(Board board) {
		BoardLogic.organizeID(board);
	}

	/**
	 * Only east and south are considered positive directions when north west is
	 * (0,0)
	 *
	 * @param direction Direction of travel
	 * @return True if direction is east and south otherwise false
	 */
	public static boolean isPositive(Direction direction) {
		switch (direction) {
		case EAST:
		case SOUTH:
			return true;
		default:
			return false;
		}
	}

	/**
	 * method to check if direction of travel is valid
	 * 
	 * @param direction Direction a piece travels in
	 * @param pos       Position of a piece
	 * @return True if direction of travel is valid otherwise false
	 */
	public static boolean allowsDirection(Direction direction, Position pos) {
		int minPos = BoardConstants.MIN_POS;
		int maxPos = BoardConstants.MAX_POS;

		int x = pos.getX();
		int y = pos.getY();
		if (direction.isEastWest()) {
			if (x == minPos) { // if the pos is on the west border it can only go east
				return direction.equals(Direction.EAST);
			} else if (x == maxPos) { // if the pos is on the east border it can only go west
				return direction.equals(Direction.WEST);
			}
		} else if (direction.isNorthSouth()) {
			if (y == minPos) { // if the pos is on the north border it can only go south
				return direction.equals(Direction.SOUTH);
			} else if (y == maxPos) { // if the pos is on the south border it can only go north
				return direction.equals(Direction.NORTH);
			}
		}
		return true; // gets here if the move is in the middle of the board
	}

	public static List<MoveSet> getValidMoves(Board board) {
		return BoardLogic.getValidMoves(board);
	}

	/**
	 * method to check for rabbit hole on board
	 * 
	 * @param x X position on Board
	 * @param y Y position on Board
	 * @return True if Rabbit hole exists in x,y otherwise false
	 */
	private static boolean isDefaultRabbitHole(int x, int y) {
		Position[] rabbitHoles = BoardConstants.RABBIT_HOLES;
		for (int i = 0; i < rabbitHoles.length; i++) {
			if (new Position(x, y).equals(rabbitHoles[i])) {
				return true;
			}
		}
		return false;
	}

	public static MoveSet generateMoveSet(Move move, Board board) {
		return BoardLogic.generateMoveSet(move, board);
	}

	/**
	 * Get number of rabbits needed to win
	 * 
	 * @param board
	 * @return number of rabbits on board
	 */
	public static int getRabbitsToWin(Board board) {
		int height = board.getModel().getHeight();
		int width = board.getModel().getWidth();
		int rabbitsToWin = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Position pos = new Position(j, i);
				Tile tile = board.getTile(pos);
				if (tile.getPiece() != null && tile.getPiece() instanceof Rabbit && !(tile instanceof RabbitHole)) {
					rabbitsToWin++;
				}
			}
		}
		return rabbitsToWin;
	}

	/**
	 * method to validate level 
	 * @param board
	 * @param maxMoves
	 * @throws InvalidBoardException
	 */
	public static void validate(Board board, int maxMoves) throws InvalidBoardException {
		BoardValidator.validate(board, maxMoves);
	}

}