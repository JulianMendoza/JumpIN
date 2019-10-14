package jumpin.model;

import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.MoveConstants;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.constants.StateOfGame;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.move.FoxMove;
import jumpin.model.move.Move;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.Position;

/**
 * A class that constructs the game
 * 
 * @author Giuseppe
 */
public class Game {

	private Board board;
	private GameState gameState;
	
	/**
	 * Default constructor for the Game
	 */
	public Game() {
		board = new Board();
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Rabbit rabbit = new Rabbit(PieceConstants.RABBIT_ID_1);
		Mushroom mushroom= new Mushroom();
		Mushroom mushroom2= new Mushroom();
		Fox foxb = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceConstants.FOX_ID_2);
		Fox foxb2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceConstants.FOX_ID_2);
		Rabbit rabbit2 = new Rabbit(PieceConstants.RABBIT_ID_2);
		Rabbit rabbit3 = new Rabbit(PieceConstants.RABBIT_ID_3);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		board.assignPiece(new Position(3, 0), rabbit);
		board.assignPiece(new Position(3, 1), mushroom);
		board.assignPiece(new Position(4, 3), foxb2);
		board.assignPiece(new Position(3, 3), foxb);
		board.assignPiece(new Position(2, 4), mushroom2);
		board.assignPiece(new Position(1, 4), rabbit2);
		board.assignPiece(new Position(4, 2), rabbit3);
		
		gameState = new GameState(3, StateOfGame.IN_PROGRESS);
		board.addListener(gameState);
	}
	
	/**
	 * Moves a piece to a specified position if it is valid
	 * 
	 * @param pos		position of where to move the piece to
	 * @param direction	orientation of the piece
	 * @param distance	distance of the Fox piece move
	 * @throws NoPieceException		if there is no piece to move at the specified position
	 * @throws IllegalMoveException	if the board does not allow for the move
	 */
	public void movePiece(Position pos, Direction direction, int distance) throws NoPieceException, IllegalMoveException {
		Piece piece = board.selectPiece(pos);

		// First check if a piece exists at a selected position
		if (piece == null) {
			throw new NoPieceException();
		}

		// Second check if the piece is allowed to move in this direction
		if (!piece.allowsDirection(direction)) {
			throw new IllegalMoveException(piece + " is not allowed to move " + direction);
		}

		// Third check if the board allows a move in this direction
		if (!BoardUtilities.allowsDirection(direction, pos)) {
			throw new IllegalMoveException(piece + " cannot move " + direction + " off the board");
		}

		if (piece instanceof Rabbit) {
			if(distance != MoveConstants.DEFAULT_DISTANCE) { //if the player tries to specify tiles rabbit moves
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			Move move = BoardUtilities.findRabbitMove(board, direction);
			if (move == null) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			board.updateBoard(move);
		} else if (piece instanceof Fox) { // Have to move multiple pieces because fox is 2 pieces
			if(distance == MoveConstants.DEFAULT_DISTANCE) {
				distance = MoveConstants.MIN_DISTANCE;
			}
			FoxMove move = BoardUtilities.findFoxMove(board, direction, distance);
			if (move == null) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			board.updateBoard(move.getFirst());
			board.updateBoard(move.getSecond());
		}
	}
	public GameState getGameState() {
		return this.gameState;
	}

	/**
	 * Generates string representation of the game's state and board
	 * @return string of game state and board
	 */
	public String toString() {
		return gameState.toString() + board.toString();
	}

}
