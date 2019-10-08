package jumpin.model;

import java.util.List;

import jumpin.model.constants.Direction;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.util.BoardUtilities;

public class Game {

	private Board board;
	
	public Game() {
		board = new Board();
	}
	
	public void movePiece(Position pos, Direction direction) throws NoPieceException, IllegalMoveException {
		Piece piece = board.selectPiece(pos);
		
		//First check if a piece exists at a selected position
		if(piece == null) {
			throw new NoPieceException();
		}

		//Second check if the piece is allowed to move in this direction
		if(piece.allowsDirection(direction)) {
			throw new IllegalMoveException(piece + " is not allowed to move " + direction);
		}
		
		//Third check if the board allows a move in this direction
		if(BoardUtilities.allowsDirection(direction, pos)) {
			throw new IllegalMoveException(piece + " cannot move " + direction + " off the board");
		}
		
		if(piece instanceof Rabbit) {
			Move move = BoardUtilities.findRabbitMove(board, direction);
			if(move == null) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			board.updateBoard(move);
		} else if(piece instanceof Fox) { //Have to move multiple pieces because fox is 2 pieces
			List<Move> moves = BoardUtilities.findFoxMove();
			if(moves.isEmpty()) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			for(Move move : moves) {
				board.updateBoard(move);
			}
		}
	}
	
}
