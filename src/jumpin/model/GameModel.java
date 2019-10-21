package jumpin.model;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.constants.MoveConstants;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.exception.NoTileException;
import jumpin.model.move.FoxMove;
import jumpin.model.move.Move;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.LevelGenerator;
import jumpin.model.util.Position;

/**
 * A class that constructs the game
 * 
 * @author Giuseppe
 * @documentation Cameron Davis
 */
public class GameModel {

	private Board board;
	private GameState gameState;
	private LevelGenerator generator;

	/**
	 * Default constructor for the Game
	 */
	public GameModel() {
		board = new Board();
		gameState = new GameState();
		generator = new LevelGenerator(this.board, this.gameState);
	}

	/**
	 * Moves a piece to a specified position if it is valid
	 * 
	 * @param pos       position of where to move the piece to
	 * @param direction orientation of the piece
	 * @param distance  distance of the Fox piece move
	 * @throws NoPieceException     if there is no piece to move at the specified
	 *                              position
	 * @throws IllegalMoveException if the board does not allow for the move
	 * @throws NoTileException      if the board model is give an invalid position
	 */
	public void movePiece(Position pos, Direction direction, int distance) throws NoPieceException, IllegalMoveException, NoTileException {
		Piece piece;
		try {
			piece = board.selectPiece(pos);
		} catch (NoTileException e) {
			throw e;
		}

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
			if (distance != MoveConstants.DEFAULT_DISTANCE) { // if the player tries to specify tiles rabbit moves
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			Move move = BoardUtilities.findRabbitMove(board, direction);
			if (move == null) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			board.updateBoard(move);
		} else if (piece instanceof Fox) { // Have to move multiple pieces because fox is 2 pieces
			if (distance == MoveConstants.DEFAULT_DISTANCE) {
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

	public List<Move> getValidMoves(Position position) throws NoTileException, NoPieceException {
		List<Move> validMoves = new ArrayList<Move>();
		Piece piece;
		try {
			piece = board.selectPiece(position);
		} catch (NoTileException e) {
			throw e;
		}

		if (piece == null) {
			throw new NoPieceException();
		}

		return validMoves;
	}

	public Board getBoard() {
		return board;
	}

	public GameState getGameState() {
		return this.gameState;
	}

	public LevelGenerator getGenerator() {
		return generator;
	}

	/**
	 * Generates string representation of the game's state and board
	 * 
	 * @return string of game state and board
	 */
	@Override
	public String toString() {
		return gameState.toString() + board.toString();
	}

}