package jumpin.model;

import jumpin.model.board.Board;
import jumpin.model.constants.Direction;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.MoveConstants;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceConstants;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.move.FoxMove;
import jumpin.model.move.Move;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.BoardUtilities;
import jumpin.model.util.Position;

public class Game {

	private Board board;
	private GameState gameState;

	public Game() {
		board = new Board();
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceConstants.FOX_ID_1);
		Rabbit rabbit = new Rabbit(PieceConstants.RABBIT_ID_1);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		board.assignPiece(new Position(3, 0), rabbit);
		gameState = new GameState(1, "IN_PROGRESS");
		board.addListener(gameState);
	}

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

	public String toString() {
		return gameState.toString() + board.toString();
	}

}
