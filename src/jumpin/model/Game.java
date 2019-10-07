package jumpin.model;

import jumpin.model.constants.Direction;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.exception.NoPieceException;

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

		if(piece.allowsDirection(direction)) {
			throw new IllegalMoveException(piece + " is not allowed to move " + direction);
		}
		
		if(piece instanceof Rabbit) {
			
		}
		
		
		
		
	}
	
}
