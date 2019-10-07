package jumpin.model;

import jumpin.model.constants.Direction;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;
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
			Position newPos = BoardUtilities.findRabbitMove(board, pos, direction);
			if(pos.equals(newPos)) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			board.updateBoard(pos, newPos);
		} else if(piece instanceof Fox) {
			
		}
	}
	
}
